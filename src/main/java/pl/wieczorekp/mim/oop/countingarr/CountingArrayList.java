package pl.wieczorekp.mim.oop.countingarr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.*;

public class CountingArrayList<T extends Comparable<T>> extends AbstractList<T> implements RandomAccess {
    private ElementData<T>[] elements;
    private int nextFree;

    public CountingArrayList() {
        this.nextFree = 0;
        this.elements = allocateArray(ElementData.class, 1);
    }

    @Override
    public T set(int index, T element) {
        elements[index].setElement(element);
        return elements[index].getElement();
    }

    @Override
    public boolean add(T t) {
        this.add(this.size(), t);
        nextFree++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (size() == elements.length) {
            grow();
        }
        if (elements[index] == null) {
            elements[index] = new ElementData<>();
        }

        elements[index].setElement(element);
    }

    @Override
    public T remove(int index) {
        ElementData<T> removed = elements[index];

        for (int i = index; i < elements.length-1; i++) {
            elements[i].setElement(elements[i+1].getElement());
        }
        elements[elements.length-1] = null;
        nextFree--;

        return removed.getElement();
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= nextFree) {
            throw new IndexOutOfBoundsException();
        }
        return elements[i].getElement();
    }

    public int getReads(int index) {
        return elements[index].getReads();
    }

    public int getWrites(int index) {
        return elements[index].getWrites();
    }

    @Override
    public int size() {
        return nextFree;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        ElementData<T>[] sortedArr = mergeSort(c, toAMaintainingReferencesArray(), 0, size());
        for (int i = 0; i < sortedArr.length; i++) {
            elements[i].setElement(sortedArr[i].getElement());
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    private static <U> U[] allocateArray(Class<U> cls, int size) {
        return (U[]) Array.newInstance(cls, size);
    }

    private ElementData<T>[] mergeSort(Comparator<? super T> c, ElementData<T>[] arr, int begin, int end) {
        if (begin < end) {
            throw new IndexOutOfBoundsException("wtf");
        }
        if (begin == end) {
            ElementData<T>[] sortedArr = allocateArray(ElementData.class, 1);
            sortedArr[0] = arr[begin];
            sortedArr[0].increaseWritesCounter();
            return sortedArr;
        }
        int mid = (begin + end) / 2;

        return merge(c,
                mergeSort(c, arr, 0, mid),
                mergeSort(c, arr, mid+1, end));
    }

    private ElementData<T>[] merge(Comparator<? super T> c, ElementData<T>[] arr1, ElementData<T>[] arr2) {
        int i, i1, i2;
        i = i1 = i2 = 0;
        ElementData<T> newArr[] = allocateArray(ElementData.class, arr1.length + arr2.length);

        while (i1 < arr1.length && i2 < arr2.length) {
            if (c.compare(arr1[i1].getElement(), arr2[i2].getElement()) > 0) {
                newArr[i] = arr1[i1];
                i1++;
            } else {
                newArr[i] = arr2[i2];
                i2++;
            }

            newArr[i].increaseWritesCounter();
            i++;
        }

        while (i1 < arr1.length) {
            newArr[i] = arr1[i1];
            newArr[i].increaseWritesCounter();
            i1++;
            i++;
        }

        while (i2 < arr2.length) {
            newArr[i] = arr2[i2];
            newArr[i].increaseWritesCounter();
            i2++;
            i++;
        }

        return newArr;
    }

    private void grow() {
        ElementData<T>[] newElements = (ElementData<T>[]) Array.newInstance(ElementData.class, elements.length*2);
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
            newElements[i].increaseWritesCounter();
        }
        this.elements = newElements;
    }

    private ElementData<T>[] toAMaintainingReferencesArray() {
        ElementData<T>[] arr = allocateArray(ElementData.class, nextFree);
        for (int i = 0; i < elements.length; i++) {
            arr[i] = elements[i];
            arr[i].increaseWritesCounter();
        }
        return arr;
    }

    private void increaseWrites() {
        Arrays.stream(elements)
                .filter(Objects::nonNull)
                .forEach(ElementData::increaseWritesCounter);
    }

    private void increaseReads() {
        Arrays.stream(elements)
                .filter(Objects::nonNull)
                .forEach(ElementData::increaseReadsCounter);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ElementData<T extends Comparable<T>> implements Comparable<ElementData<T>> {
        @Getter
        private int reads;
        @Getter
        private int writes;
        private T el;

        public void setElement(T element) {
            writes++;
            this.el = element;
        }

        public T getElement() {
            reads++;
            return el;
        }

        private void increaseWritesCounter() {
            writes++;
        }

        private void increaseReadsCounter() {
            reads++;
        }

        @Override
        public int compareTo(ElementData<T> tElementData) {
            return getElement().compareTo(tElementData.getElement());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getElement());
        }

        @Override
        public String toString() {
            return el + " (r=" + reads + ", w=" + writes + ")";
        }
    }
}
