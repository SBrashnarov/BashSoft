package IO.commands;

import IO.OutputWriter;
import annotations.Alias;
import annotations.Inject;
import contracts.Course;
import contracts.Database;
import contracts.Student;
import dataStructures.SimpleSortedList;
import exceptions.InvalidInputException;

import java.util.Comparator;

@Alias("display")
public class DisplayCommand extends Command {

    @Inject
    private Database repository;

    public DisplayCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        String entityToDisplay = data[1];
        String sortType = data[2];
        if (entityToDisplay.equals("students")) {
            Comparator<Student> studentComparator = this.createStudentComparator(sortType);

            SimpleSortedList<Student> students = this.repository.getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(students.joinWith(System.lineSeparator()));
        } else if (entityToDisplay.equals("courses")) {
            Comparator<Course> courseComparator = this.createCourseComparator(sortType);

            SimpleSortedList<Course> courses = this.repository.getAllCoursesSorted(courseComparator);
            OutputWriter.writeMessageOnNewLine(courses.joinWith(System.lineSeparator()));
        }
    }

    private Comparator<Student> createStudentComparator(String sortType) {
        switch (sortType) {
            case "ascending":
                return Comparable::compareTo;
            case "descending":
                return Comparator.reverseOrder();
            default:
                throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Course> createCourseComparator(String sortType) {
        switch (sortType) {
            case "ascending":
                return Comparable::compareTo;
            case "descending":
                return Comparator.reverseOrder();
            default:
                throw new InvalidInputException(this.getInput());
        }
    }
}
