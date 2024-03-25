package thale.info

import io.micrometer.core.instrument.MeterRegistry
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.apache.http.config.Registry
import org.junit.jupiter.api.Test

@QuarkusTest
class GreetingResourceTest {


    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/hello")
          .then()
             .statusCode(200)
    }

}