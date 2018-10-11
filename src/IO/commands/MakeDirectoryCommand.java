package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.DirectoryManager;
import exceptions.InvalidInputException;

@Alias("mkdir")
public class MakeDirectoryCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public MakeDirectoryCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String folderName = data[1];
        this.ioManager.createDirectoryInCurrentDirectory(folderName);
    }
}
