package com.company.algarithims;

import java.util.Random;

public class LinkedListHT {
    private Node head, tail;


    public LinkedListHT(){
        this(null,null);
    }
    private LinkedListHT(Node head, Node tail){ //private constructor for merge sort
        this.head = head;
        this.tail = tail;
    }

    public static void main(String[] args){
        LinkedListHT tester = new LinkedListHT();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            tester.addAtHead(random.nextInt(2000)-1000);
        }

        System.out.println(tester.toString());
        tester.sort();

        System.out.println(tester.toString());
        tester.reverse();
        System.out.println(tester.toString());
    }


    /**This method appends a new list to the end of this list.*/
    public void addAtTail(LinkedListHT lst){
        if (head == null){
            head = lst.head;
        }else {
            tail = lst.head;
        }
    }

    /**This method adds a new node to the beginning of our list*/
	public void addAtHead(int x){
	    if(head == null){
	        head = tail = new Node(x);
        }else {
            Node helper = head;
            head = new Node(x);
            head.next = helper;
        }
    }


	public void sort(){
        LinkedListHT help = sortRecurse(this);
        head = help.head;
        tail = help.tail;
    }
    private static LinkedListHT sortRecurse(LinkedListHT listHT){
	    if(listHT.head == listHT.tail){
	        return listHT;
        }if(listHT.head.next == listHT.tail){
	        if(listHT.head.value>listHT.tail.value){
	            listHT.reverse();
            }
	        return listHT;
        }
	    Node mid = listHT.head;
	    Node end = listHT.head;
	    int count = 0;
	    while (end != null){
	        end = end.next;
            count++;
	        if(count%2==0&&end!=null){
	            mid = mid.next;
            }
        }
	    LinkedListHT upper = new LinkedListHT(mid.next, listHT.tail);
        mid.next = null;//cut of upper
        listHT.tail = mid;
	    return merge(sortRecurse(listHT),sortRecurse(upper));
    }
    private static LinkedListHT merge(LinkedListHT listHT1, LinkedListHT listHT2){
	    LinkedListHT result = new LinkedListHT();
	    Node a = listHT1.head;
	    Node b = listHT2.head;
        while ( a != null || b != null){
            if(b==null||(a!=null && a.value< b.value)){
                result.addAtHead(a.value);
                a = a.next;
            }else {
                result.addAtHead(b.value);
                b = b.next;
            }
        }
        result.reverse();
        return result;
    }

    /**This method deletes the head of our linked list*/
	public void delAtHead(){
        head = head.next;
        if(head == null){ //cleaning up loos end
            tail = null;
        }
    }

    /**This method deletes the las node of the list by making the one before last point at null*/
	public void delAtTail(){
        Node node = this.head;
        //finding the node before last:
        while (node.next.next != null) {
            node = node.next;
        }
        node.setNext(null);
        tail = node;

    }

    /**This method gets the value of each node and adds them together, returning the sum of all the values
     * within the list.*/
	public int sum(){
        int sum = 0;
        Node node = this.head;
        while(node.next !=null){
            sum = sum + node.next.getValue();
        }

        return sum;
    }

    /**This method reverses the order of the like list by changing the direction of the "arrows" between
     * each node.*/
	public void reverse(){
	    Node helperOne = head;
	    Node helperTwo = helperOne.next;
	    Node helperThree = helperTwo.next;
        head.next = null;

	    while(helperThree != null){
	        helperTwo.next = helperOne;
	        helperOne = helperTwo;
	        helperTwo = helperThree;
	        helperThree = helperThree.next;
        }
	    helperTwo.next=helperOne;
	    head =helperTwo;

    }

	public boolean equals(Object ob){
	    if(ob instanceof LinkedListHT){
	        Node nodeA = this.head;
	        Node nodeB = ((LinkedListHT)ob).head;
	        while (nodeA != null){
	            if(!nodeA.equals(nodeB)){
	                return false;
                }
	            nodeA = nodeA.next;
	            nodeB = nodeB.next;
            }
	        return true;
        }
        return false;
    }

    /**This method combines each value of every node in to a string.*/
    public String toString(){
	    Node node = head;
	    String string = "[";
	    while(node != null){
	        string = string + node.getValue() ;

            node = node.next;

	        if(node == null){
                string = string + "]";
            }else{
                string = string + ", ";
            }

        }
	    return string;
    }

    private class Node{
        private Node next;
        private final int value;


        public Node(int value){
            this.value = value;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public int getValue(){
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node){
                return value == ((Node)obj).getValue();
            }
            return false;
        }
    }

}
