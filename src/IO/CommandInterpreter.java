package IO;

import IO.commands.*;
import contracts.*;
import exceptions.InvalidInputException;

import java.io.IOException;

public class CommandInterpreter implements Interpreter {

    private ContentComparer tester;
    private Database repository;
    private AsynchDownloader downloadManager;
    private DirectoryManager inputOutputManager;

    public CommandInterpreter(ContentComparer tester,
                              Database repository,
                              AsynchDownloader downloadManager,
                              DirectoryManager inputOutputManager) {
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.inputOutputManager = inputOutputManager;
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();

        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }
    }

    private Executable parseCommand(String input, String[] data, String command) {
        switch (command) {
            case "open":
                return new OpenFileCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "mkdir":
                return new MakeDirectoryCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "ls":
                return new TraverseFoldersCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "cmp":
                return new CompareFilesCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "cdrel":
                return new ChangeRelativePathCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "cdabs":
                return new ChangeAbsolutePathCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "readdb":
                return new ReadDatabaseCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "dropdb":
                return new DropDatabaseCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "filter":
                return new PrintFilteredStudentsCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "order":
                return new PrintOrderedStudentsCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "download":
                return new DownloadFileCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "downloadasynch":
                return new DownloadFileAsynchCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "help":
                return new GetHelpCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "show":
                return new ShowCourseCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "display":
                return new DisplayCommand(input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            default:
                throw new InvalidInputException(input);
        }
    }
}
