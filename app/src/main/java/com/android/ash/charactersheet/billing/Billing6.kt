package com.android.ash.charactersheet.billing

import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.Logger
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.AcknowledgePurchaseResponseListener
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.ProductDetailsResponseListener
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryPurchasesAsync
import com.d20charactersheet.framework.boc.model.Character
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Billing6(private val logger: Logger = Logger) : PurchasesUpdatedListener,
    BillingClientStateListener, ProductDetailsResponseListener, AcknowledgePurchaseResponseListener,
    KoinComponent {

    companion object {
        const val DEFAULT_CHARACTER_ID = 0
    }

    private val messageDisplay: MessageDisplay by inject()

    var premiumVersion: Product = Product("premium_version")

    internal var billingClient: BillingClient? = null
    var productDetails: List<ProductDetails>? = null

    fun startConnection(billingClientBuilder: BillingClient.Builder) {
        logger.debug("startConnection")
        if (billingClient?.isReady != true) {
            billingClient = billingClientBuilder.setListener(this).enablePendingPurchases().build()
            billingClient?.startConnection(this)
        }
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        logger.debug("onBillingSetupFinished (billingResult.responseCode=${billingResult.responseCode})")
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            queryProductDetailsList()
            queryPurchases()
        } else {
            messageDisplay.display(R.string.billing_connection_error, billingResult.responseCode)
        }
    }

    override fun onBillingServiceDisconnected() {
        logger.debug("onBillingServiceDisconnected")
    }

    fun queryProductDetailsList() {
        logger.debug("queryProductsDetailsList()")

        if (billingClient?.isReady == true) {

            val productList = listOf(
                QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(premiumVersion.productDetail)
                    .setProductType(BillingClient.ProductType.INAPP)
                    .build()
            )
            val queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                    .setProductList(productList)
                    .build()

            billingClient?.queryProductDetailsAsync(queryProductDetailsParams, this)
        } else {
            messageDisplay.display(R.string.billing_no_connection_queryProductDetailsList)
        }

    }

    override fun onProductDetailsResponse(
        billingResult: BillingResult,
        productDetailsList: MutableList<ProductDetails>
    ) {
        logger.debug("onProductDetailsResponse(billingResult.responseCode=${billingResult.responseCode}, productDetailsList=$productDetailsList)")
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            productDetails = productDetailsList
        }
    }


    fun queryPurchases() {
        logger.debug("queryPurchases")
        if (billingClient?.isReady == true) {

            runBlocking {
                val params = QueryPurchasesParams.newBuilder()
                    .setProductType(BillingClient.ProductType.INAPP)
                    .build()
                billingClient?.queryPurchasesAsync(params)?.purchasesList?.forEach {
                    for (product in it.products) {
                        if (product == premiumVersion.productDetail) {
                            premiumVersion.purchaseState = it.purchaseState
                        }
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

            productDetails?.get(0)?.let {

                val productDetailsParamsList = listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(it)
                        .build()
                )

                val billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList)
                    .build()

                billingClient?.launchBillingFlow(activity, billingFlowParams)
            }
        } else {
            messageDisplay.display(R.string.billing_no_connection)
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        logger.debug("onPurchasesUpdated(billingResult=$billingResult,purchases=$purchases)")
        purchases?.let {
            when (billingResult.responseCode) {
                BillingClient.BillingResponseCode.OK -> handlePurchases(purchases)
                BillingClient.BillingResponseCode.USER_CANCELED -> logger.debug("billingResult.responseCode=BillingResponseCode.USER_CANCELED")
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> logger.debug("billingResult.responseCode=BillingResponseCode.ITEM_ALREADY_OWNED")
                else -> messageDisplay.display(
                    R.string.billing_purchasesUpdated_error,
                    billingResult.responseCode
                )
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
            Purchase.PurchaseState.PURCHASED -> acknowledgePurchase(purchase)
            Purchase.PurchaseState.PENDING -> messageDisplay.display(R.string.billing_purchase_pending)
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        if (!purchase.isAcknowledged) {
            val acknowledgePurchaseParams =
                AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken)
                    .build()
            billingClient?.acknowledgePurchase(acknowledgePurchaseParams, this)
        }
    }

    override fun onAcknowledgePurchaseResponse(billingResult: BillingResult) {
        logger.debug("onAcknowledgePurchaseResponse(billingResult=$billingResult)")
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> logger.debug("billingResult.responseCode=BillingResponseCode.OK")
            else -> messageDisplay.display(
                R.string.billing_onAcknowledgePurchaseResponse_error,
                billingResult.responseCode
            )
        }
    }

    fun requiresPurchase(allCharacters: List<Character>) =
        !(hasPremiumVersion() || hasNoCharacters(allCharacters) || hasOnlyDefaultCharacter(
            allCharacters
        ))

    private fun hasPremiumVersion() =
        premiumVersion.purchaseState == Purchase.PurchaseState.PURCHASED

    private fun hasNoCharacters(allCharacters: List<Character>) =
        allCharacters.isEmpty()

    private fun hasOnlyDefaultCharacter(allCharacters: List<Character>) =
        (allCharacters.size == 1 && allCharacters[0].id == DEFAULT_CHARACTER_ID)

}