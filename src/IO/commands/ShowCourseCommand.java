package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.Database;
import exceptions.InvalidInputException;


@Alias("show")
public class ShowCourseCommand extends Command {

    @Inject
    private Database repository;

    public ShowCourseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2 && data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 2) {
            String courseName = data[1];
            this.repository.getStudentsByCourse(courseName);
        }

        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            this.repository.getStudentMarkInCourse(courseName, userName);
        }
    }
}
