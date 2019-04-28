package pl.codecouple.kubernetes.browser.domain.dto;

public class NamespaceDto {

	private final String name;

	public NamespaceDto(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
