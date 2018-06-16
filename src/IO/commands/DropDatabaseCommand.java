package IO.commands;

import IO.OutputWriter;
import contracts.*;
import exceptions.InvalidInputException;

public class DropDatabaseCommand extends Command implements Executable {

    public DropDatabaseCommand(String input,
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
        if (data.length != 1) {
            throw new InvalidInputException(this.getInput());
        }

        this.getRepository().unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}
