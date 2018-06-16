package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public class PrintFilteredStudentsCommand extends Command implements Executable {

    public PrintFilteredStudentsCommand(String input,
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
        if (data.length != 3 && data.length != 4) {
            throw new InvalidInputException(this.getInput());
        }

        String course = data[1];
        String filter = data[2].toLowerCase();

        if (data.length == 3) {
            this.getRepository().filterAndTake(course, filter);
            return;
        }

        Integer numberOfStudents = Integer.parseInt(data[3]);

        this.getRepository().filterAndTake(course, filter, numberOfStudents);
    }
}
