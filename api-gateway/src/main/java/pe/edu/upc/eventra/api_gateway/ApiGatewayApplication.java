package pe.edu.upc.eventra.api_gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class ApiGatewayApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return factory -> factory.setPort(8080);
	}
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				// Rutas para user-service
				.route(r -> r.path("/user-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/auth/login").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/auth/register").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser/{id}").and().method(HttpMethod.PUT).uri("lb://user-service"))
				.route(r -> r.path("/api/typeofuser/{id}").and().method(HttpMethod.DELETE).uri("lb://user-service"))
				.route(r -> r.path("/api/users").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/users").and().method(HttpMethod.POST).uri("lb://user-service"))
				.route(r -> r.path("/api/users/{id}").and().method(HttpMethod.PUT).uri("lb://user-service"))
				.route(r -> r.path("/api/users/{id}").and().method(HttpMethod.DELETE).uri("lb://user-service"))
				.route(r -> r.path("/api/users/{id}").and().method(HttpMethod.GET).uri("lb://user-service"))
				.route(r -> r.path("/api/users/email/{email}").and().method(HttpMethod.GET).uri("lb://user-service"))
				// Rutas para tickets-service
				.route(r -> r.path("/tickets-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://tickets-service"))
				.route(r -> r.path("/api/tickets").and().method(HttpMethod.POST).uri("lb://tickets-service"))
				.route(r -> r.path("/api/tickets").and().method(HttpMethod.GET).uri("lb://tickets-service"))
				.route(r -> r.path("/api/tickets/{id}").and().method(HttpMethod.GET).uri("lb://tickets-service"))
				.route(r -> r.path("/api/tickets/{id}").and().method(HttpMethod.PUT).uri("lb://tickets-service"))
				.route(r -> r.path("/api/tickets/{id}").and().method(HttpMethod.DELETE).uri("lb://tickets-service"))
				.route(r -> r.path("/api/tickets/event/{eventId}").and().method(HttpMethod.GET).uri("lb://tickets-service"))
				// Rutas para reservations-service
				.route(r -> r.path("/reservations-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://reservations-service"))
				.route(r -> r.path("/api/reservations").and().method(HttpMethod.POST).uri("lb://reservations-service"))
				.route(r -> r.path("/api/reservations").and().method(HttpMethod.GET).uri("lb://reservations-service"))
				.route(r -> r.path("/api/reservations/{id}").and().method(HttpMethod.GET).uri("lb://reservations-service"))
				.route(r -> r.path("/api/reservations/{id}").and().method(HttpMethod.PUT).uri("lb://reservations-service"))
				.route(r -> r.path("/api/reservations/{id}").and().method(HttpMethod.DELETE).uri("lb://reservations-service"))
				// Rutas para events-service
				.route(r -> r.path("/events-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://events-service"))
				.route(r -> r.path("/api/events").and().method(HttpMethod.POST).uri("lb://events-service"))
				.route(r -> r.path("/api/events").and().method(HttpMethod.GET).uri("lb://events-service"))
				.route(r -> r.path("/api/events/{id}").and().method(HttpMethod.GET).uri("lb://events-service"))
				.route(r -> r.path("/api/events/{id}").and().method(HttpMethod.PUT).uri("lb://events-service"))
				.route(r -> r.path("/api/events/{id}").and().method(HttpMethod.DELETE).uri("lb://events-service"))
				.route(r -> r.path("/api/categoryevent").and().method(HttpMethod.POST).uri("lb://events-service"))
				.route(r -> r.path("/api/categoryevent").and().method(HttpMethod.GET).uri("lb://events-service"))
				.route(r -> r.path("/api/categoryevent/{id}").and().method(HttpMethod.PUT).uri("lb://events-service"))
				.route(r -> r.path("/api/categoryevent/{id}").and().method(HttpMethod.DELETE).uri("lb://events-service"))
				.route(r -> r.path("/api/events/title/{title}").and().method(HttpMethod.GET).uri("lb://events-service"))
				.route(r -> r.path("/api/events/category/{categoryId}").and().method(HttpMethod.GET).uri("lb://events-service"))
				.route(r -> r.path("/api/events/user/{userId}").and().method(HttpMethod.GET).uri("lb://events-service"))
				// Rutas para notification-service
				.route(r -> r.path("/notification-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://notification-service"))
				.route(r -> r.path("/api/notifications").and().method(HttpMethod.POST).uri("lb://notification-service"))
				.route(r -> r.path("/api/notifications").and().method(HttpMethod.GET).uri("lb://notification-service"))
				.route(r -> r.path("/api/notifications/{id}").and().method(HttpMethod.GET).uri("lb://notification-service"))
				.route(r -> r.path("/api/notifications/{id}").and().method(HttpMethod.PUT).uri("lb://notification-service"))
				.route(r -> r.path("/api/notifications/{id}").and().method(HttpMethod.DELETE).uri("lb://notification-service"))
				// Rutas para notification-service
				.route(r -> r.path("/payment-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/payments").and().method(HttpMethod.POST).uri("lb://payment-service"))
				.route(r -> r.path("/api/payments").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/payments/{id}").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/payments/{id}").and().method(HttpMethod.PUT).uri("lb://payment-service"))
				.route(r -> r.path("/api/payments/{id}").and().method(HttpMethod.DELETE).uri("lb://payment-service"))
				.route(r -> r.path("/api/refunds").and().method(HttpMethod.POST).uri("lb://payment-service"))
				.route(r -> r.path("/api/refunds").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/refunds/{id}").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/refunds/{id}").and().method(HttpMethod.PUT).uri("lb://payment-service"))
				.route(r -> r.path("/api/refunds/{id}").and().method(HttpMethod.DELETE).uri("lb://payment-service"))
				.route(r -> r.path("/api/statuses").and().method(HttpMethod.POST).uri("lb://payment-service"))
				.route(r -> r.path("/api/statuses").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/statuses/{id}").and().method(HttpMethod.GET).uri("lb://payment-service"))
				.route(r -> r.path("/api/statuses/{id}").and().method(HttpMethod.PUT).uri("lb://payment-service"))
				.route(r -> r.path("/api/statuses/{id}").and().method(HttpMethod.DELETE).uri("lb://payment-service"))
				.build();
	}
}

