import dataStructures.SimpleSortedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsSimpleSortedList {

    private static final List<Integer> UNSORTED_ELELEMENTS_TO_ADD = new ArrayList<>(Arrays.asList(
            6,7,8,9,
            1,2,3,4,5,
            15,16,17,18,19,
            10,11,12,13,14,
            20,21,22,23,24,
            30,31,32,33,34,
            25,26,27,28,29,
            35,36,37,38,39,
            45,46,47,48,49,
            40,41,42,43,44));

    private static final List<Integer> EXPECTED_ELEMENTS = new ArrayList<>(Arrays.asList(
            1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49));


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeSizeShouldThrowException(){
        SimpleSortedList<Integer> ints = new SimpleSortedList<>(Integer.class, -5);
    }

    @Test
    public void testMultiResizeShouldWorkCorrect() {
        SimpleSortedList<Integer> ints = new SimpleSortedList<>(Integer.class);

        ints.addAll(UNSORTED_ELELEMENTS_TO_ADD);

        Assert.assertEquals(49, ints.size());
    }

    @Test
    public void testAddAllElementsShouldAddElementsAndSortThem() {
        SimpleSortedList<Integer> ints = new SimpleSortedList<>(Integer.class, 50);

        ints.addAll(UNSORTED_ELELEMENTS_TO_ADD);

        List<Integer> sortedElements = new ArrayList<>();

        for (Integer element : ints) {
            sortedElements.add(element);
        }

        Assert.assertEquals(EXPECTED_ELEMENTS, sortedElements);
    }
}
