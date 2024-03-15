// https://kotlinlang.org/docs/serialization.html#serialize-and-deserialize-json
package serverkt

// server
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.Serializable

// Json
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

// http
import io.ktor.http.HttpStatusCode

// validation
// import kotlinx.serialization.SerializationException
import io.ktor.server.plugins.BadRequestException
// import io.ktor.server.application.*
// import io.ktor.server.plugins.requestvalidation.*

// my modules
import reqDataVerifyKt.ReqDataVerify
import resDataVerifyKt.ResDataVerify

// for receive & respond
import io.ktor.server.request.* 

// @Serializable
// data class Data(val a: Int, val b: String)

@Serializable
data class FeeCalcRequest(
    val cart_value: Int, // cent
    val delivery_distance: Int, //meter
    val number_of_items: Int,
    val time: String // UTC(ISO 8601)
)


@Serializable
data class FeecCalcResponse(
    val delivery_fee: Int
)

class Server {
    
    fun deliveryFeeServerConfig() {
        embeddedServer(Netty, port = 8080) {

            install(ContentNegotiation){
                json(Json{ignoreUnknownKeys = false})
            }

            routing {
                post("/delivery-fee") {
                    // val parameters = call.receive<Data>()
                    // println(parameters)
                    // call.respondText("Hello, world!")
                    // // call.respondText(parameters)
                    try {
                        // Request Data verification
                        val request: FeeCalcRequest = call.receive<FeeCalcRequest>()
                        ReqDataVerify().invalidateRequest(request)
        
                        // Fee calculation
                        val finalFee = ResDataVerify().calculateDeliveryFee(request)
        
                        //Response to Clients
                        call.respond(FeecCalcResponse(finalFee))
        
                    } catch (e: BadRequestException) {                   
                        call.respond(HttpStatusCode.BadRequest, "400: Invalid request format\nType of values are wrong or invalid key included\n\nExample of expected request:\n{\"cart_value\": 10, \"delivery_distance\": 1000, \"number_of_items\": 5, \"time\": \"2024-01-01T12:00:00Z\"}")
                        // e.printStackTrace()
                    } catch (e: Exception) {
                        call.respond(HttpStatusCode.InternalServerError, "500: Internal Server Errorr")
                        println(e)
                        // e.printStackTrace()
                    }


                }
            }
        }.start(wait = true)
    }

}