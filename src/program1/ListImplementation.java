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
public class ListImplementation {
    
    protected Node head;
    protected long size;
    protected Node tail;
    public Node curr, n;
    
    public ListImplementation()
    {
        /*
        default constructor for initialization of head and tail nodes for list
        */
        size = 0;
        tail = null;
        head = tail;
    }
    /*
    The below method add a node to the list based on asecending order. If the element is smallest 
    than those present in the list, it becomes Head and rest are shifted. If it is argest, it becomes tail.
    */
    public void add(int value)
    {
        Node temp = new Node(value, null);
        int ctr = 0;
        System.out.println("Adding " + value + " to the List");
        if(size == 0){
            head = temp;
            head.setNext(tail);
            size++;            
        }
        else
        {
            curr = head;
            if(head.getElement() > value){
                head = temp;
                head.setNext(curr);
                size++;
            }
            else
            {
                while(curr.getElement() < value && ctr <= size-1){
                    if(ctr <= size - 1){
                                                
                        if(curr.getNext() != null){
                            n = curr;
                            curr = curr.getNext();
                        }
                        ctr++;
                    }
                    
                }
                if(ctr < size){
                    n.setNext(temp);
                    temp.setNext(curr);
                    size++;
                }
                else
                {
                    temp.setNext(null);
                    curr.setNext(temp);
                    tail = temp;
                    size++;
                }
            }
        }
    }
    /*
    The below method deletes from the List based on the Key value provided. It searches through the list for 
    that Key and delete the node from the list.
    */
    public void delete(int key){
        System.out.println("Deleting " + key + " from the List");
        curr = head;
        int ctr = 0;
        if(head.getElement() == key){
            head = curr.getNext();
            curr.setNext(null);
            size--;
        }
        else
        {
            while(curr.getElement() != key && ctr < size){
                n = curr;
                if(curr.getNext() != null){
                    curr = curr.getNext();
                }
                ctr++;
                
            }
            if(ctr < size-1){
                n.setNext(curr.getNext());
                curr.setNext(null);
                size--;
            }
            else
            {
                if(ctr == size - 1 && curr.getElement() == key){
                n.setNext(null);
                tail = n;
                curr.setNext(null);
                size--;
            }
                else
                  System.out.println("Key " + key + " not found in the list");  
            }
        }
        
    }
    /*
    Displays all the nodes present in the List
    */
    public void traverseList(){
        System.out.println("Printing whole List now: ");
        int ctr;
        curr = head;
        for(ctr = 0; ctr <= size; ctr++){
            if(curr != null) {
                System.out.println("Key " + ctr + " = " + curr.getElement());
            
                curr = curr.getNext();
        }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ListImplementation L = new ListImplementation();
        L.add(8);
        L.add(4);
        L.add(1);
        L.add(7);
        L.add(6);
        L.add(15);
        L.add(10);
        L.add(23);
        L.add(13);
        L.add(11);
        L.traverseList();
        L.delete(4);
        L.delete(2);
        L.delete(23);
        L.traverseList();
    }
}
