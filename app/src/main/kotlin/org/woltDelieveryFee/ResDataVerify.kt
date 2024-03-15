package resDataVerifyKt

import serverkt.FeeCalcRequest
import calculateTotalDeliveryFeeKt.Deliveryfee

class ResDataVerify{

    fun calculateDeliveryFee(request: FeeCalcRequest): Int {
        val deliveryFee = Deliveryfee()
        val finalFee = deliveryFee.sumDeliveryFee(request)
        
        //response Int verification
        if (finalFee < 0) { 
            throw Exception("Final fee was negative")
        }
        
        return finalFee
    }

}
