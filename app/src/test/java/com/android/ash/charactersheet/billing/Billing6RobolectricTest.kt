package com.android.ash.charactersheet.billing

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.appModule
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.BillingResponseCode
import com.android.billingclient.api.BillingClient.Builder
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.Purchase.PurchaseState
import com.d20charactersheet.framework.boc.model.Character
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class Billing6RobolectricTest : KoinTest {

    private val messageDisplay: MessageDisplay by inject()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }


    @Before
    fun before() {
        declareMock<MessageDisplay>()
    }

    @Test
    fun startConnection_everythingIsFine_establishConnection() {
        // Arrange
        val billingClient: BillingClient = mock()
        whenever(billingClient.isReady).thenReturn(false)

        val billingClientBuilder: Builder = mock()
        whenever(billingClientBuilder.setListener(any())).doReturn(billingClientBuilder)
        whenever(billingClientBuilder.enablePendingPurchases()).doReturn(billingClientBuilder)
        whenever(billingClientBuilder.build()).doReturn(billingClient)

        // Act
        Billing6().startConnection(billingClientBuilder)

        // Assert
        verify(billingClient).startConnection(any())
    }

    @Test
    fun startConnection_connectionAlreadyEstablished_doNothing() {
        // Arrange
        val billingClient: BillingClient = mock()
        whenever(billingClient.isReady).thenReturn(true)
        val underTest = Billing6()
        underTest.billingClient = billingClient

        val billingClientBuilder: Builder = mock()

        // Act
        underTest.startConnection(billingClientBuilder)

        // Assert
        verifyNoMoreInteractions(billingClientBuilder)
    }

    @Test
    fun endConnection_everythingIsFine_closeConnection() {
        // Arrange
        val underTest = Billing6()
        underTest.billingClient = mock()

        // Act
        underTest.endConnection()

        // Assert
        verify(underTest.billingClient)?.endConnection()
    }

    /*
        @Test
        fun onBillingSetupFinished_responseCodeOk_queryProductDetailsListAndQueryPurchases() {
            // Arrange
            val billingClient: BillingClient = mock()
            whenever(billingClient.isReady).doReturn(true)

            val logger: Logger = mock()
            val underTest = Billing(logger)
            underTest.billingClient = billingClient

            val billingResult: BillingResult = mock()
            whenever(billingResult.responseCode).doReturn(BillingResponseCode.OK)

            // Act
            underTest.onBillingSetupFinished(billingResult)

            // Assert
            verify(underTest.billingClient)!!.queryProductDetailsAsync(any(), any())
            verify(underTest.billingClient)!!.queryPurchases(ProductType.INAPP)
        }
    */

    @Test
    fun onBillingSetupFinished_responseCodeNotOk_displayErrorMessage() {
        // Arrange
        val billingClient: BillingClient = mock()

        val underTest = Billing6()
        underTest.billingClient = billingClient

        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.ERROR)

        // Act
        underTest.onBillingSetupFinished(billingResult)

        // Assert
        verifyNoMoreInteractions(billingClient)
        verify(messageDisplay).display(R.string.billing_connection_error, BillingResponseCode.ERROR)
    }

    @Test
    fun onProductDetailsResponse_responseOk_setProductDetailsList() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.OK)
        val productDetailsList: MutableList<ProductDetails> = mutableListOf()
        val underTest = Billing6()

        // Act
        underTest.onProductDetailsResponse(billingResult, productDetailsList)

        // Assert
        assertThat(underTest.productDetails).isSameAs(productDetailsList)
    }

    @Test
    fun onProductDetailsResponse_responseError_productDetailsListNotSet() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.ERROR)
        val underTest = Billing6()

        // Act
        underTest.onProductDetailsResponse(billingResult, mock())

        // Assert
        assertThat(underTest.productDetails).isNull()
    }

    @Test
    fun startBillingFlow_billingClientIsNull_displayErrorMessage() {
        // Arrange
        val underTest = Billing6()
        underTest.billingClient = null

        // Act
        underTest.startBillingFlow(mock())

        // Assert
        val captor = argumentCaptor<Int>()
        verify(messageDisplay).display(captor.capture())
        assertThat(captor.firstValue).isEqualTo(R.string.billing_no_connection)
    }

    @Test
    fun startBillingFlow_billingClientIsNotReady_displayErrorMessage() {
        // Arrange
        val underTest = Billing6()
        val billingClient: BillingClient = mock()
        whenever(billingClient.isReady).doReturn(false)
        underTest.billingClient = billingClient

        // Act
        underTest.startBillingFlow(mock())

        // Assert
        val captor = argumentCaptor<Int>()
        verify(messageDisplay).display(captor.capture())
        assertThat(captor.firstValue).isEqualTo(R.string.billing_no_connection)
    }

    @Test
    fun onPurchasesUpdated_premiumVersionPurchased_setPremiumVersionAsPurchasedAndAcknowledgePurchase() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.OK)
        val purchase: Purchase = mock()
        whenever(purchase.purchaseState).doReturn(PurchaseState.PURCHASED)
        whenever(purchase.purchaseToken).doReturn("myPurchaseToken")

        val underTest = Billing6()
        underTest.billingClient = mock()

        // Act
        underTest.onPurchasesUpdated(billingResult, listOf(purchase))

        // Assert
        assertThat(underTest.premiumVersion.purchaseState).isEqualTo(PurchaseState.PURCHASED)
        verify(underTest.billingClient)?.acknowledgePurchase(any(), any())
        verifyNoMoreInteractions(messageDisplay)
    }

    @Test
    fun onPurchasesUpdated_purchaseIsPending_setPremiumVersionAsPendingAndDoNotAcknowledgePurchaseAndDisplayMessage() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.OK)
        val purchase: Purchase = mock()
        whenever(purchase.purchaseState).doReturn(PurchaseState.PENDING)
        val billingClient: BillingClient = mock()

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.onPurchasesUpdated(billingResult, listOf(purchase))

        // Assert
        assertThat(underTest.premiumVersion.purchaseState).isEqualTo(PurchaseState.PENDING)
        verifyNoMoreInteractions(billingClient)
        verify(messageDisplay).display(R.string.billing_purchase_pending)
    }

    @Test
    fun onPurchasesUpdated_purchaseIsInUnspecifiedStated_setPremiumVersionAsUnspecifiedAndDoNotAcknowledgePurchase() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.OK)
        val purchase: Purchase = mock()
        whenever(purchase.purchaseState).doReturn(PurchaseState.UNSPECIFIED_STATE)
        val billingClient: BillingClient = mock()

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.onPurchasesUpdated(billingResult, listOf(purchase))

        // Assert
        assertThat(underTest.premiumVersion.purchaseState).isEqualTo(PurchaseState.UNSPECIFIED_STATE)
        verifyNoMoreInteractions(billingClient)
        verifyNoMoreInteractions(messageDisplay)
    }

    @Test
    fun onPurchasesUpdated_userCanceled_noAction() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.USER_CANCELED)
        val billingClient: BillingClient = mock()

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.onPurchasesUpdated(billingResult, listOf())

        // Assert
        verifyNoMoreInteractions(billingClient)
        verifyNoMoreInteractions(messageDisplay)
    }

    @Test
    fun onPurchasesUpdated_itemAlreadyOwned_noAction() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.ITEM_ALREADY_OWNED)
        val billingClient: BillingClient = mock()

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.onPurchasesUpdated(billingResult, listOf())

        // Assert
        verifyNoMoreInteractions(billingClient)
        verifyNoMoreInteractions(messageDisplay)
    }

    @Test
    fun onPurchasesUpdated_someError_displayErrorMessage() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.ERROR)
        val billingClient: BillingClient = mock()

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.onPurchasesUpdated(billingResult, listOf())

        // Assert
        verifyNoMoreInteractions(billingClient)
        verify(messageDisplay).display(
            R.string.billing_purchasesUpdated_error,
            BillingResponseCode.ERROR
        )
    }

    @Test
    fun queryProductDetailsList_everythingFine_queryProductDetailsAsync() {
        // Arrange
        val billingClient: BillingClient = mock()
        whenever(billingClient.isReady).doReturn(true)

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.queryProductDetailsList()

        // Assert
        verify(billingClient).queryProductDetailsAsync(any(), any())
    }

    @Test
    fun queryProductDetailsList_billingClientNotReady_startConnection() {
        // Arrange
        val billingClient: BillingClient = mock()
        whenever(billingClient.isReady).doReturn(false)

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.queryProductDetailsList()

        // Assert
        verify(messageDisplay).display(R.string.billing_no_connection_queryProductDetailsList)
    }

    @Test
    fun queryProductDetailsList_billingClientIsNull_startConnection() {
        // Act
        Billing6().queryProductDetailsList()

        // Assert
        verify(messageDisplay).display(R.string.billing_no_connection_queryProductDetailsList)
    }

    /*
            @Test
            fun queryPurchases_purchased_productPurchaseStateIsPurchased() {
                // Arrange
                val purchase: Purchase = mock()
                whenever(purchase.products).doReturn(ArrayList<String>().also { it.add("premium_version") })
                whenever(purchase.purchaseState).doReturn(PurchaseState.PURCHASED)
                val purchaseResult = Purchase.PurchasesResult(BillingResult(), listOf(purchase))

                val billingClient: BillingClient = mock()
                whenever(billingClient.isReady).doReturn(true)
                whenever(billingClient.queryPurchases(ProductType.INAPP)).doReturn(purchaseResult)


                val underTest = Billing6()
                underTest.billingClient = billingClient

                // Act
                underTest.queryPurchases()

                // Assert
                assertThat(underTest.premiumVersion.purchaseState).isEqualTo(PurchaseState.PURCHASED)
            }
    */

    @Test
    fun queryPurchases_billingClientIsNotReady_displayErrorMessage() {
        // Arrange
        val billingClient: BillingClient = mock()
        whenever(billingClient.isReady).doReturn(false)

        val underTest = Billing6()
        underTest.billingClient = billingClient

        // Act
        underTest.queryPurchases()

        // Assert
        verify(messageDisplay).display(R.string.billing_no_connection_queryPurchases)
    }

    @Test
    fun queryPurchases_billingClientIsNull_displayErrorMessage() {
        // Act
        Billing6().queryPurchases()

        // Assert
        verify(messageDisplay).display(R.string.billing_no_connection_queryPurchases)
    }

    @Test
    fun requiresPurchase_zeroCharacters_purchaseNotRequired() {
        // Act
        val requiresPurchase = Billing6().requiresPurchase(listOf())

        // Assert
        assertThat(requiresPurchase).isFalse
    }

    @Test
    fun requiresPurchase_defaultCharacter_purchaseNotRequired() {
        // Act
        val requiresPurchase = Billing6().requiresPurchase(listOf(Character().apply {
            id = Billing6.DEFAULT_CHARACTER_ID
        }))

        // Assert
        assertThat(requiresPurchase).isFalse
    }

    @Test
    fun requiresPurchase_oneCharacter_purchaseRequired() {
        // Act
        val requiresPurchase = Billing6().requiresPurchase(listOf(Character().apply { id = 1 }))

        // Assert
        assertThat(requiresPurchase).isTrue
    }

    @Test
    fun requiresPurchase_oneCharacterAndDefaultCharacter_purchaseRequired() {
        // Act
        val requiresPurchase = Billing6().requiresPurchase(
            listOf(
                Character().apply { id = Billing6.DEFAULT_CHARACTER_ID },
                Character().apply { id = 1 })
        )

        // Assert
        assertThat(requiresPurchase).isTrue
    }

    @Test
    fun requiresPurchase_premiumVersionWithOneCharacter_purchaseNotRequired() {
        // Arrange
        val underTest = Billing6().apply {
            premiumVersion.purchaseState = PurchaseState.PURCHASED
        }

        // Act
        val requiresPurchase = underTest.requiresPurchase(listOf(Character().apply { id = 1 }))

        // Assert
        assertThat(requiresPurchase).isFalse
    }

    @Test
    fun requiresPurchase_premiumVersionWithOneCharacterAndDefaultCharacter_purchaseRequired() {
        // Arrange
        val underTest = Billing6().apply {
            premiumVersion.purchaseState = PurchaseState.PURCHASED
        }

        // Act
        val requiresPurchase = underTest.requiresPurchase(
            listOf(
                Character().apply { id = Billing6.DEFAULT_CHARACTER_ID },
                Character().apply { id = 1 })
        )

        // Assert
        assertThat(requiresPurchase).isFalse
    }

    @Test
    fun onAcknowledgePurchaseResponse_responseCodeIsOK_noAction() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.OK)

        // Act
        Billing6().onAcknowledgePurchaseResponse(billingResult)

        // Assert
        verifyNoMoreInteractions(messageDisplay)
    }

    @Test
    fun onAcknowledgePurchaseResponse_error_displayErrorMessage() {
        // Arrange
        val billingResult: BillingResult = mock()
        whenever(billingResult.responseCode).doReturn(BillingResponseCode.ERROR)

        // Act
        Billing6().onAcknowledgePurchaseResponse(billingResult)

        // Assert
        verify(messageDisplay).display(
            R.string.billing_onAcknowledgePurchaseResponse_error,
            BillingResponseCode.ERROR
        )
    }

}