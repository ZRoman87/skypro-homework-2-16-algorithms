package hw_2_16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static hw_2_16.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {

    private final IntegerListImpl out = new IntegerListImpl();
    private final IntegerListImpl otherList = new IntegerListImpl();
    private final IntegerListImpl otherNullList = new IntegerListImpl();
    private String OUT_TO_STRING;
    private String OUT_TO_ARRAY;
    private String OUT_NULL_STRING;

    @BeforeEach
    public void setUp(){
        out.add(INTEGER_00);
        out.add(INTEGER_01);
        out.add(INTEGER_02);

        otherList.add(INTEGER_01);
        otherList.add(INTEGER_02);
        otherList.add(INTEGER_03);

        OUT_TO_STRING = "[0, 1, 2, null, null]";
        OUT_TO_ARRAY = "[0, 1, 2]";
        OUT_NULL_STRING = "[null, null, null, null, null]";
    }

    @Test
    public void shouldReturnCorrectIntegerAndSizeAfterAdd(){
        Integer result = out.add(INTEGER_03);
        assertEquals(INTEGER_03, result);
        assertEquals(4, out.size());
    }
    @Test
    public void shouldReturnCorrectStringAndSizAfterAddByIndex(){
        Integer result = out.add(INDEX_0, INTEGER_10);
        assertEquals(INTEGER_10, result);
        assertEquals(4, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToAddStringToIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.add(INDEX_5, INTEGER_05)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToAddStringToIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.add(INDEX_3, INTEGER_03)
        );
    }
    @Test
    public void shouldReturnCorrectStringAndSizAfterSet(){
        Integer result = out.set(INDEX_0, INTEGER_10);
        assertEquals(INTEGER_10, result);
        assertEquals(3, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToSetStringToIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.set(INDEX_5, INTEGER_05)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToSetStringToIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.set(INDEX_3, INTEGER_03)
        );
    }

    @Test
    public void shouldReturnCorrectStringAndSizAfterRemove(){
        Integer result = out.remove(INTEGER_00);
        assertEquals(INTEGER_00, result);
        assertEquals(2, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToRemoveNotExistElement() {
        assertThrows(RuntimeException.class,
                () -> out.remove(INTEGER_03)
        );
    }
    @Test
    public void shouldReturnCorrectStringAndSizAfterRemoveByIndex(){
        Integer result = out.remove(INDEX_0);
        assertEquals(INTEGER_00, result);
        assertEquals(2, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToRemoveStringToIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.remove(INDEX_5)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToRemoveStringToIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.remove(INDEX_3)
        );
    }

    @Test
    public void shouldReturnFalseIfListsNotEquals(){
        boolean result = out.equals(otherList);
        assertFalse(result);
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenAnotherListIsNull() {
        assertThrows(RuntimeException.class,
                () -> out.equals(otherNullList)
        );
    }

    @Test
    public void shouldReturnCorrectSize(){
        int result = out.size();
        assertEquals(3, result);
    }

    @Test
    public void shouldReturnTrueIfListEmpty(){
        boolean result = otherNullList.isEmpty();
        assertTrue(result);
    }
    @Test
    public void shouldReturnCorrectString(){
        String result = out.toString();
        assertEquals(OUT_TO_STRING, result);
    }
    @Test
    public void shouldReturnTrueWhenListContainsItem(){
        boolean result = out.contains(INTEGER_01);
        assertTrue(result);
    }
    @Test
    public void shouldReturnCorrectIndexOfItemInList(){
        int result = out.indexOf(INTEGER_01);
        assertEquals(INDEX_1, result);
    }
    @Test
    public void shouldReturnCorrectLastIndexOfItemInList(){
        out.add(INTEGER_01);
        int result = out.lastIndexOf(INTEGER_01);
        assertEquals(INDEX_3, result);
    }

    @Test
    public void shouldReturnCorrectStringAndSizAfterGet(){
        Integer result = out.get(INDEX_0);
        assertEquals(INTEGER_00, result);
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToGetStringByIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.get(INDEX_5)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToGetStringByIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.get(INDEX_3)
        );
    }
    @Test
    public void shouldClearList(){
        out.clear();
        String result = out.toString();
        assertEquals(OUT_NULL_STRING, result);
    }
    @Test
    public void shouldReturnStringArrayWithListItems(){
        String result = Arrays.toString(out.toArray());
        assertEquals(OUT_TO_ARRAY, result);
    }

}
