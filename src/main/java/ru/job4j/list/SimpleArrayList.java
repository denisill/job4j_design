package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            growArrays();
        }
        container[size++] = value;
        modCount++;
    }

    public void growArrays() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        T setValue = get(index);
        container[index] = newValue;
        return setValue;
    }

    @Override
    public T remove(int index) {
        T removeValue = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removeValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}