package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public class DownloadFileAsynchCommand extends Command {

    public DownloadFileAsynchCommand(String input,
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

        String fileUrl = data[1];
        this.getDownloadManager().downloadOnNewThread(fileUrl);
    }
}
