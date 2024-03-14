// checking if numbers are postive and if time format is in ISO format
package reqDataVerifyKt

import serverkt.FeeCalcRequest
import java.time.OffsetDateTime
// import kotlinx.serialization.SerializationException
import io.ktor.server.plugins.BadRequestException



class ReqDataVerify {

    // Final data validation
    fun invalidateRequest(request: FeeCalcRequest) {
        if (!jsonVerification(request)) {
            throw BadRequestException("keys are ok but value was minus or fractional number. otherwise time format was wrong")
        }
    }


    fun jsonVerification(request: FeeCalcRequest): Boolean {
        return  request.cart_value >= 0 &&
                request.delivery_distance >= 0 &&
                request.number_of_items >= 0 &&
                isValidTime(request.time)
    }
    
    
    fun isValidTime(time: String): Boolean {
        return try {
                    OffsetDateTime.parse(time)
                    true
                } catch (e: Exception) {
                    false
                }
    }

}