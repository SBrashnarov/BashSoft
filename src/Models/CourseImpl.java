package Models;

import contracts.Course;
import contracts.Student;
import exceptions.DuplicateEntryInStructureException;
import exceptions.InvalidStringException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CourseImpl implements Course {

    public static final int NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final int MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private HashMap<String, Student> studentsByName;

    public CourseImpl(String name) {
        this.setName(name);
        this.studentsByName = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(studentsByName);
    }

    public void enrollStudent(Student student) {
        if (this.studentsByName.containsKey(student.getUserName())) {
            throw new DuplicateEntryInStructureException(student.getUserName(), this.name);
        }
        this.studentsByName.put(student.getUserName(), student);
    }

    @Override
    public int compareTo(Course otherCourse) {
        return this.getName().compareTo(otherCourse.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }

    private void setName(String name) {
        if (name == null || name.trim().equals(" ")) {
            throw new InvalidStringException();
        }
        this.name = name;
    }
}
