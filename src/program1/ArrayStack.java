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
public class ArrayStack {
    
    public static final int cap = 10;
    protected int top1 = -1, top2 = -1, capacity;
    protected int s1[], s2[];
    public ArrayStack() 
    {
        this.capacity = cap;
    }
    
    public ArrayStack(int cap)
    {
        capacity = cap;
        s1 = new int[capacity];
        s2 = new int[capacity];
    }
    
    public int size(int top)
    {
        return (top + 1);
    }
    
    public boolean isEmpty(int top)
    {
        return (top < 0);
    }
    /*The below method enqueues the item in the queue through Stack1
    */
    
    public void enqueue1(int item, boolean msg) throws FullStackException
    {
       if(msg)
        System.out.println("Item " + item + " enqueued");
       top1+=1;
       s1[top1] = item;
        
    }
    
    /*
    Enqueueing the items into Stack2
    */
    public void enqueue2(int item) throws FullStackException
    {
        if(size(top2) == capacity)
            throw new FullStackException("Queue is Full");
        s2[++top2] = item;
        
    }
    /*
    popping items from Stack2
    */
    public void popItemFromStack2(int item)
    {
        if(s2[top2] == item)
        {
            s2[top2] = 0;
            top2--;
        }
        else
            System.out.println("Item not eligible for dequeuing");
    }
    
    /*
    popping items from Stack1
    */
    public void popItemFromStack1(int item)
    {
        if(s1[top1] == item)
        {
            s1[top1] = 0;
            top1=top1-1;
        }
        else
            System.out.println("Item not eligible for dequeuing");
    }
    /*
    Whenever we need to dequeue any item from the queue, we pop all items from Stack1 and push to Stack2;
    push the item to be dequeued out from tack2 and then pop all remaining items from Stack2 and push 
    to Stack1
    */
    public void dequeueAllFromStack1() throws FullStackException
    {
        while(top1 != -1){
            enqueue2(s1[top1]);
            popItemFromStack1(s1[top1]);
        }
    }
    
    public void dequeueAllFromStack2() throws FullStackException
    {
        do
        {
//            enqueue2(s1[top1]);
            System.out.println("Item " + s2[top2] + " Dequeued  ");
            popItemFromStack2(s2[top2]);
            
            
        }while(top2 != -1);
    }
    
    public void dequeueAll() throws FullStackException
    {
        dequeueAllFromStack1();
        dequeueAllFromStack2();
        System.out.println(" Queue is now empty");
    }
    
    public void dequeueSelected() throws FullStackException
    {
        dequeueAllFromStack1();
        System.out.println("Item " + s2[top2] + " Dequeued : Remaining list is as below");
        popItemFromStack2(s2[top2]);
        do
        {
            enqueue1(s2[top2], false);
            System.out.print(s2[top2] + "   ");
            popItemFromStack2(s2[top2]);
        }while(top2 != -1);
        
    }
    
    public static void main(String[] args) throws FullStackException {
        // TODO code application logic here
        ArrayStack A = new ArrayStack(10);
        A.enqueue1(7, true);
        A.enqueue1(6, true);
        A.enqueue1(5, true);
        A.dequeueSelected();
        A.enqueue1(1, true);
        A.enqueue1(9, true);
        A.dequeueSelected();
        A.enqueue1(13, true);
        A.enqueue1(18, true);
        A.dequeueSelected();
        A.dequeueSelected();
        A.dequeueSelected();
//        A.dequeueAll();
    }

    private static class FullStackException extends Exception {

        public FullStackException(String stack_is_Full) {
        }
    }
    
}
