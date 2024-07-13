package appkt.calculateTotalDeliveryFeeKt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class CalculateOrderSurchargeTest {
    val deliveryFee = DeliveryFee()

    @Test fun `If the cart value is less than 10€, a small order surcharge is added to the delivery price The surcharge is the difference between the cart value and 10€`() {
        // calculateOrderSurcharge(cart_value)
        val minus = assertFailsWith<Exception> {
            deliveryFee.calculateOrderSurcharge(-100000)
        }        
        val zero = deliveryFee.calculateOrderSurcharge(0)
        val mid = deliveryFee.calculateOrderSurcharge(790)
        val just = deliveryFee.calculateOrderSurcharge(1000)
        val large = deliveryFee.calculateOrderSurcharge(10000)

        assertEquals("error in calculateOrderSurcharge", minus.message)
        assertEquals(1000, zero)
        assertEquals(210, mid)
        assertEquals(0, just)
        assertEquals(0, large)   
        
    }



}
