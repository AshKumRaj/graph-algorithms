/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment3;

/**
 *
 * @author Ashish
 */
public class HeapSort {
    public int b[] = {20, 19, 18, 17, 164, 25, 14, 13, 120, 70, 10, 9, 8, 71, 6, 5, 4, 3, 2, 1};
    public int a[] = {b.length, 22, 19, 18, 17, 164, 25, 14, 13, 120, 70, 10, 9, 8, 71, 6, 5, 4, 3, 2, 1};
    boolean checkChild(int index){
        int temp = a[index];
        if(index <= a.length/2)
            if((2 * index + 1) > a.length-1){
                if(a[index] <= a[2*index]){
                    a[index] = a[2*index];
                    a[2*index] = temp;
                }
                return true;
            }
            else {
                if(a[index] <= a[2*index] || a[index] <= a[2*index + 1]){
                    if(a[2*index] > a[2*index + 1]){
                        a[index] = a[2*index];
                        a[2*index] = temp;
                    }
                    else {
                        a[index] = a[2*index + 1];
                        a[2*index + 1] = temp;
                    }   
                
                    return true;
            
                }
            }
        
        return false;
    }
    void heapSorting(){
        int size = a.length-1;
        boolean call;
        for(int i = size/2; i>=1; i--){
            call = checkChild(i);
                if(call){
                    setCildItems(i+1);
            }
        }
        int temp = a[size];
        a[size] = a[1];
        a[1] = temp;
        while(size != 1){
            size--;
            for(int i=1; i<= size; i++){
                checkChild(i);
               }
            temp = a[size];
            a[size] = a[1];
            a[1] = temp;
            System.out.println();
            for(int j=1; j < a.length; j++)
            System.out.print(a[j]+" ");
        }
        System.out.println("Sorted array as follows");
        for(int i=1; i < a.length; i++)
            System.out.println(a[i]);
    }    

    void setCildItems(int i) {
        boolean callAgain;
        while(i <= a.length/2){
            callAgain = checkChild(i);
            if(callAgain)
                setCildItems(i+1);
            i++;
        }
            
    }
    public static void main(String[] args) {
        // TODO code application logic here
        HeapSort h = new HeapSort();
        h.heapSorting();
    }
}
