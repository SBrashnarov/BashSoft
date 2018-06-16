package Repository;

import IO.OutputWriter;
import StaticData.ExceptionMessages;
import contracts.DataFilter;

import java.util.Map;
import java.util.function.Predicate;

public class RepositoryFilter implements DataFilter {

    public void printFilteredStudents(
            Map<String, Double> studentsWithMarks,
            String filterType,
            Integer numberOfStudents) {

        Predicate<Double> filter = createFilter(filterType);

        if (filter == null) {
            OutputWriter.displayException(ExceptionMessages.INVALID_FILTER);
            return;
        }

        int studentsCount = 0;
        for (String student : studentsWithMarks.keySet()) {
            if (studentsCount == numberOfStudents) {
                break;
            }

            Double mark = studentsWithMarks.get(student);

            if (filter.test(mark)) {
                OutputWriter.printStudent(student, mark);
                studentsCount++;
            }
        }

    }
    private Predicate<Double> createFilter(String filterType) {
        switch (filterType){
            case "excellent":
                return mark -> mark >= 5.0;
            case "average":
                return mark -> 3.5 <= mark && mark < 5.0;
            case "poor":
                return mark -> mark < 3.5;
            default:
                return null;
        }
    }
}
