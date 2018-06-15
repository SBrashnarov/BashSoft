package Models;

import IO.OutputWriter;
import StaticData.ExceptionMessages;
import exceptions.DuplicateEntryInStructureException;
import exceptions.InvalidStringException;
import exceptions.KeyNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String userName;
    private HashMap<String, Course> enrolledCourses;
    private HashMap<String, Double> marksByCourseName;

    public Student(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new HashMap<>();
        this.marksByCourseName = new HashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        if (userName == null || userName.trim().equals(" ")) {
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }

    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(this.marksByCourseName);
    }

    public void enrollInCourse(Course course) {
        if (this.enrolledCourses.containsKey(course.getName())) {
            throw new DuplicateEntryInStructureException(this.userName, course.getName());
        }
        this.enrolledCourses.put(course.getName(), course);
    }

    public void setMarksInCourse(String courseNmae, int...scores) {
        if (!this.enrolledCourses.containsKey(courseNmae)) {
            throw new KeyNotFoundException();
        }
        if (scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
        }
        double mark = calculateMark(scores);
        this.marksByCourseName.put(courseNmae, mark);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores)
                .sum() /
                (double) (Course.NUMBER_OF_TASKS_ON_EXAM * Course.MAX_SCORE_ON_EXAM_TASK);

        double mark = percentageOfSolvedExam * 4 + 2;
        return mark;
    }
}
