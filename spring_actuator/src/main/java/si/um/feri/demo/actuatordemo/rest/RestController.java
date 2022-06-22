package si.um.feri.demo.actuatordemo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.um.feri.demo.actuatordemo.jms.JmsProducer;
import java.util.Arrays;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger log = LoggerFactory.getLogger(RestController.class);

	@Autowired
	JmsProducer jmsProducer;

	@GetMapping("/items")
	public @ResponseBody Iterable<String> getAll() {
		return Arrays.asList("prvi","drugi","tretji");
	}

	@GetMapping("/jms")
	public @ResponseBody void sendMsg() {
		jmsProducer.sendMessage("Zdravo!");
	}

}