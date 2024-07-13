package appkt.calculateTotalDeliveryFeeKt


//Spec:If the number of items is five or more, an additional 50 cent surcharge is added for each item above and including the fifth item. An extra "bulk" fee applies for more than 12 items of 1,20€.
internal fun DeliveryFee.calculateItemSurcharge(numberOfItems: Int): Int {
        val baseItemSurcharge: Int = 50
        val additionalSurchargeThreshold: Int = 5
        val bulkFee: Int = 120
        val bulkFeeThreshold: Int = 12

        var additionalSurcharge: Int = if (numberOfItems >= additionalSurchargeThreshold) {
            val extraItems: Int = numberOfItems - (additionalSurchargeThreshold - 1)
            baseItemSurcharge * extraItems 
        } else 0

        //Bulk fee
        additionalSurcharge += if (numberOfItems > bulkFeeThreshold) bulkFee else 0 //CONVERSATIONAL


        if(numberOfItems < 0 || additionalSurcharge < 0) throw Exception("error in calculateItemSurcharge")
    return additionalSurcharge
}