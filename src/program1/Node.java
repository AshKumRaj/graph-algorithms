/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program1;

/**
 *
 * @author Ashish
 */
public class Node {
    private int element;
    private Node next;

    public int getElement() {
        return element;
    }

    public void setElement(int newElement) {
        this.element = newElement;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node newNext) {
        this.next = newNext;
    }
    
    
    public Node (int s, Node n)
    {
        element = s;
        next = n;
    }
        
}
