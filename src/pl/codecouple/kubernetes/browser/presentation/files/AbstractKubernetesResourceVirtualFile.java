package pl.codecouple.kubernetes.browser.presentation.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.codeInsight.daemon.OutsidersPsiFileSupport;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;

public abstract class AbstractKubernetesResourceVirtualFile extends VirtualFile {

	protected final String fileName;
	protected final String filePath;

	private final VirtualFile parent;
	private final VirtualFileSystem fileSystem;

	protected int modificationStamp = 0;

	protected AbstractKubernetesResourceVirtualFile(final String filePath, final VirtualFileSystem fileSystem) {
		this.filePath = filePath;
		this.fileSystem = fileSystem;
		File file = new File(filePath);
		this.fileName = file.getName();
		this.parent = null;

		OutsidersPsiFileSupport.markFile(this);
	}

	@NotNull
	@Override
	public String getName() {
		return fileName;
	}

	@NotNull
	@Override
	public VirtualFileSystem getFileSystem() {
		return fileSystem;
	}

	@NotNull
	@Override
	public String getPath() {
		return filePath;
	}

	@Override
	public boolean isWritable() {
		return true;
	}

	@Override
	public boolean isDirectory() {
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public VirtualFile getParent() {
		return parent;
	}

	@Override
	public VirtualFile[] getChildren() {
		return new VirtualFile[0];
	}

	@NotNull
	@Override
	public OutputStream getOutputStream(final Object o, final long l, final long l1) throws IOException {
		throw new RuntimeException();
	}

	@NotNull
	@Override
	public abstract byte[] contentsToByteArray() throws IOException;

	@Override
	public long getTimeStamp() {
		return modificationStamp;
	}

	@Override
	public long getModificationStamp() {
		return modificationStamp;
	}

	@Override
	public long getLength() {
		try {
			return contentsToByteArray().length;
		} catch (IOException e) {
			return 0;
		}
	}

	@Override
	public void refresh(final boolean b, final boolean b1, @Nullable final Runnable runnable) {
		if (runnable != null) {
			runnable.run();
		}
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return VfsUtilCore.byteStreamSkippingBOM(contentsToByteArray(), this);
	}
}
