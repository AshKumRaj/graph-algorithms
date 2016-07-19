/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 *
 * @author Ashish
 */
public class TertiarySearch {
    public boolean exitfunc = false;
    public int k, count = 0, index, pos =0;
    //The below tertiary search function takes the array, array size and the element to be searched as the arguments and returns the INDEX
    //at which th element occurs in the array. The output shows the POSITION which is calculated by INDEX + 1 
    public int tertiarySearchElement(int a[], int n, int e){
        if((count <= index) && !exitfunc){
        int b[] = new int[a.length];
        index = (count == 0) ? a.length : index;
        int j = (n > 2) ? n/3 : 1;
        int i = (n > 2) ? j : 0;
        if(a[i] == e){      //checking at the first divider position, i.e. n/3
             exitfunc = true;
             count++;
             pos = pos + i;
             return pos;
             
        }
        if(!exitfunc)
        if(a[i + j] == e){      //checking at the second divider position, i.e. 2n/3
            exitfunc = true;
            count++;
            
            pos = pos + j + i;
            return pos;
        }
        else{
            if(!exitfunc){
            if(a[i] > e){
               for (k = 0; k <= i; k++){        //constucting new array to be sent in recursive function
                   b[k] = a[k];
               } 
               count++;
               
               tertiarySearchElement(b, k-1, e);        //recursive call
               
            }
            if(a[i] < e && a[i+j] > e){
                for (k = i+1; k <= i+j; k++){       //constructing new array to be sent in recursive function
                   b[k-i-1] = a[k];
               } 
               count++;
               pos = pos + j + 1;
               tertiarySearchElement(b, k-i-1, e);      //recursive call
               
            }
            if(a[i+j] < e){
                for (k = i+j+1; k <= n-1; k++){     //constructing new array to be sent in recursive function
                   b[k-i-j-1] = a[k];
               } 
               count++;
               pos = pos + 2*j + 1;
               tertiarySearchElement(b, k-i-j-1, e);       //recursive call
               
            }
            }   
        }   
        }
        return (count >= index ? index*2 : pos);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[] a = {3,7,8,9,10,11,12,13,15,16,21,23,24,25,26,27,28,29,30,31,32,34,35,37,38,39,40,41,42,48};
        int foundAt, e = 33;    
        TertiarySearch T = new TertiarySearch();
        foundAt = T.tertiarySearchElement(a, a.length, e) +1;
        if(foundAt > a.length + 1)
            System.out.println("Element " + e + " is not present in the given array");
        else{
            if(foundAt == 1)
                System.out.println("Element " + e + " is the FIRST element in the array");
            if(foundAt == a.length)
                System.out.println("Element " + e + " is the LAST element in the array");
            if(foundAt > 1 && foundAt < a.length)
                System.out.println("Element " + e + " is at position " + foundAt);
        }
      }
}
