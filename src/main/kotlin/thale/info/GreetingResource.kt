package thale.info

import io.micrometer.core.instrument.MeterRegistry
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

var times = 0

@Path("/")
class GreetingResource(val registry: MeterRegistry) {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {

        registry.counter("greeting_counter").increment()

        return "Welcome! I respond from pod ${getHostName()}. Currently this endpoint was called ${++times} times."
    }

    private fun getHostName() = System.getenv("HOSTNAME") ?: "N/A"

    @GET
    @Path("/env")
    @Produces(MediaType.TEXT_PLAIN)
    fun printEnv() = """
        Keys: ${System.getenv().keys.joinToString { ", " }},
        Values: ${System.getenv().values.joinToString { ", " }},
        Env: ${System.getenv().map { "${it.key} = ${it.value}" }.joinToString { ", " }}
    """.trimIndent()

}