package Repository;

import IO.OutputWriter;
import contracts.DataSorter;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorter implements DataSorter {

    public void printSortedStudents(
            Map<String, Double> courseData,
            String comparisonType,
            int numberOfStudents) {

        Comparator<Map.Entry<String, Double>> comparator =
                (x, y) -> {
                    double value1 = x.getValue();
                    double value2 = y.getValue();
                    return Double.compare(value1, value2);
                };

        List<String> sortedStudents = courseData.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }

        for (String student : sortedStudents) {
            OutputWriter.printStudent(student, courseData.get(student));
        }
    }

}
