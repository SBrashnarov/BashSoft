package IO.commands;

import StaticData.SessionData;
import annotations.Alias;
import exceptions.InvalidInputException;

import java.awt.*;
import java.io.File;

@Alias("open")
public class OpenFileCommand extends Command {

    public OpenFileCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileName = data[1];
        String filePath = SessionData.currentPath + File.separator + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }
}
