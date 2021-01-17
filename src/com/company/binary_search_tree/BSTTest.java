package com.company.binary_search_tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.Assert.*;

/**
 *  Test class for Doubly Linked List
 *
 *  @author brian Dunne
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class BSTTest {

    @Test
    public void constructor(){
        BST<Integer , String> test = new BST<>();
        assertTrue("checking new bst is empty", test.isEmpty());
    }

    @Test
    public void isEmpty() {
        BST<Integer , String> test = new BST<>();
        assertTrue("checking new bst is empty", test.isEmpty());
        test.put(10, "10-s");
        assertFalse("checking freshly populated bst is not empty", test.isEmpty());
        test.delete(10);
        assertTrue("checking freshly emptied bst is empty", test.isEmpty());
    }

    @Test
    public void size() {
        BST<Integer , String> test = new BST<>();
        assertEquals("testing size of fresh bst is 0", 0, test.size());
        test.put(10, "10-s");
        assertEquals("testing freshly populated by 1 bst is 1", 1, test.size());
        test.put(1, "11-s");
        assertEquals("testing adding another to bst is size 1", 2, test.size());
        test.delete(10);
        test.delete(1);
        assertEquals("testing freshly emptied bst is 1", 0, test.size());
    }

    @Test
    public void contains() {
        BST<Integer , String> test = new BST<>();
        assertFalse("testing a freshly initialised bst doesn't contain something", test.contains(10));
        test.put(10, "10-s");
        assertTrue("testing after adding, bst contains something", test.contains(10));
        test.delete(10);
        assertFalse("testing after removing, bst no longer contains something", test.contains(10));
    }

    @Test
    public void get() {
        BST<Integer , String> test = new BST<>();
        assertNull("testing get on freshly initialised bst", test.get(10));
        test.put(10, "10-s");
        assertEquals("testing after adding, get 10", "10-s", test.get(10));
        assertNull("testing get on freshly initialised bst", test.get(1));
        test.put(10, "10-s");
        test.put(1, "1-s");
        assertEquals("testing after adding, get 10", "1-s", test.get(1));
        assertEquals("testing after adding, get 10", "10-s", test.get(10));
        assertNull("testing get on freshly initialised bst", test.get(2));
        test.delete(1);
        assertEquals("testing after adding, get 10", "10-s", test.get(10));

        test = generateSymetricBST();
        System.out.println(test);
        assertEquals("testing after adding, get 1", "1-s", test.get(1));
        assertEquals("testing after adding, get 1", "2-s", test.get(2));
        assertEquals("testing after adding, get 1", "3-s", test.get(3));
        assertEquals("testing after adding, get 1", "4-s", test.get(4));
        assertEquals("testing after adding, get 1", "5-s", test.get(5));
        assertEquals("testing after adding, get 1", "6-s", test.get(6));
        assertEquals("testing after adding, get 1", "7-s", test.get(7));
        assertEquals("testing after adding, get 1", "8-s", test.get(8));
        assertEquals("testing after adding, get 1", "9-s", test.get(9));
        assertEquals("testing after adding , get 1", "10-s", test.get(10));
        assertEquals("testing after adding, get 10", "10-s", test.get(10));

    }

    @Test
    public void put() {
        BST<Integer , String> test = new BST<>();
        test.put(10, "10-s");
        assertEquals("testing put, first element", "(()10())", test.printKeysInOrder());
        test.put(10, "supper 10");
        assertEquals("testing after changing existing element, get 10", "supper 10", test.get(10));
        test.put(10, null);
        assertNull("testing setting 10 to null, should mean bst is empty", test.get(10));
        assertTrue("should be empty now",test.isEmpty());




        test = new BST<>();
        test.put(8, "8-s");
        assertEquals("testing put, set up balanced binary tree", "(()8())", test.printKeysInOrder());
        test.put(4, "4-s");
        assertEquals("testing put, set up balanced binary tree", "((()4())8())", test.printKeysInOrder());
        test.put(2, "2-s");
        assertEquals("testing put, set up balanced binary tree", "(((()2())4())8())", test.printKeysInOrder());
        test.put(1, "1-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2())4())8())", test.printKeysInOrder());
        test.put(3, "3-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4())8())", test.printKeysInOrder());
        test.put(6, "6-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4(()6()))8())", test.printKeysInOrder());
        test.put(5, "5-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6()))8())", test.printKeysInOrder());
        test.put(7, "7-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8())", test.printKeysInOrder());
        test.put(12, "12-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(()12()))", test.printKeysInOrder());
        test.put(10, "10-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8((()10())12()))", test.printKeysInOrder());
        test.put(9, "9-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10())12()))", test.printKeysInOrder());
        test.put(11, "11-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12()))", test.printKeysInOrder());
        test.put(14, "14-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12(()14())))", test.printKeysInOrder());
        test.put(13, "13-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12((()13())14())))", test.printKeysInOrder());
        test.put(15, "15-s");
        assertEquals("testing put, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
    }

    @Test
    public void height() {
        BST<Integer, String> test = new BST<>();
        assertEquals("testing height on empty bst", -1, test.height());

        test = new BST<>();
        test.put(8, "8-s");
        assertEquals("testing height, set up balanced binary tree", 0, test.height());
        test.put(4, "4-s");
        assertEquals("testing height, set up balanced binary treet", 1, test.height());
        test.put(2, "2-s");
        assertEquals("testing height, set up balanced binary tree", 2, test.height());


        test.put(12, "12-s");
        assertEquals("testing height, set up balanced binary tree", 2, test.height());
        test.put(6, "6-s");
        assertEquals("testing height, set up balanced binary tree", 2, test.height());
        test.put(14, "14-s");
        assertEquals("testing height, set up balanced binary tree", 2, test.height());
        test.put(10, "10-s");
        assertEquals("testing height, set up balanced binary tree", 2, test.height());

        test.put(9, "9-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(1, "1-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(3, "3-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(5, "5-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(7, "7-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(11, "11-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(13, "13-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());
        test.put(15, "15-s");
        assertEquals("testing height, set up balanced binary tree", 3, test.height());

    }

    @Test
    public void median() {
        BST<Integer, String> test = new BST<>();
        assertNull("checking midian on empty bdt", test.median());

        test = new BST<>();
        test.put(8, "8-s");
        assertEquals("testing median, set up balanced binary tree", 8, test.median().intValue());
        test.put(4, "4-s");
        assertEquals("testing median, set up balanced binary tree", 4, test.median().intValue());
        test.put(2, "2-s");
        assertEquals("testing median, set up balanced binary tree", 4, test.median().intValue());
        test.put(1, "1-s");
        assertEquals("testing median, set up balanced binary tree", 2, test.median().intValue());
        test.put(3, "3-s");
        assertEquals("testing median, set up balanced binary tree", 3, test.median().intValue());
        test.put(6, "6-s");
        assertEquals("testing median, set up balanced binary tree", 3, test.median().intValue());
        test.put(5, "5-s");
        assertEquals("testing median, set up balanced binary tree", 4, test.median().intValue());
        test.put(7, "7-s");
        assertEquals("testing median, set up balanced binary tree", 4, test.median().intValue());
        test.put(12, "12-s");
        assertEquals("testing median, set up balanced binary tree", 5, test.median().intValue());
        test.put(10, "10-s");
        assertEquals("testing median, set up balanced binary tree", 5, test.median().intValue());
        test.put(9, "9-s");
        assertEquals("testing median, set up balanced binary tree", 6, test.median().intValue());
        test.put(11, "11-s");
        assertEquals("testing median, set up balanced binary tree", 6, test.median().intValue());
        test.put(14, "14-s");
        assertEquals("testing median, set up balanced binary tree", 7, test.median().intValue());
        test.put(13, "13-s");
        assertEquals("testing median, set up balanced binary tree", 7, test.median().intValue());
        test.put(15, "15-s");
        assertEquals("testing median, set up balanced binary tree", 8, test.median().intValue());
    }

    @Test
    public void delete() {
        BST<Integer, String> test = generateSymetricBST();

        assertEquals("testing remove, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(4);
        assertEquals("testing remove element '4'", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(4);
        assertEquals("testing remove same element again '4'", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(14);
        assertEquals("testing remove another element '14' ", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12((()13())15())))", test.printKeysInOrder());
        test.delete(15);
        assertEquals("testing remove another element '15' ", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(8);
        assertEquals("testing remove another element '8' ", "((((()1())2(()3()))5(()6()))7(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(1);
        assertEquals("testing remove another element '8' ", "(((()2(()3()))5(()6()))7(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(2);
        assertEquals("testing remove another element '8' ", "(((()3())5(()6()))7(((()9())10(()11()))12(()13())))", test.printKeysInOrder());


        test = generateSymetricBST('L');
        assertEquals("testing remove, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(4);
        assertEquals("testing remove element '4'", "((((()1())2())3((()5())6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(4);
        assertEquals("testing remove same element again '4'", "((((()1())2())3((()5())6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(14);
        assertEquals("testing remove another element '14' ", "((((()1())2())3((()5())6(()7())))8(((()9())10(()11()))12(()13(()15()))))", test.printKeysInOrder());
        test.delete(15);
        assertEquals("testing remove another element '15' ", "((((()1())2())3((()5())6(()7())))8(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(8);
        assertEquals("testing remove another element '8' ", "((((()1())2())3((()5())6()))7(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(1);
        assertEquals("testing remove another element '8' ", "(((()2())3((()5())6()))7(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(2);
        assertEquals("testing remove another element '8' ", "((()3((()5())6()))7(((()9())10(()11()))12(()13())))", test.printKeysInOrder());

        test = generateSymetricBST('R');
        assertEquals("testing remove, set up balanced binary tree", "((((()1())2(()3()))4((()5())6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(4);
        assertEquals("testing remove element '4'", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(4);
        assertEquals("testing remove same element again '4'", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12((()13())14(()15()))))", test.printKeysInOrder());
        test.delete(14);
        assertEquals("testing remove another element '14' ", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12((()13())15())))", test.printKeysInOrder());
        test.delete(15);
        assertEquals("testing remove another element '15' ", "((((()1())2(()3()))5(()6(()7())))8(((()9())10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(8);
        assertEquals("testing remove another element '8' ", "((((()1())2(()3()))5(()6(()7())))9((()10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(1);
        assertEquals("testing remove another element '1' ", "(((()2(()3()))5(()6(()7())))9((()10(()11()))12(()13())))", test.printKeysInOrder());
        test.delete(2);
        assertEquals("testing remove another element '2' ", "(((()3())5(()6(()7())))9((()10(()11()))12(()13())))", test.printKeysInOrder());




    }

    private BST<Integer, String> generateSymetricBST(char mode){
        BST<Integer, String> bst = new BST<>(mode);
        bst.put(8, "8-s");
        bst.put(4, "4-s");
        bst.put(2, "2-s");
        bst.put(1, "1-s");
        bst.put(3, "3-s");
        bst.put(6, "6-s");
        bst.put(5, "5-s");
        bst.put(7, "7-s");
        bst.put(12, "12-s");
        bst.put(10, "10-s");
        bst.put(9, "9-s");
        bst.put(11, "11-s");
        bst.put(14, "14-s");
        bst.put(13, "13-s");
        bst.put(15, "15-s");
        return bst;
    }
    private BST<Integer, String> generateSymetricBST(){ return generateSymetricBST('B');}




    @Test
    public void testPrettyPrint() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree",
                "-null\n", bst.prettyPrintKeys());

        //  -7
        //   |-3
        //   | |-1
        //   | | |-null
        bst.put(7, 7);       //   | |  -2
        bst.put(8, 8);       //   | |   |-null
        bst.put(3, 3);       //   | |    -null
        bst.put(1, 1);       //   |  -6
        bst.put(2, 2);       //   |   |-4
        bst.put(6, 6);       //   |   | |-null
        bst.put(4, 4);       //   |   |  -5
        bst.put(5, 5);       //   |   |   |-null
        //   |   |    -null
        //   |    -null
        //    -8
        //     |-null
        //      -null

        String result =
                "-7\n" +
                        " |-3\n" +
                        " | |-1\n" +
                        " | | |-null\n" +
                        " | |  -2\n" +
                        " | |   |-null\n" +
                        " | |    -null\n" +
                        " |  -6\n" +
                        " |   |-4\n" +
                        " |   | |-null\n" +
                        " |   |  -5\n" +
                        " |   |   |-null\n" +
                        " |   |    -null\n" +
                        " |    -null\n" +
                        "  -8\n" +
                        "   |-null\n" +
                        "    -null\n";
        assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
    }


    /** <p>Test {@link BST#delete(Comparable)}.</p> */
    @Test
    public void testDelete() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
        //         5

        assertEquals("Checking order of constructed tree",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(9);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

        bst.delete(6);
        assertEquals("Deleting node with single child",
                "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(3);
        assertEquals("Deleting node with two children",
                "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
    }
}