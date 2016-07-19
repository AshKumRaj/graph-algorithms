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
public class Program1 {

void myFunction( int counter)
{
if(counter == 0)
     System.out.println("exiting");
if(counter > 0)
       {
       System.out.println("hello"+counter);
       myFunction(--counter);
       System.out.println(counter);
       
       }
} 
public static void main(String[] args) {
        // TODO code application logic here
    Program1 p = new Program1();
       p.myFunction(-8);
    }

}

    
        
    

