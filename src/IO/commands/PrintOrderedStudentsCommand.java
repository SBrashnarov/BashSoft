package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.Database;
import exceptions.InvalidInputException;

@Alias("order")
public class PrintOrderedStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintOrderedStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3 && data.length != 4) {
            throw new InvalidInputException(this.getInput());
        }

        String course = data[1];
        String compareType = data[2].toLowerCase();

        if (data.length == 3) {
            this.repository.orderAndTake(course, compareType);
            return;
        }

        Integer numberOfStudents = Integer.parseInt(data[3]);
        this.repository.orderAndTake(course, compareType, numberOfStudents);
    }
}
