package hw_2_16;

import hw_2_16.exception.IntegerArrayOutOfBoundsExeption;
import hw_2_16.exception.IntegerListNotExistElementExeption;
import hw_2_16.exception.IntegerListOutOfSizeExeption;
import hw_2_16.exception.NullItemExeption;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] list;
    private final int initCapacity = 5;
    private int size;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public IntegerListImpl() {
         this.list = new Integer[initCapacity];
    }

    @Override
    public Integer add(Integer item) {

        validateItem(item);

        if (size == list.length) {
            list = grow();
        }
        list[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item){

        final int s = list.length;

        validateItem(item);
        validateIndex(index);

        if (size == s) list = grow();

        System.arraycopy(list, index,
                list, index + 1,
                size - index);

        list[index] = item;
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item){

        validateItem(item);
        validateIndex(index);

        list[index] = item;

        return item;

    }
    @Override
    public Integer remove(Integer item){

        Integer oldValue = null;

        validateItem(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                oldValue = list[i];
                System.arraycopy(list, i+1,
                         list, i,
                        list.length - i-1);
                --size;
            }
        }
        if (oldValue == null) {
            throw new IntegerListNotExistElementExeption();
        }
        return oldValue;
    }
    @Override
    public Integer remove(int index){

        Integer oldValue;

        validateIndex(index);

        oldValue = list[index];
        System.arraycopy(list, index+1,
                list, index,
                list.length - index-1);
        --size;

        return oldValue;
    }
    @Override
    public boolean equals(IntegerList otherList){
        boolean equal = false;
        if (size == otherList.size()) {
            equal = true;
            for (int i = 0; i < size; i++) {
                if (!list[i].equals(otherList.get(i))){
                    equal = false;
                    break;
                }
            }
        }
        else {
            if (otherList.size() == 0) throw new IntegerListOutOfSizeExeption();
        }
        return equal;
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    @Override
    public String toString(){
        return Arrays.toString(list);
    }
    @Override
    public boolean contains(Integer item){

        boolean result;
        Integer[] listCopy = toArray();

        validateItem(item);
        //result = binarySearch(listCopy,item);
        result = binarySearch(listCopy,item);

        return result;
    }
    @Override
    public int indexOf(Integer item){

        int result = 0;

        validateItem(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                result = i;
                break;
            } else {
                result = -1;
            }
        }
        return result;
    }
    @Override
    public int lastIndexOf(Integer item){

        int result = 99;

        validateItem(item);

        for (int i = size-1; i > 0; i--) {
            if (list[i].equals(item)) {
                result = i;
                break;
            } else {
                result = -1;
            }
        }
        return result;
    }
    @Override
    public Integer get(int index){

        validateIndex(index);

        return list[index];
    }
    @Override
    public void clear(){
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                list[i] = null;
            }
        }
    }
    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private Integer[] grow() {
        return grow(list.length + 1);
    }
    private Integer[] grow(int minCapacity) {
        return list = Arrays.copyOf(list,
                newCapacity(minCapacity));
    }
    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = list.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (list == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }
    private void validateItem(Integer item){
        if (item == null) {
            throw new NullItemExeption();
        }
    }
    private void validateIndex(int index) {
        if (index >= list.length)
            throw new IntegerArrayOutOfBoundsExeption();
        else if (index >= size) {
            throw new IntegerListOutOfSizeExeption();
        }
    }
    private boolean binarySearch(Integer[] arr, Integer item){
        int min = 0;
        int max = arr.length - 1;
        quickSort(arr,0,arr.length-1);
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == arr[mid]) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        Integer temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
