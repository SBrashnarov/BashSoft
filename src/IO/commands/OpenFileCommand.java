package IO.commands;

import StaticData.SessionData;
import contracts.*;
import exceptions.InvalidInputException;

import java.awt.*;
import java.io.File;

public class OpenFileCommand extends Command implements Executable {

    public OpenFileCommand(String input,
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

        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }
}
