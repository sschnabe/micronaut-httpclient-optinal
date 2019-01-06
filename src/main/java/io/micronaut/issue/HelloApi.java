package io.micronaut.issue;

import java.util.Optional;

import javax.annotation.Nullable;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;

public interface HelloApi {

	@Get(uri = "/hello/{name}", produces = MediaType.APPLICATION_JSON)
	Optional<HelloValue> hello(@Nullable String name);
}