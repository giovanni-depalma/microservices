# spring-microservice

In this guide we will route all of our requests to https://httpbin.org/

To get started, create a new Bean of type RouteLocator in Application.java.

src/main/java/gateway/Application.java

```java
@Bean
public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes().build();
}
```

Let’s create a route that routes a request to https://httpbin.org/get when a request is made to the Gateway at /get. In our configuration of this route we will add a filter that will add the request header Hello with the value World to the request before it is routed.

```java
@Bean
public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(p -> p
            .path("/get")
            .filters(f -> f.addRequestHeader("Hello", "World"))
            .uri("http://httpbin.org:80"))
        .route(p -> p
            .host("*.hystrix.com")
            .filters(f -> f.hystrix(config -> config.setName("mycmd")))
            .uri("http://httpbin.org:80")).
        .build();
}
```

```
curl http://localhost:8080/get
```

Now lets do something a little more interesting. Since the services behind the Gateway could potentially behave poorly effecting our clients we might want to wrap the routes we create in circuit breakers. You can do this in the Spring Cloud Gateway using Hystrix. This is implemented via a simple filter that you can add to your requests. Lets create another route to demonstrate this.

In this example we will leverage HTTPBin’s delay API that waits a certain number of seconds before sending a response. Since this API could potentially take a long time to send its response we can wrap the route that uses this API in a HystrixCommand. Add a new route to our RouteLocator object that looks like the following

```java
@Bean
public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
            .route(p -> p
                .path("/get")
                .filters(f -> f.addRequestHeader("Hello", "World"))
                .uri("http://httpbin.org:80"))
            .route(p -> p
                .host("*.hystrix.com")
                .filters(f -> f.hystrix(config -> config.setName("mycmd")))
                .uri("http://httpbin.org:80")).
            build();
        //@formatter:on
}
```

```
$ curl --dump-header - --header 'Host: www.hystrix.com' http://localhost:8080/delay/3
```

```java
@Bean
public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
        .route(p -> p
            .path("/get")
            .filters(f -> f.addRequestHeader("Hello", "World"))
            .uri("http://httpbin.org:80"))
        .route(p -> p
            .host("*.hystrix.com")
            .filters(f -> f.hystrix(config -> config
                .setName("mycmd")
                .setFallbackUri("forward:/fallback")))
            .uri("http://httpbin.org:80"))
        .build();
        //@formatter:on
}
```

Now when the Hystrix wrapped route times out it will call /fallback in the Gateway app. Lets add the /fallback endpoint to our application.

```java
@RequestMapping("/fallback")
public Mono<String> fallback() {
  return Mono.just("fallback");
}
```

## CircuitBreaker
https://martinfowler.com/bliki/CircuitBreaker.html