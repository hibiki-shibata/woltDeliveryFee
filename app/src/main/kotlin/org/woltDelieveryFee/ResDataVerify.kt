package appkt.resDataVerifyKt

import appkt.serverkt.FeeCalcRequest
import appkt.calculateTotalDeliveryFeeKt.DeliveryFee

class ResDataVerify{

    fun calculateDeliveryFee(request: FeeCalcRequest): Int {
        val deliveryFee = DeliveryFee()
        val finalFee = deliveryFee.sumDeliveryFee(request)
        
        //response Int verification
        if (finalFee < 0) { 
            throw Exception("Final fee was negative")
        }
        
        return finalFee
    }

}
