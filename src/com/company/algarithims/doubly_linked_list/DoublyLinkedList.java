package com.company.algarithims.doubly_linked_list;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Brian Dunne
 *  @version 09/10/18 11:13:22
 *
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data:
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 *
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
public class DoublyLinkedList<T extends Comparable<T>>{
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * completely un necessary, this is the default constructor
     */
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: TODO O(1)
     *
     * Justification:
     *  TODO there is no iteration or function calls here, providing everything works this should be good
     */
    public boolean isEmpty(){
        return head==null;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     *
     * Worst-case asymptotic running time cost: TODO O(n)
     *
     * Justification:
     *  TODO it steps through the list until either it reaches the desired position or an edge
     */
    public void insertBefore(int pos, T data){
        if (isEmpty()){
            DLLNode node = new DLLNode(data,null,null);
            head = tail = node;
        }else {
            if(pos<=0){
                head = new DLLNode(data,null, head);
            }else{
                DLLNode current = getNode(pos);
                if(current==null){
                    tail = new DLLNode(data, tail, null);
                }else {
                    new DLLNode(data,current.prev,current);
                }
            }
        }
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: TODO O(n)
     *
     * Justification:
     *  TODO getNode has O(n)
     *
     */
    public T get(int pos){
        DLLNode node = getNode(pos);
        return (node!=null)?node.data:null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     *
     * Worst-case asymptotic running time cost: TODO O(n)
     *
     * Justification:
     *  TODO getNode has O(n) and removeNode has O(1)
     */
    public boolean deleteAt(int pos){
        DLLNode node = getNode(pos);
        if(node==null) return false;
        removeNode(node);
        return true;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: TODO O(n)
     *
     * Justification:
     *  TODO steps through the list swapping the polarity of prev and next. then swaps head and tail
     */
    public void reverse(){
        DLLNode current = head;
        while(current!=null){
            DLLNode help = current.next;
            current.next = current.prev;
            current.prev = help;
            current = help;
        }
        DLLNode help = tail;
        tail = head;
        head = help;
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements unique.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: TODO O(n^2)
     *
     * Justification:
     *  TODO steps through each node then checks all remaining nodes to se if they store the same data. removeNode has O(1)
     */
    public void makeUnique(){
        DLLNode node = head;
        while(node!=tail&&node!=null){
            DLLNode test = node.next;
            while (test!=null){
                if (node.data.equals(test.data)){
                    removeNode(test);
                }
                test = test.next;
            }
            node = node.next;
        }
    }


    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param data : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: TODO O(1)
     *
     * Justification:
     *  TODO doesn't cycle through anything, doesn't call any complex functions
     */
    public void push(T data){
        if (isEmpty()) {
            DLLNode node = new DLLNode(data, null, null);
            head = tail = node;
        }else {
            tail = new DLLNode(data, tail, null);
        }
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: TODO O(1)
     *
     * Justification:
     *  TODO doesn't cycle through anything, doesn't call any complex functions
     */
    public T pop(){
        if(isEmpty()) return null;
        T data = tail.data;
        removeNode(tail);
        return data;
    }


    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param data : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: TODO O(1)
     *
     * Justification:
     *  TODO same as push, just used push because it basically dose the same thing
     */
    public void enqueue(T data){
        push(data); // just slap on top of the list
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with a queue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: TODO O(1)
     *
     * Justification:
     *  TODO doesn't cycle through anything, doesn't call any complex functions
     */
    public T dequeue(){
        if(isEmpty()) return null;
        T data = head.data;
        removeNode(head);
        return data; // take from the head of the list
    }

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    @Override
    public String toString() {
        if (isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        DLLNode node = head;
        do{
            builder.append(node.data.toString());
            node = node.next;
            if (node!=null){
                builder.append(",");
            }
        }while (node!=null);
        return builder.toString()+"";
    }

    /**
     * Returns the node stored at a particular position
     * @param pos : the position
     * @return the node at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: TODO O(n)
     *
     * Justification:
     *  TODO steps through the list till either it's reached position or the end of the list.
     *
     */
    private DLLNode getNode(int pos){
        if(pos<0) return null;
        DLLNode current = head;
        int index = 0;
        while(index<pos && current != null){
            current = current.next;
            index++;
        }
        return current;
    }

    private void removeNode(DLLNode node){
        if(node.prev!=null) node.prev.next=node.next;
        else {
            head = node.next;
            //head.prev = null;
        }
        if(node.next!=null) node.next.prev=node.prev;
        else {
            tail = node.prev;
            //tail.next = null;
        }
    }

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {

        public final T data;
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List#
         *
         * modified to de clutter the methods of DLL class
         */
        public DLLNode( T theData, DLLNode prevNode, DLLNode nextNode )
        {
            data = theData;
            prev = prevNode;
            if (prev!=null)prev.next = this;
            next = nextNode;
            if (next!=null)next.prev = this;
        }
    }
}







