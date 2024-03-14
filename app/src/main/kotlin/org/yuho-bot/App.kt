/*
 doc
 server https://ktor.io/docs/create-server.html#servlet
 dependencies https://ktor.io/docs/server-dependencies.htmlcd

 */
package appkt

import serverkt.Server

fun main() {
    val server = Server()
    server.deliveryFeeServerConfig()
}
