package appkt.calculateTotalDeliveryFeeKt


//Spec:If the cart value is less than 10€, a small order surcharge is added to the delivery price. The surcharge is the difference between the cart value and 10€.
internal fun DeliveryFee.calculateOrderSurcharge(cartValue: Int): Int{
    val surcharges: Int
    val cartMinValue: Int = 1000

    surcharges = if (cartValue <= cartMinValue) {
        cartMinValue - cartValue
    } else 0
    

    if(cartValue < 0 || cartMinValue < surcharges || surcharges < 0) throw Exception("error in calculateOrderSurcharge")    
return surcharges
}