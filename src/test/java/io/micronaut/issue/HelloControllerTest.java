package io.micronaut.issue;


import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
class HelloControllerTest {

	@Inject
	HelloClient declarativeClient;
	@Inject @Client("/")
	RxHttpClient httpClient;

	@Test @DisplayName("Declarative client with optional value present")
	void declarativeWithValue() {
		Optional<HelloValue> optional = declarativeClient.hello("Fred");
		Assertions.assertTrue(optional.isPresent());
		Assertions.assertEquals(new HelloValue("Fred"), optional.get());
	}

	@Test @DisplayName("Declarative client without optional value present")
	void declarativeWithoutValue() {
		Optional<HelloValue> optional = declarativeClient.hello(null);
		Assertions.assertFalse(optional.isPresent());
	}

	@Test @DisplayName("Http client with optional value present")
	void httpWithValue() {
		Optional optional = httpClient
				.retrieve(HttpRequest.GET("hello/Fred"), Argument.of(Optional.class, HelloValue.class))
				.blockingFirst();
		Assertions.assertTrue(optional.isPresent());
		Assertions.assertEquals(new HelloValue("Fred"), optional.get());
	}

	@Test @DisplayName("Http client without optional value present")
	void httpWithoutValue() {
		Assertions.assertThrows(HttpClientResponseException.class, () -> httpClient
				.retrieve(HttpRequest.GET("hello"), Argument.of(Optional.class, HelloValue.class))
				.blockingFirst());
	}
}