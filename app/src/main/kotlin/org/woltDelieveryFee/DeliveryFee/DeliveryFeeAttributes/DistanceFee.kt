package appkt.calculateTotalDeliveryFeeKt

import kotlin.math.ceil


// Spec:1€ is added for every additional 500 meters. Even if the distance would be shorter than 500 meters, the minimum fee is always 1€.
internal fun DeliveryFee.calculateDistanceFee(distance: Int): Int {
    val baseDistanceFee: Int = 200
    val firstMeterThreshold: Int = 1000
    val secondMeterThreshold: Int = 500 //surchage will be added by each distances of this value(meter)
    val distanceFeePer100m: Int = 100
    val additionalDistance: Int = distance - firstMeterThreshold
    val additionalDistanceFee: Int = if (additionalDistance > 0) {
        (ceil(additionalDistance / secondMeterThreshold.toDouble()) * distanceFeePer100m).toInt() //ceil -> rounding up fractional number
    } else 0
    
    val TotaldistanceFee: Int = baseDistanceFee + additionalDistanceFee


    if(distance < 0 || TotaldistanceFee < distanceFeePer100m) throw Exception("error in calculateDistanceFee")
return TotaldistanceFee
}