package contracts;

import dataStructures.SimpleSortedList;

import java.util.Comparator;

public interface Requester {

    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> comparator);

    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> comparator);
}
