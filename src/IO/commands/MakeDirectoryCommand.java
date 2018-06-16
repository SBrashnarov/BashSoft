package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public class MakeDirectoryCommand extends Command implements Executable {

    public MakeDirectoryCommand(String input,
                                String[] data,
                                Database repository,
                                ContentComparer tester,
                                DirectoryManager ioManager,
                                AsynchDownloader downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String folderName = data[1];
        this.getIoManager().createDirectoryInCurrentDirectory(folderName);
    }
}
