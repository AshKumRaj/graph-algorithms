
import java.util.ArrayList;
import java.util.List;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class BinaryTree {
    
 
public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public  class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
}
 }

