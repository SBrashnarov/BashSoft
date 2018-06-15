package IO.commands;

import IO.IOManager;
import IO.OutputWriter;
import Judge.Tester;
import Network.DownloadManager;
import Repository.StudentsRepository;
import exceptions.InvalidInputException;

public class DropDatabaseCommand extends Command {

    public DropDatabaseCommand(String input,
                               String[] data,
                               StudentsRepository repository,
                               Tester tester, IOManager ioManager,
                               DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1) {
            throw new InvalidInputException(this.getInput());
        }

        this.getRepository().undloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}
