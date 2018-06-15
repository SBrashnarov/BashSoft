package IO.commands;

import IO.IOManager;
import Judge.Tester;
import Network.DownloadManager;
import Repository.StudentsRepository;
import StaticData.SessionData;
import exceptions.InvalidInputException;

import java.awt.*;
import java.io.File;

public class OpenFileCommand extends Command {

    public OpenFileCommand(String input,
                           String[] data,
                           StudentsRepository repository,
                           Tester tester,
                           IOManager ioManager,
                           DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }
}
