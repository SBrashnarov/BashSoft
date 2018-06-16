package contracts;

public interface FilteredTaker {

    void filterAndTake(String courseName, String filterType);

    void filterAndTake(String courseName, String filterType, int studentsToTake);
}
