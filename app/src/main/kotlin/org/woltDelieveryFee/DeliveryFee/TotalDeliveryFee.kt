// This file calculate final delivery fee
package appkt.calculateTotalDeliveryFeeKt

// import java.time.DayOfWeek
import java.time.OffsetDateTime
// import kotlin.math.ceil
import appkt.serverkt.FeeCalcRequest



class DeliveryFee {

    // Total calculation
      fun sumDeliveryFee(request: FeeCalcRequest): Int {        
    
            val deliveryTime: OffsetDateTime = OffsetDateTime.parse(request.time)

            val cartValueForFreeDelivery: Int = 20000
            val deliveryFeeMaxCap: Int = 1500

            //Spec:The delivery is free (0€) when the cart value is equal or more than 200€.
            if (request.cart_value >= cartValueForFreeDelivery) return 0
            
            // Calculate each type of fee
            val smallOrderSurcharge: Int = calculateOrderSurcharge(request.cart_value)
            val distanceFee: Int = calculateDistanceFee(request.delivery_distance)
            val itemSurcharge: Int = calculateItemSurcharge(request.number_of_items)

            var deliveryFeeSubTotal: Int = smallOrderSurcharge + distanceFee + itemSurcharge

            // Check if RushHour
            if (isRushHour(deliveryTime)) {
                val rushDeliveryFee: Int = calculateRushHourFee(deliveryFeeSubTotal, deliveryFeeMaxCap)
                return rushDeliveryFee
            }

            //Spec: The delivery fee can never be more than 15€, including possible surcharges.
            val deliveryFee: Int = if (deliveryFeeSubTotal >= deliveryFeeMaxCap) deliveryFeeMaxCap else deliveryFeeSubTotal


            if(deliveryFee < 0 || deliveryFeeMaxCap < deliveryFee ) throw Exception("Error happened in SumDeliveryFee")
        return deliveryFee

    }


}


// spec flaws
// should I apply bulk fee when the number of item is just 12?
// should I round up when return value of RushHours multiplies?
// 