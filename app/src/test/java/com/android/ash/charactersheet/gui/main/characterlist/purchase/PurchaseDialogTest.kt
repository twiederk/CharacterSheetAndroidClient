package com.android.ash.charactersheet.gui.main.characterlist.purchase

import android.content.DialogInterface
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.billing.Billing
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock


class PurchaseDialogTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val billing: Billing by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<Billing>()
        declareMock<FirebaseAnalytics>()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun show_showPurchaseDialog_showDialogAndLogFirebaseEvent() {

        // Arrange
        val alertDialog: AlertDialog = mock()
        val alertDialogBuilder: AlertDialog.Builder = mock()
        whenever(alertDialogBuilder.setView(null)).doReturn(alertDialogBuilder)
        whenever(alertDialogBuilder.create()).doReturn(alertDialog)

        val layoutInflater: LayoutInflater = mock()
        val activity: AppCompatActivity = mock()
        whenever(activity.getSystemService(any())).doReturn(layoutInflater)

        // Act
        PurchaseDialog(alertDialogBuilder).show(activity)

        // Assert
        verify(alertDialog, only()).show()
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.PURCHASE_DIALOG_SHOW, null)
    }

    @Test
    fun performPurchase_purchasePremiumVersion_startBillingFlowAndLogFirebaseEvent() {
        // Act
        PurchaseDialog(mock()).performPurchase(mock())

        // Assert
        verify(billing).startBillingFlow(any())
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.PURCHASE_PERFORM, null)
    }

    @Test
    fun cancelPurchase_purchaseCancel_closeDialogAndLogFirebaseEvent() {
        // Arrange
        val dialog: DialogInterface = mock()

        // Act
        PurchaseDialog(mock()).cancelPurchase(dialog)

        // Assert
        verify(dialog).cancel()
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.PURCHASE_CANCEL, null)
    }

    @Test
    fun restorePurchase_PurchaseRestore_queryPurchase() {
        // Arrange
        val dialog: DialogInterface = mock()

        // Act
        PurchaseDialog(mock()).restorePurchase(dialog)

        // Assert
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.PURCHASE_RESTORE, null)
        verify(billing).queryPurchases()
        verify(dialog).cancel()
    }

}