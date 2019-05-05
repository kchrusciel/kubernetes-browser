package pl.codecouple.kubernetes.browser.domain.dto;

import java.util.Objects;

public class NamespaceDto extends NamedObjectDto {

	private final boolean selected;

	public NamespaceDto(final String name, final boolean selected) {
		super(name);
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean equals(final Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		final NamespaceDto that = (NamespaceDto) o;
		return selected == that.selected;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), selected);
	}
}
