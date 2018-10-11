package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.Database;
import exceptions.InvalidInputException;


@Alias("readdb")
public class ReadDatabaseCommand extends Command {

    @Inject
    private Database repository;

    public ReadDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileName = data[1];
        this.repository.loadData(fileName);
    }
}
