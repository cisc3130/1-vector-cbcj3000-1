
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.lang.RuntimeException;
import java.util.Arrays;

public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess {

    protected Object[] data;
    protected int size;

    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("");
        }
    }

    @SuppressWarnings("unchecked")
    private E data(int index) {
        return (E) data[index];
    }

    private void grow() {
        int newCapacity = data.length * 2;
        data = Arrays.copyOf(data, newCapacity);
    }

    public Vector() {
        this(10);
    }

    public Vector(int initialCapacity) {
        data = new Object[initialCapacity];
        size = 0;
    }

    public E get(int index) {
        rangeCheck(index);
        return data(index);
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = data(index);
        data[index] = element;
        return oldValue;
    }

    public boolean add(E element) {
        if (size == data.length) {
            grow();
        }
        data[size++] = element;
        return true;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        //Iterates through the data and as soon as it reaches object o
        //the location is returned. If it is not reached the default returns -1
        for (int i = 0; i < size; i++) { //Iterating through the data
            if (data(i) == o) { //If o isfound while iterating
                return i; //returning where o was ocaated in data
            }
        }
        return -1; // returning -1 if o was not found
    }

    public int addAfterIf(Object toAdd, Object elementBefore) {
        //This is the index of the element before, it determines where toAdd is placed
        int idx = indexOf(elementBefore);
        rangeCheck(idx); //edge case
        add(data(size - 1)); //because we are adding an element and all the data gets pushed back one
        if (idx > (size / 2)) { //if located in second half
            for (int i = 0; i < size; i++) {
                data[i] = data[i + 1]; //code pushing everything back by one
            }
            data[idx - 1] = toAdd; //adds toAdd right after elementBefore
            size++;//increases size since we pushed everything back one
        } else { //if located in first half
            for (int i = 0; i < size; i++) {
                data[i] = data[i + 1]; //code pushing everything back by one
            }
            data[(size / 2)] = toAdd; //adds toAdd add the end of thefirst half of the array
            size++; //increases size since we pushed everything back one
        }
        return indexOf(toAdd);
    }

    public static void main(String[] args) {
        Vector<Integer> intlist = new Vector<Integer>();
        Vector<String> stringlist = new Vector<String>();
        Vector<Vector<Integer>> intveclist = new Vector<Vector<Integer>>();

        for (Integer i = 0; i < 10; i++) {
            intlist.add(i);
        }

        System.out.println(intlist.indexOf(7));
        System.out.println(intlist.indexOf("seven"));
    }
}