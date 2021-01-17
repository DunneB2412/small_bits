package com.company.doubly_linked_list;

import com.company.doubly_linked_list.DoublyLinkedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author brian Dunne
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // com.company.fr_recursion.test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);
        assertEquals( "Checking insertBefore to a list containing 7 elements at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // com.company.fr_recursion.test empty list
        testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(10,1);
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(-10,1);
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );

        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(1, 3);
        assertEquals( "Checking insertBefore to my personal feedback's fail case", "1,3,2", testDLL.toString() );
        testDLL.insertBefore(3, 4);
        assertEquals( "Checking insertBefore to my personal feedback's fail case", "1,3,2,4", testDLL.toString() );


    }

    @Test
    public void testIsEmpty() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();

        assertTrue("Checking IsEmpty to a new list", testDLL.isEmpty());

        testDLL.insertBefore(0,1);
        assertFalse("Checking IsEmpty to a populated", testDLL.isEmpty());

        testDLL.deleteAt(0);
        assertTrue("Checking IsEmpty to a freshly emptied list", testDLL.isEmpty());

    }

    @Test
    public void testDeleteAt(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();

        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to a list containing 3 elements at position 1", "1,3", testDLL.toString() );
        testDLL.deleteAt(1);
        assertEquals( "Checking insertBefore to a list containing 2 elements at position 1", "1", testDLL.toString() );
        testDLL.deleteAt(0);
        assertEquals( "Checking insertBefore to a list containing 1 elements at position 1", "", testDLL.toString() );
        assertTrue("Checking if then the set is emptied, it sets head and tail correctly", testDLL.isEmpty());

        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 3 elements at position 0", "2", testDLL.toString() );
        testDLL.deleteAt(0);
        assertEquals( "Checking insertBefore to a list containing 2 elements at position 0", "", testDLL.toString() );

        assertFalse("checking removal of element form empty list", testDLL.deleteAt(1));
        assertEquals( "Checking insertBefore to a list containing 2 elements at position 0", "", testDLL.toString() );
        testDLL.insertBefore(0,1);
        assertFalse("checking removal of element form outside the list", testDLL.deleteAt(1));
    }

    @Test
    public void testReverse(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.reverse();
        assertEquals("testing reverse for empty list", "", testDLL.toString());

        testDLL.insertBefore(0,1);
        testDLL.reverse();
        assertEquals("testing reverse for one element", "1", testDLL.toString());


        testDLL.insertBefore(1,2);
        testDLL.reverse();
        assertEquals("testing reverse for two elements", "2,1", testDLL.toString());
        testDLL.reverse();
        assertEquals("testing reverse for symmetry", "1,2", testDLL.toString());

        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.insertBefore(4,5);
        testDLL.insertBefore(5,6);
        testDLL.insertBefore(6,7);
        testDLL.reverse();
        assertEquals("testing reverse for many", "7,6,5,4,3,2,1", testDLL.toString());

        testDLL=new DoublyLinkedList<>();
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(10,1);
        testDLL.insertBefore(10,1);
        testDLL.insertBefore(10,1);
        testDLL.makeUnique();
        testDLL.insertBefore(1,3);
        assertEquals( "Checking insertBefore to my personal feedback's fail case", "2,3,1", testDLL.toString() );
    }

    @Test
    public void testMakeUnique(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.makeUnique();
        assertEquals("testing makeUnique on empty list", "", testDLL.toString());

        testDLL.insertBefore(0,1);
        testDLL.makeUnique();
        assertEquals("testing makeUnique on one element ser", "1", testDLL.toString());

        testDLL.insertBefore(0,1);
        testDLL.makeUnique();
        assertEquals("testing makeUnique for two similar item list", "1", testDLL.toString());

        testDLL.insertBefore(10,2);
        testDLL.insertBefore(10,1);
        testDLL.insertBefore(10,4);
        testDLL.insertBefore(10,3);
        testDLL.insertBefore(10,2);
        testDLL.makeUnique();
        assertEquals("testing make unique on non unique large set", "1,2,4,3", testDLL.toString());
        testDLL.makeUnique();
        assertEquals("testing make unique on unique large set", "1,2,4,3", testDLL.toString());
    }

    @Test
    public void testGet(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();

        assertNull("testing get on empty set",testDLL.get(1));

        testDLL.insertBefore(10,1);
        assertEquals("testing get on one element set", 1, (int) testDLL.get(0));
        assertNull("testing get with index out of bounds", testDLL.get(1));

        testDLL.insertBefore(10,2);
        testDLL.insertBefore(10,3);
        testDLL.insertBefore(10,4);
        testDLL.insertBefore(10,5);
        assertEquals("testing get on many @0", 1, (int) testDLL.get(0));
        assertEquals("testing get on many @end", 5, (int) testDLL.get(4));
        assertEquals("testing get on many @centre", 3, (int) testDLL.get(2));
        assertNull("testing get on many @<0",  testDLL.get(-1));
        assertNull("testing get on many @>end",  testDLL.get(10));

    }


    @Test
    public void testStack(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();

        testDLL.push(1);
        assertEquals("testing first push to empty list","1",testDLL.toString());
        testDLL.push(2);
        assertEquals("testing second push to queue", "1,2",testDLL.toString());
        testDLL.push(3);
        testDLL.push(4);
        testDLL.push(5);
        testDLL.push(5);
        assertEquals("testing many pushes to queue", "1,2,3,4,5,5",testDLL.toString());

        assertEquals("testing pop", 5, (int) testDLL.pop());
        assertEquals("testing that pop actually removed element", "1,2,3,4,5", testDLL.toString());

        testDLL.push(6);
        assertEquals("tesing that you can still push", "1,2,3,4,5,6", testDLL.toString());

        assertEquals("testing pop", 6, (int) testDLL.pop());
        assertEquals("testing pop", 5, (int) testDLL.pop());
        assertEquals("testing pop", 4, (int) testDLL.pop());
        assertEquals("testing pop", 3, (int) testDLL.pop());
        assertEquals("testing pop", 2, (int) testDLL.pop());
        assertEquals("testing pop", 1, (int) testDLL.pop());
        assertTrue("checking if the stack emptied properly", testDLL.isEmpty());
        assertNull("testing pop with an empty stack",  testDLL.pop());
    }

    @Test
    public void testQueue(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();

        testDLL.enqueue(1);
        assertEquals("testing first enqueue to empty list","1",testDLL.toString());
        testDLL.enqueue(2);
        assertEquals("testing second enqueue to queue", "1,2",testDLL.toString());
        testDLL.enqueue(3);
        testDLL.enqueue(4);
        testDLL.enqueue(5);
        testDLL.enqueue(5);
        assertEquals("testing many enqueues to queue", "1,2,3,4,5,5",testDLL.toString());

        assertEquals("testing dequeue", 1, (int) testDLL.dequeue());
        assertEquals("testing that pop actually removed element", "2,3,4,5,5", testDLL.toString());

        testDLL.enqueue(6);
        assertEquals("testing that you can still enqueue", "2,3,4,5,5,6", testDLL.toString());

        assertEquals("testing dequeue", 2, (int) testDLL.dequeue());
        assertEquals("testing dequeue", 3, (int) testDLL.dequeue());
        assertEquals("testing dequeue", 4, (int) testDLL.dequeue());
        assertEquals("testing dequeue", 5, (int) testDLL.dequeue());
        assertEquals("testing dequeue", 5, (int) testDLL.dequeue());
        assertEquals("testing dequeue", 6, (int) testDLL.dequeue());
        assertTrue("checking if the queue emptied properly", testDLL.isEmpty());
        assertNull("testing pop with an empty queue",  testDLL.dequeue());
    }
    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one com.company.fr_recursion.test.

}
