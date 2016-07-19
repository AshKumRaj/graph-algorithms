/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class Heaping {
    public int b[] = {20, 191, 108, 117, 164, 25, 134, 13, 10, 70, 110, 1, 56, 64, 8, 71, 6, 24, 22, 81};
    public int a[] = {b.length, 20, 191, 108, 117, 164, 25, 134, 13, 10, 70, 110, 1, 56, 64, 8, 71, 6, 24, 22, 81};
    boolean checkChild(int index){
        /*This method checks check the children of parent index 
        and swap the greatest of two*/
        int temp = a[index];
        if(index <= a.length/2)
            if((2 * index + 1) > a.length-1){
                if(a[index] <= a[2*index]){
                    a[index] = a[2*index];
                    a[2*index] = temp;
                    return true;
               }
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
        System.out.println("Array before Heap sorting is as follows");
        for(int i=1; i < a.length; i++)
            System.out.print(a[i]+" ");
        System.out.println();
//        Heapify begins here
        for(int i = size/2; i>=1; i--){
            call = checkChild(i);
                if(call){
                    setChildItems(size/2, i+1);
                }
            }
//        Swapping the last and first term
        int temp = a[size];
        a[size] = a[1];
        a[1] = temp;
//        Uncomment the below portion to see step by step procedure for debugging
//        System.out.println("Heapified array as follows");
//        for(int i=1; i < a.length; i++)
//            System.out.print(a[i]+" ");
        
//        Sorting begins now after array is heapified and first and last 
//        elements are swapped. The heaping order is disturbed now
//        So we need to fix that now step by step
        while(size != 1){
            size--;
            for(int i=1; i<= size/2; i++){
                checkChildSort(i, size);
               }
            temp = a[size];
            a[size] = a[1];
            a[1] = temp;
//            Uncomment the below portion to see step by step procedure for debugging
//            System.out.println("for size="+size);
//            for(int j=1; j < a.length; j++)
//            System.out.print(a[j]+" ");
        }
        System.out.println("Sorted array as follows");
        for(int i=1; i < a.length; i++)
            System.out.print(a[i]+" ");     //Printing the final sorted array
    }    

    void setChildItems(int i, int j) {
        boolean callAgain;
        for(int k = i; k >= j; k--)
        {
            callAgain = checkChild(k);
            if(callAgain)
                setChildItems(i,k);
        }
            
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Heaping h = new Heaping();
        h.heapSorting();
    }

    void checkChildSort(int i, int size) {
        int temp = a[i];
        if(i <= size/2){
            if((2 * i + 1) > size-1 && size%2==0){
                if(a[i] <= a[2*i]){
                    a[i] = a[2*i];
                    a[2*i] = temp;
                }
            }
            else {
                if(a[i] <= a[2*i] || a[i] <= a[2*i + 1]){
                    if(a[2*i] > a[2*i + 1]){
                        a[i] = a[2*i];
                        a[2*i] = temp;
                    }
                    else {
                        a[i] = a[2*i + 1];
                        a[2*i + 1] = temp;
                    } 
                }
            }
        }  
    }
}
