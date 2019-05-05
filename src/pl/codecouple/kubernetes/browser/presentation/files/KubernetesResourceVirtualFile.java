package pl.codecouple.kubernetes.browser.presentation.files;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.vfs.VirtualFileSystem;

public class KubernetesResourceVirtualFile extends AbstractKubernetesResourceVirtualFile {

	private volatile byte[] content;

	public KubernetesResourceVirtualFile(final String filePath, final VirtualFileSystem fileSystem) {
		super(filePath, fileSystem);
	}

	public KubernetesResourceVirtualFile(final String filePath, final VirtualFileSystem fileSystem, final byte[] content) {
		super(filePath, fileSystem);
		this.content = content;
	}

	@NotNull
	@Override
	public byte[] contentsToByteArray() throws IOException {
		return content;
	}
}
