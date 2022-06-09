/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package testaw10;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import java.time.Duration;

public class Test extends Simulation {

  HttpProtocolBuilder httpProtocol =
      http
          // Here is the root for all relative URLs
          .baseUrl("http://localhost:8080")
          // Here are the common headers
          .acceptHeader("application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        //   .doNotTrackHeader("1")
          .acceptLanguageHeader("en-US,en;q=0.5")
          .acceptEncodingHeader("gzip, deflate, br")
          .userAgentHeader(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");

  // A scenario is a chain of requests and pauses
  ScenarioBuilder scn =
      scenario("Scenario Name")
          .exec(http("all_products_1").get("/products"))
          // Note that Gatling has recorded real time pauses
          // 13284888
          // 13044003
          // 21412
          .pause(2)
          .exec(http("product_1").get("/products/13284888"))
          .pause(Duration.ofMillis(200))
          .exec(http("product_2").get("/products/13122135"))
          .pause(Duration.ofMillis(200))
          .exec(http("getcart_1").get("/cart"))
          .pause(Duration.ofMillis(500))
          .exec(http("addcart_1").post("/cart/13284888"))
          .pause(Duration.ofMillis(500))
          .exec(http("addcart_2").post("/cart/13122135"))
          .pause(Duration.ofMillis(500))
          .exec(http("addcart_3").post("/cart/10020951649181"))
          .pause(Duration.ofMillis(300))
          //productNum=22
          .exec(http("modify_1").put("/cart/13284888").queryParam("productNum", "22"))
          .pause(Duration.ofMillis(500))
          .exec(http("delete_1").delete("/cart/13122135"))
          .pause(Duration.ofMillis(1500))
          .exec(http("getcart_2").get("/cart"))
          .pause(Duration.ofMillis(150))
          .exec(http("checkout_1").get("/checkout"))
          .pause(Duration.ofMillis(1500))
          .exec(http("getcart_2").get("/cart"));
        //   .pause(Duration.ofMillis(200))
        //   .exec(http("product_3").get("/products/21412"));
        //   .pause(Duration.ofMillis(200))
        //   .exec(http("request_3").get("/computers/6"))
        //   .pause(3)
        //   .exec(http("request_4").get("/"))
        //   .pause(2)
        //   .exec(http("request_5").get("/computers?p=1"))
        //   .pause(Duration.ofMillis(670))
        //   .exec(http("request_6").get("/computers?p=2"))
        //   .pause(Duration.ofMillis(629))
        //   .exec(http("request_7").get("/computers?p=3"))
        //   .pause(Duration.ofMillis(734))
        //   .exec(http("request_8").get("/computers?p=4"))
        //   .pause(5)
        //   .exec(http("request_9").get("/computers/new"))
        //   .pause(1)
        //   .exec(
        //       http("request_10")
        //           // Here's an example of a POST request
        //           .post("/computers")
        //           // Note the triple double quotes: used in Scala for protecting a whole chain of
        //           // characters (no need for backslash)
        //           .formParam("name", "Beautiful Computer")
        //           .formParam("introduced", "2012-05-30")
        //           .formParam("discontinued", "")
        //           .formParam("company", "37"));

  {
    setUp(scn.injectOpen(atOnceUsers(1000)).protocols(httpProtocol));
  }
}
