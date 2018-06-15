package IO.commands;

import IO.IOManager;
import Judge.Tester;
import Network.DownloadManager;
import Repository.StudentsRepository;
import exceptions.InvalidInputException;

public class TraverseFoldersCommand extends Command {

    public TraverseFoldersCommand(String input,
                                  String[] data,
                                  StudentsRepository repository,
                                  Tester tester, IOManager ioManager,
                                  DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 1) {
            this.getIoManager().traverseFolder(0);
        }

        if (data.length == 2) {
            int depth = Integer.parseInt(data[1]);
            this.getIoManager().traverseFolder(depth);
        }
    }
}
