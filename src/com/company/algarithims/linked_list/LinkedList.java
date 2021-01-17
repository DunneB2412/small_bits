package com.company.algarithims.linked_list;

public class LinkedList
{
    private int listCount;
    private Node head;

    public LinkedList()
    {
        head = new Node();
        listCount = 0;
    }
    public void add(int data){
        if(size()==0){
            head.setNextNode(new Node(data));
        }else {
            Node last = get(size() - 1);
            last.setNextNode(new Node(data));
        }
    }
    public void insert(int index, int data){
        Node prevoins = get(index-1);
        Node next = get(index);
        prevoins.setNextNode(new Node(data, next));
    }
    public Node get(int index){
        if(index>=size()||index<0){
            throw new IndexOutOfBoundsException("indexing <"+index+"> with <"+ size()+"> elements");
        }
        Node start = head;
        Node last = start;
        int counter = 0;
        while(last != null && counter<index){
            last = start.getNextNode();
            start = last;
            counter ++;
        }
        return  start;
    }
    public void remove(int index){
        get(index);
        if(index == 0){
            head = get(1);
            return;
        }
        Node prevoins = get(index-1);
        Node next;
        try {
            next = get(index + 1);
        }
        catch (Exception e){
            next = null;
        }
        prevoins.setNextNode(next);
    }
    public int size(){
        Node start = head;
        Node last;
        int counter = 0;
        do{
            last = start.getNextNode();
            start = last;
            counter ++;
        }while(last != null);
        return  counter;
    }
    @Override
    public String toString(){
        String sout = "<"+size()+">[";
        for(int i=0; i< size(); i++){
            sout+= get(i);
            if(i<size()-1){
                sout+=",";
            }
        }
        sout+="]";
        return sout;
    }

}
