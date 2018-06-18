package dataStructures;

import contracts.SimpleOrderedBag;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class SimpleSortedList<E extends Comparable<E>> implements SimpleOrderedBag<E> {

    private static final int DEFAULT_SIZE = 16;

    private E[] innerCollection;
    private int size;
    private Comparator<E> comparator;

    public SimpleSortedList(Class<E> type, Comparator<E> comparator, int capacity) {
        this.initializeInnerCollection(type, capacity);
        this.comparator = comparator;
    }

    public SimpleSortedList(Class<E> type, int capacity) {
        this(type, Comparable::compareTo, capacity);
    }

    public SimpleSortedList(Class<E> type, Comparator<E> comparator) {
        this(type, comparator, DEFAULT_SIZE);
    }

    public SimpleSortedList(Class<E> type) {
        this(type, Comparable::compareTo, DEFAULT_SIZE);
    }


    @Override
    public void add(E element) {
        if (this.innerCollection.length <= this.size()) {
            this.resize();
        }

        this.innerCollection[this.size()] = element;
        this.size++;

        Arrays.sort(this.innerCollection, 0, this.size(), this.comparator);
    }

    @Override
    public void addAll(Collection<E> elements) {
        if (this.innerCollection.length <= this.size() + elements.size()) {
            this.multiResize(elements);
        }

        for (E element : elements) {
            this.innerCollection[this.size()] = element;
            this.size++;
        }

        Arrays.sort(this.innerCollection, 0, this.size(), this.comparator);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String joinWith(String separator) {
        StringBuilder output = new StringBuilder();
        for (E element : this) {
            output.append(element)
                    .append(separator);
        }
        output.setLength(output.length() - separator.length());
        return output.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleSortedListIterator();
    }

    private void initializeInnerCollection(Class<E> type, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative!");
        }

        this.innerCollection = (E[]) Array.newInstance(type, capacity);
    }

    private void resize() {
        int newSize = this.innerCollection.length * 2;
        E[] newElements = Arrays.copyOf(this.innerCollection, newSize);
        this.innerCollection = newElements;
    }

    private void multiResize(Collection<E> elements) {
        int newSize = this.innerCollection.length * 2;
        while (newSize <= this.size() + elements.size()) {
            newSize *= 2;
        }

        E[] newElements = Arrays.copyOf(this.innerCollection, newSize);
        this.innerCollection = newElements;
    }

    private final class SimpleSortedListIterator implements Iterator<E> {

        private int index;

        public SimpleSortedListIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return size > index;
        }

        @Override
        public E next() {
            return innerCollection[index++];
        }
    }
}
