package com.company.algarithims.linked_list;

public class Main {

    public static void main(String[] args) {
        // this is the testing functionality
        LinkedList linkedList = new LinkedList();

        // adding elements
        linkedList.add(1);
        System.out.println(linkedList);
        linkedList.add(2);
        System.out.println(linkedList);
        linkedList.add(3);
        System.out.println(linkedList);
        linkedList.add(4);
        System.out.println(linkedList);
        linkedList.add(5);
        System.out.println(linkedList);
        linkedList.add(6);
        System.out.println(linkedList);
        linkedList.add(7);
        System.out.println(linkedList);
        linkedList.add(8);
        System.out.println(linkedList);
        linkedList.add(9);
        System.out.println(linkedList);

        //inserting and getting
        int index = 2;
        System.out.println("get("+index+")->"+linkedList.get(index));
        linkedList.insert(index,20);
        System.out.println(linkedList);
        System.out.println("get("+index+")->"+linkedList.get(index));
        System.out.println();
        linkedList.remove(2);// removing the incerted object at index
        System.out.println(linkedList);

        //removing
        linkedList.remove(0);
        System.out.println(linkedList);
        linkedList.remove((linkedList.size()-1));
        System.out.println(linkedList);

        //list like error handeling
        System.out.println("\nintentional error to prove error handling is similer to lists");
        linkedList.remove(linkedList.size());
        System.out.println(linkedList);
    }
}
