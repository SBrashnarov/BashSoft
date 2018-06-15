package IO.commands;

import IO.IOManager;
import Judge.Tester;
import Network.DownloadManager;
import Repository.StudentsRepository;
import exceptions.InvalidInputException;

public class CompareFilesCommand extends Command {

    public CompareFilesCommand(String input,
                                  String[] data,
                                  StudentsRepository repository,
                                  Tester tester, IOManager ioManager,
                                  DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        String firstPath = data[1];
        String secondPath = data[2];
        this.getTester().compareContent(firstPath, secondPath);
    }
}


