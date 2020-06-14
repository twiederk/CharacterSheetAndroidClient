package com.android.ash.charactersheet.billing

import com.android.billingclient.api.Purchase

data class Product(val sku: String, var purchaseState: Int = Purchase.PurchaseState.UNSPECIFIED_STATE)
