package appkt.calculateTotalDeliveryFeeKt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class calculateItemSurchargeTest {
    val deliveryFee = DeliveryFee()


    @Test fun `If the number of items is five or more  an additional 50 cent surcharge is added for each item above and including the fifth item An extra bulk fee applies for more than 12 items of 120cent`() {
        // calculateItemSurcharge(number_of_items)
        val minus = assertFailsWith<Exception> {
            deliveryFee.calculateItemSurcharge(-100000)
        }   
        val zero = deliveryFee.calculateItemSurcharge(0)
        val small = deliveryFee.calculateItemSurcharge(1)
        val just = deliveryFee.calculateItemSurcharge(5)
        val large = deliveryFee.calculateItemSurcharge(10)
        val twelve = deliveryFee.calculateItemSurcharge(12)
        val superLarge = deliveryFee.calculateItemSurcharge(14)

        assertEquals("error in calculateItemSurcharge", minus.message)
        assertEquals(0, zero)
        assertEquals(0, small)
        assertEquals(50, just)
        assertEquals(300, large)
        assertEquals(400, twelve)
        assertEquals(620, superLarge)
      
    } 

}