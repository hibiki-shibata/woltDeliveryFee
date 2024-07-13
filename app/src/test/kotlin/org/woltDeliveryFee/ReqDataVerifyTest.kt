// testing ReqDataVerify.kt
package appkt.reqDataVerifyKt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import appkt.serverkt.FeeCalcRequest
import java.time.OffsetDateTime

class ReqDataVerifyeqDataverification {

    val reqDataVerify = ReqDataVerify()

    @Test  fun `Check the requested values are always postive number`(){

        val generalReq = FeeCalcRequest(
                cart_value = 1000, 
                delivery_distance = 2500,
                number_of_items = 3,
                time = "2024-02-03T05:35:00Z" 
        )
        
            val isTrue = reqDataVerify.jsonVerification(generalReq)
            assertTrue(isTrue)


        val `negative cart_value` = FeeCalcRequest(
                cart_value = -1000, 
                delivery_distance = 2500,
                number_of_items = 3,
                time = "2024-02-03T05:35:00Z" 
        )

            val isFalse1 = reqDataVerify.jsonVerification(`negative cart_value`)
            assertFalse(isFalse1)


        val `negative delivery_distance` = FeeCalcRequest(
                cart_value = 1000, 
                delivery_distance = -2500,
                number_of_items = 3,
                time = "2024-02-03T05:35:00Z" 
        )

            val isFalse2 = reqDataVerify.jsonVerification(`negative delivery_distance`)
            assertFalse(isFalse2)


        val `negative number_of_items` = FeeCalcRequest(
                cart_value = 1000, 
                delivery_distance = 2500,
                number_of_items = -3,
                time = "2024-02-03T05:35:00Z" 
        )

            val isFalse3 = reqDataVerify.jsonVerification(`negative number_of_items`)
            assertFalse(isFalse3)


        val `Invalid time format` = FeeCalcRequest(
                cart_value = 1000, 
                delivery_distance = 2500,
                number_of_items = -3,
                time = "2024-02-03T05:35:0000Z" 
        )

            val isFalse4 = reqDataVerify.jsonVerification(`Invalid time format`)
            assertFalse(isFalse4)    

    }


    @Test fun `Check if the requested value is in the specified format`(){
        val expectedFormat = "2024-02-03T05:35:00Z"
        val unexpectedFormat = "2024-02-03T05:35:0000Z"

     
        val expectedFormatResult = reqDataVerify.isValidTime(expectedFormat)
        val unexpectedFormatResult = reqDataVerify.isValidTime(unexpectedFormat)
        
        assertTrue(expectedFormatResult)
        assertFalse(unexpectedFormatResult)

    }

}