package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public class ShowCourseCommand extends Command implements Executable {

    public ShowCourseCommand(String input,
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
        if (data.length != 2 && data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 2) {
            String courseName = data[1];
            this.getRepository().getStudentsByCourse(courseName);
        }

        if (data.length == 3) {
            String courseName = data[1];
            String userName = data[2];
            this.getRepository().getStudentMarkInCourse(courseName, userName);
        }
    }
}
