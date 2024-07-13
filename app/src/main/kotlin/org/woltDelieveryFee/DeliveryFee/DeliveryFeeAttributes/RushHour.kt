package appkt.calculateTotalDeliveryFeeKt

import java.time.DayOfWeek
import java.time.OffsetDateTime


//Spec:During the Friday rush, 3 - 7 PM(UTC), the delivery fee (the total fee including possible surcharges) will be multiplied by 1.2x. However, the fee still cannot be more than the max (15€).
internal fun DeliveryFee.isRushHour(deliveryTime: OffsetDateTime): Boolean{
        val isBetween3pmAnd7pm: Boolean = deliveryTime.hour in 15..19
        val isFriday: Boolean = deliveryTime.getDayOfWeek() == DayOfWeek.FRIDAY
    
    return isBetween3pmAnd7pm && isFriday
}


internal fun DeliveryFee.calculateRushHourFee(originalFee: Int, deliveryFeeMaxCap: Int): Int {
        val RushHourMultiplier: Double = 1.2
        val updatedFee: Int = (originalFee * RushHourMultiplier).toInt() //CONVERSATIONAL

        val deliveryFee: Int = if (updatedFee >= deliveryFeeMaxCap) deliveryFeeMaxCap else updatedFee

    
        if(originalFee < 0 || deliveryFeeMaxCap < deliveryFee || deliveryFee < 0) throw Exception("error in calculateRushHourFee")
    return deliveryFee
}