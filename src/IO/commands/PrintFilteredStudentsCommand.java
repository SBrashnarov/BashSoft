package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.Database;
import exceptions.InvalidInputException;

@Alias("filter")
public class PrintFilteredStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintFilteredStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3 && data.length != 4) {
            throw new InvalidInputException(this.getInput());
        }

        String course = data[1];
        String filter = data[2].toLowerCase();

        if (data.length == 3) {
            this.repository.filterAndTake(course, filter);
            return;
        }

        Integer numberOfStudents = Integer.parseInt(data[3]);

        this.repository.filterAndTake(course, filter, numberOfStudents);
    }
}
