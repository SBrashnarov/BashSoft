package IO;

import java.io.File;
import java.util.LinkedList;
import StaticData.ExceptionMessages;
import StaticData.SessionData;
import contracts.DirectoryManager;
import exceptions.InvalidFileNameException;
import exceptions.InvalidPathException;

public class IOManager implements DirectoryManager {

    private static final String FILE_SEPARATOR = File.separator;

    public void traverseDirectory(int depth) {
        LinkedList<File> subFolders = new LinkedList<>();

        String path = getCurrentDirectoryPath();
        int initialIndentation = path.split(FILE_SEPARATOR).length;

        File root = new File(path);
        subFolders.add(root);

        while (subFolders.size() > 0) {
            File currentFolder = subFolders.removeFirst();
            int currentIndentation = currentFolder.toString().split(FILE_SEPARATOR)
                    .length - initialIndentation;

            if (depth - currentIndentation < 0) {
                break;
            }

            OutputWriter.writeMessageOnNewLine(currentFolder.toString());

            if (currentFolder.listFiles() != null) {
                for (File file : currentFolder.listFiles()) {
                    if (file.isDirectory()) {
                        subFolders.add(file);
                    } else {
                        int indexOfLastSlash = file.toString().lastIndexOf(FILE_SEPARATOR);
                        for (int i = 0; i < indexOfLastSlash; i++) {
                            OutputWriter.writeMessage(" ");
                        }

                        OutputWriter.writeMessageOnNewLine(file.getName());
                    }
                }
            }
        }
    }

    public void createDirectoryInCurrentDirectory(String name) {
        String path = getCurrentDirectoryPath() + FILE_SEPARATOR + name;
        File file = new File(path);
        boolean wasDirMade = file.mkdir();

        if (!wasDirMade) {
            throw new InvalidFileNameException();
        }
    }

    private String getCurrentDirectoryPath() {
        return SessionData.currentPath;
    }

    public void changeCurrentDirRelativePath(String relativePath) {
        if (relativePath.equals("..")) {
            try {
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.lastIndexOf(FILE_SEPARATOR);
                SessionData.currentPath = currentPath.substring(0, indexOfLastSlash);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException(ExceptionMessages.INVALID_PATH);
            }
        } else {
            String currentPath = SessionData.currentPath;
            currentPath += FILE_SEPARATOR + relativePath;
            changeCurrentDirAbsolutePath(currentPath);
        }
    }

    public void changeCurrentDirAbsolutePath(String absolutePath) throws InvalidPathException {
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new InvalidPathException();
        }

        SessionData.currentPath = absolutePath;
    }
}
