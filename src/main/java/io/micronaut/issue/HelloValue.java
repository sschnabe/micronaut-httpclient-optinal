package io.micronaut.issue;

import java.util.Objects;

public class HelloValue {

	private String value;

	public HelloValue() {}

	public HelloValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof HelloValue
				&& Objects.equals(value, HelloValue.class.cast(other).value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}