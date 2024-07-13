package appkt.calculateTotalDeliveryFeeKt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class calculateDistanceFeeTest {
    val deliveryFee = DeliveryFee()


    @Test fun `1€ is added for every additional 500 meters Even if the distance would be shorter than 500 meters, the minimum fee is always 1€`() {
        // calculateDistanceFee(delivery_distance)
        val minus = assertFailsWith<Exception> {
            deliveryFee.calculateDistanceFee(-100000)
        }   
        val zero = deliveryFee.calculateDistanceFee(0)
        val minimum = deliveryFee.calculateDistanceFee(500)
        val middle = deliveryFee.calculateDistanceFee(800)
        val just = deliveryFee.calculateDistanceFee(1000)
        val large = deliveryFee.calculateDistanceFee(1501)
        val large2 = deliveryFee.calculateDistanceFee(1499) 

        assertEquals("error in calculateDistanceFee", minus.message)
        assertEquals(200, zero)
        assertEquals(200, minimum)
        assertEquals(200, middle)
        assertEquals(200, just)
        assertEquals(400, large)
        assertEquals(300, large2)
    }

}