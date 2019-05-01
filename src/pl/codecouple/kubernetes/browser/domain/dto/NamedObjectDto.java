package pl.codecouple.kubernetes.browser.domain.dto;

import java.util.Objects;

abstract class NamedObjectDto {

	private final String name;

	NamedObjectDto(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		final NamedObjectDto that = (NamedObjectDto) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
