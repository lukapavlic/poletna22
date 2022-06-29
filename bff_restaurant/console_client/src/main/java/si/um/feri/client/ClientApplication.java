package si.um.feri.client;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import si.um.feri.client.domain.Customer;
import si.um.feri.client.domain.Order;
import si.um.feri.client.domain.Restaurant;

@SpringBootApplication
public class ClientApplication {

	private static final Logger log = Logger.getLogger(ClientApplication.class.toString());

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			log.info("Sending posts...");

			List.of(new Customer("Janez", "Maribor"), new Customer("Mirko", "Poljčane"), new Customer("Tona", "Ptuj"))
					.forEach(c -> restTemplate.postForObject("http://localhost:8081/customers", c, Customer.class));

			List.of(new Restaurant("Baščaršija", "Maribor",
					Map.ofEntries(new AbstractMap.SimpleEntry<String, Double>("Čevapi", 10.0),
							new AbstractMap.SimpleEntry<String, Double>("Plošča", 18.0),
							new AbstractMap.SimpleEntry<String, Double>("Pleskavica", 12.0))),
					new Restaurant("Jaka&Janez", "Maribor",
							Map.ofEntries(new AbstractMap.SimpleEntry<String, Double>("Hamburger", 12.0),
									new AbstractMap.SimpleEntry<String, Double>("Steak", 20.0),
									new AbstractMap.SimpleEntry<String, Double>("Perutničke", 11.0))),
					new Restaurant("Pepa", "Maribor",
							Map.ofEntries(new AbstractMap.SimpleEntry<String, Double>("Pizza klasika", 11.0),
									new AbstractMap.SimpleEntry<String, Double>("Pizza pekoča", 13.0),
									new AbstractMap.SimpleEntry<String, Double>("Pizza sir", 10.0))))
					.forEach(r -> restTemplate.postForObject("http://localhost:8082/restaurants", r, Restaurant.class));

			ResponseEntity<Customer[]> customers = restTemplate.getForEntity("http://localhost:8081/customers",
					Customer[].class);
			ResponseEntity<Restaurant[]> restaurants = restTemplate.getForEntity("http://localhost:8082/restaurants",
					Restaurant[].class);
			Arrays.asList(customers.getBody()).forEach(c -> {
				Arrays.asList(restaurants.getBody()).forEach(r -> {
					Random generator = new Random();
					Object[] values = r.getMenu().keySet().toArray();
					String randomValue = (String) values[generator.nextInt(values.length)];
					List.of(new Order(r.getId(), c.getId(), randomValue))
							.forEach(o -> restTemplate.postForObject("http://localhost:8083/orders", o, Order.class));
				});
			});

			log.info("Post completed");

			ResponseEntity<Customer[]> customersGet = restTemplate
					.getForEntity("http://localhost:8081/customers?query=Tona", Customer[].class);
			int tonaId = customersGet.getBody()[0].getId();

			ResponseEntity<Restaurant[]> restaurantsGet = restTemplate
					.getForEntity("http://localhost:8082/restaurants?query=Baščaršija", Restaurant[].class);
			int restavracijaId = restaurantsGet.getBody()[0].getId();

			ResponseEntity<Order[]> ordersGet = restTemplate
					.getForEntity("http://localhost:8083/orders/customers/" + tonaId, Order[].class);

			Arrays.asList(ordersGet.getBody()).stream().filter(o -> o.getRestaurantId() == restavracijaId)
					.forEach(o -> log.info("Tona je v Baščaršiji jedel: " + o.getOrderFromMenu()));

		};
	}

}
