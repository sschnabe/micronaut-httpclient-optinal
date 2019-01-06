package io.micronaut.issue;

import java.util.Optional;

import io.micronaut.http.annotation.Controller;

@Controller
public class HelloController implements HelloApi {

	@Override
	public Optional<HelloValue> hello(String name) {
		return name == null
				? Optional.empty()
				: Optional.of(new HelloValue(name));
	}
}