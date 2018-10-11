package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.DirectoryManager;
import exceptions.InvalidInputException;


@Alias("ls")
public class TraverseFoldersCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public TraverseFoldersCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 1) {
            this.ioManager.traverseDirectory(0);
        }

        if (data.length == 2) {
            int depth = Integer.parseInt(data[1]);
            this.ioManager.traverseDirectory(depth);
        }
    }
}
