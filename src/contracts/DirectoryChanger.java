package contracts;

public interface DirectoryChanger {

    void changeCurrentDirRelativePath(String relativePath);

    void changeCurrentDirAbsolutePath(String absolutePath);
}
