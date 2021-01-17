package com.company.algarithims.binary_search_tree;

/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Brian Dunne
 *
 *************************************************************************/


public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST
    private char deleteMode; // number representing mode, L = delete from left child , R = delete from right child, other = crude balancing( use B to be intuitive)

    public BST(){ this('L');} // default left as requested
    public BST(char mode){
        this.deleteMode =mode;
    }
    /**
     * Private node class.
     */
    private class Node {
        private final Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: TODO
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {return (-1)+heightSearch(root);}
    private int heightSearch(Node node){
        if(node == null) return 0;
        return Math.max(heightSearch(node.left), heightSearch(node.right))+1;
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {return isEmpty()?  null:  searchByRank(root, size(root.left)+1, (size(root)+1)/2).key; }
    private Node searchByRank(Node node, int rank, int target){
        if(rank == target) return node;
        else if(rank > target) return searchByRank(node.left, (rank-size(node.left.right))-1, target);
        else return searchByRank(node.right, (rank+size(node.right.left))+1, target);
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {return printKeysInOrderBuilder(this.root);}
    private String printKeysInOrderBuilder(Node node){
        if(node == null)return "()";
        return '(' +
                printKeysInOrderBuilder(node.left) +
                node.key +
                printKeysInOrderBuilder(node.right)+")";
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
      return prettyStringKeysBuilder(this.root,"");
    }
    private String prettyStringKeysBuilder(Node node, String prefix){
        if(node==null) return prefix+"-null\n";
        return prefix+"-"+node.key+"\n"
                + prettyStringKeysBuilder(node.left, prefix+" |")
                + prettyStringKeysBuilder(node.right, prefix+"  ");
    }

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
        root = deleteNode(this.root, key);
    }

    private Node deleteNode(Node x, Key key) {
        if (x == null) return null; //if it never existed
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = deleteNode(x.left,  key);
        else if (cmp > 0) x.right = deleteNode(x.right, key);
        else {
            if(x.right==null){
                return x.left;
            }else if(x.left==null){
                return  x.right;
            }else{
                //child replace method
                Node h = x;
                if((deleteMode!='R'&&(x.right.N<x.left.N))||deleteMode=='L'){
                    x = searchByRank(x.left, size(x.left.left)+1,x.left.N);
                    x.left = removeMost(h.left);
                    x.right = h.right;
                    //do left max
                }else{
                    x = searchByRank(x.right, size(x.right.left)+1,1);
                    x.right = removeLeast(h.right);
                    x.left = h.left;
                    // do right min
                }
            }

        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node removeLeast(Node node){
        if(node.left==null) return null;
        node.left = removeLeast(node.left);
        node.N = 1 + size(node.left) + size(node.right);
        return node;
    }
    private Node removeMost(Node node){
        if(node.right==null) return null;
        node.right = removeMost(node.right);
        node.N = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public String toString() {
        return prettyPrintKeys();
    }
}
