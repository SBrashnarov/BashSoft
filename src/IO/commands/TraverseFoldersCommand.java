package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public class TraverseFoldersCommand extends Command implements Executable {

    public TraverseFoldersCommand(String input,
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
        if (data.length != 1 && data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 1) {
            this.getIoManager().traverseDirectory(0);
        }

        if (data.length == 2) {
            int depth = Integer.parseInt(data[1]);
            this.getIoManager().traverseDirectory(depth);
        }
    }
}
