package pl.codecouple.kubernetes.browser.presentation.files;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.NonPhysicalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

public class KubernetesResourceVirtualFileSystem extends DeprecatedVirtualFileSystem implements NonPhysicalFileSystem {

	private static final String PROTOCOL = "k8s";

	public static KubernetesResourceVirtualFileSystem getInstance() {
		return (KubernetesResourceVirtualFileSystem) VirtualFileManager.getInstance().getFileSystem(PROTOCOL);
	}

	@Override
	@NotNull
	public String getProtocol() {
		return PROTOCOL;
	}

	@Override
	public VirtualFile findFileByPath(@NotNull String path) {
		return null;
	}

	@Override
	public void refresh(boolean asynchronous) {
	}

	@Override
	public VirtualFile refreshAndFindFileByPath(@NotNull String path) {
		return null;
	}

	@Override
	public void fireContentsChanged(Object requestor, @NotNull VirtualFile file, long oldModificationStamp) {
		super.fireContentsChanged(requestor, file, oldModificationStamp);
	}

	@Override
	protected void fireBeforeFileDeletion(Object requestor, @NotNull VirtualFile file) {
		super.fireBeforeFileDeletion(requestor, file);
	}

	@Override
	protected void fireFileDeleted(Object requestor, @NotNull VirtualFile file, @NotNull String fileName, VirtualFile parent) {
		super.fireFileDeleted(requestor, file, fileName, parent);
	}

	@Override
	protected void fireBeforeContentsChange(Object requestor, @NotNull VirtualFile file) {
		super.fireBeforeContentsChange(requestor, file);
	}
}
