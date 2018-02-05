package textgen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author UC San Diego MOOC team
 *
 */
class MyLinkedListTester {
    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Feel free to use these lists, or add your own
        shortList = new MyLinkedList<String>();
        shortList.add("A");
        shortList.add("B");
        emptyList = new MyLinkedList<Integer>();
        longerList = new MyLinkedList<Integer>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++)
        {
            longerList.add(i);
        }
        list1 = new MyLinkedList<Integer>();
        list1.add(65);
        list1.add(21);
        list1.add(42);

    }

    @Test
    public void testGet(){
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException ignored) {

        }

        assertEquals("A", shortList.get(0), "Check first");
        assertEquals("B", shortList.get(1), "Check second");

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException ignored) {

        }
        try {
            shortList.get(2);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException ignored) {

        }
        // test longer list contents
        for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
            assertEquals((Integer)i, longerList.get(i), "Check " + i + " element");
        }

        // test off the end of the longer array
        try {
            longerList.get(-1);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException ignored) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException ignored) {
        }
    }

    /** Test removing an element from the list.
     * We've included the example from the concept challenge.
     * You will want to add more tests.  */
    @Test
    public void testRemove()
    {
        int a = list1.remove(0);
        assertEquals(65, a, "Remove: check a is correct ");
        assertEquals((Integer)21, list1.get(0), "Remove: check element 0 is correct ");
        assertEquals(2, list1.size(), "Remove: check size is correct ");
        // TODO: Add more tests here
    }

    /** Test adding an element into the end of the list, specifically
     *  public boolean add(E element)
     * */
    @Test
    public void testAddEnd()
    {
        // TODO: implement this test
        list1.add(36);
        assertEquals((Integer)36, list1.get(list1.size()-1), "AddEnd: check last element is correct ");
        assertEquals(4, list1.size(), "AddEnd: check size is correct ");
    }


    /** Test the size of the list */
    @Test
    public void testSize()
    {
        // TODO: implement this test
        assertEquals(3, list1.size(), "Size: check list is correct ");
        assertEquals(0, emptyList.size(), "Size: check empty list is correct ");
    }


    /** Test adding an element into the list at a specified index,
     * specifically:
     * public void add(int index, E element)
     * */
    @Test
    public void testAddAtIndex()
    {
        // TODO: implement this test
        list1.add(1, 12);
        assertEquals((Integer)65, list1.get(0), "AddAtIndex: check first element is correct ");
        assertEquals((Integer)12, list1.get(1), "AddAtIndex: check current element is correct ");
        assertEquals((Integer)42, list1.get(list1.size()-1), "AddAtIndex: check last element is correct ");
    }


    /** Test setting an element in the list */
    @Test
    public void testSet()
    {
        // TODO: implement this test
        int a = list1.set(1, 58);
        assertEquals(58, a, "Set: check a is correct");

    }

}