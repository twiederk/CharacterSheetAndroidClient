package com.android.ash.charactersheet.billing

import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.Logger
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.*
import com.android.billingclient.api.Purchase.PurchaseState
import com.d20charactersheet.framework.boc.model.Character
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Billing(private val logger: Logger = Logger) : PurchasesUpdatedListener, SkuDetailsResponseListener, BillingClientStateListener, AcknowledgePurchaseResponseListener, KoinComponent {

    companion object {
        const val DEFAULT_CHARACTER_ID = 0
    }

    private val messageDisplay: MessageDisplay by inject()

    var premiumVersion: Product = Product("premium_version")

    internal var billingClient: BillingClient? = null
    var skuDetailsList: List<SkuDetails>? = null

    fun startConnection(billingClientBuilder: Builder) {
        if (billingClient?.isReady != true) {
            billingClient = billingClientBuilder.setListener(this).enablePendingPurchases().build()
            billingClient?.startConnection(this)
        }
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        logger.debug("onBillingSetupFinished (billingResult.responseCode=${billingResult.responseCode})")
        if (billingResult.responseCode == BillingResponseCode.OK) {
            querySkuDetailsList()
            queryPurchases()
        } else {
            messageDisplay.display(R.string.billing_connection_error, billingResult.responseCode)
        }
    }

    override fun onBillingServiceDisconnected() {
        logger.debug("onBillingServiceDisconnected")
    }

    fun querySkuDetailsList() {
        logger.debug("querySkuDetailsList()")

        if (billingClient?.isReady == true) {
            val skuList: MutableList<String> = ArrayList()
            skuList.add(premiumVersion.sku)

            val params = SkuDetailsParams.newBuilder()
            params.setSkusList(skuList).setType(SkuType.INAPP)

            billingClient?.querySkuDetailsAsync(params.build(), this)
        } else {
            messageDisplay.display(R.string.billing_no_connection_querySkuDetailsList)
        }
    }

    override fun onSkuDetailsResponse(billingResult: BillingResult, skuDetailsList: MutableList<SkuDetails>?) {
        logger.debug("onSkuDetailsResponse(billingResult.responseCode=${billingResult.responseCode}, skuDetailsList=$skuDetailsList)")
        if (billingResult.responseCode == BillingResponseCode.OK) {
            this.skuDetailsList = skuDetailsList
        }
    }

    fun queryPurchases() {
        logger.debug("queryPurchases")
        if (billingClient?.isReady == true) {
            billingClient?.queryPurchases(SkuType.INAPP)?.purchasesList?.forEach {
                for (sku in it.skus) {
                    if (sku == premiumVersion.sku) {
                        premiumVersion.purchaseState = it.purchaseState
                    }
                }
            }
        } else {
            messageDisplay.display(R.string.billing_no_connection_queryPurchases)
        }

    }

    fun endConnection() {
        logger.debug("endConnection()")
        billingClient?.endConnection()
    }

    fun startBillingFlow(activity: AppCompatActivity) {
        logger.debug("startBillingFlow(activity=${activity}")
        if (billingClient?.isReady == true) {
            skuDetailsList?.get(0)?.let {
                val flowParams = BillingFlowParams.newBuilder().setSkuDetails(it).build()
                billingClient?.launchBillingFlow(activity, flowParams)
            }
        } else {
            messageDisplay.display(R.string.billing_no_connection)
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        logger.debug("onPurchasesUpdated(billingResult=$billingResult,purchases=$purchases)")
        purchases?.let {
            when (billingResult.responseCode) {
                BillingResponseCode.OK -> handlePurchases(purchases)
                BillingResponseCode.USER_CANCELED -> logger.debug("billingResult.responseCode=BillingResponseCode.USER_CANCELED")
                BillingResponseCode.ITEM_ALREADY_OWNED -> logger.debug("billingResult.responseCode=BillingResponseCode.ITEM_ALREADY_OWNED")
                else -> messageDisplay.display(R.string.billing_purchasesUpdated_error, billingResult.responseCode)
            }
        }
    }

    private fun handlePurchases(purchases: List<Purchase>) {
        logger.debug("billingResult.responseCode=BillingResponseCode.OK")
        for (purchase in purchases) {
            handlePurchase(purchase)
        }
    }

    private fun handlePurchase(purchase: Purchase) {
        logger.debug("handlePurchase(purchase=$purchase")
        premiumVersion.purchaseState = purchase.purchaseState
        when (premiumVersion.purchaseState) {
            PurchaseState.PURCHASED -> acknowledgePurchase(purchase)
            PurchaseState.PENDING -> messageDisplay.display(R.string.billing_purchase_pending)
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        if (!purchase.isAcknowledged) {
            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
            billingClient?.acknowledgePurchase(acknowledgePurchaseParams, this)
        }
    }

    override fun onAcknowledgePurchaseResponse(billingResult: BillingResult) {
        logger.debug("onAcknowledgePurchaseResponse(billingResult=$billingResult)")
        when (billingResult.responseCode) {
            BillingResponseCode.OK -> logger.debug("billingResult.responseCode=BillingResponseCode.OK")
            else -> messageDisplay.display(R.string.billing_onAcknowledgePurchaseResponse_error, billingResult.responseCode)
        }
    }

    fun requiresPurchase(allCharacters: List<Character>) = !(hasPremiumVersion() || hasNoCharacters(allCharacters) || hasOnlyDefaultCharacter(allCharacters))

    private fun hasPremiumVersion() = premiumVersion.purchaseState == PurchaseState.PURCHASED

    private fun hasNoCharacters(allCharacters: List<Character>) =
        allCharacters.isEmpty()

    private fun hasOnlyDefaultCharacter(allCharacters: List<Character>) =
        (allCharacters.size == 1 && allCharacters[0].id == DEFAULT_CHARACTER_ID)

}
