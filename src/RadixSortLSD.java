/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class RadixSortLSD {
    //defining 20 numbered array
    int a[] = {9870, 9903, 4456, 9865, 1189, 2302, 3505, 4678, 2971, 8905, 3409, 2398, 1111, 3333, 7333, 9444, 5555, 6666, 8777, 7888};
    int[] res = new int[20];
    int pass = 1, rem = 0;
    /* This method implements radix sort. It passes each time the result array stored in res[] and takes the Least Significant Digit and arranges the
    numbers in result array according to the LSD obtained
    */
    void implementRadixSort(){
        System.out.println("The unsorted array of 20 numbers is as follows:");
        for(int i=0;i<20;i++)
            System.out.print(a[i]+" ");
        System.out.println();
        passArray();//first pass
        System.arraycopy(res, 0, a, 0, 20);
        pass++;
        System.out.println("The sorted array after first pass is as follows:");
        for(int i=0;i<20;i++)
            System.out.print(res[i]+" ");
        System.out.println();
        passArray();//second pass
        System.arraycopy(res, 0, a, 0, 20);
        pass++;
        System.out.println("The sorted array after second pass is as follows:");
        for(int i=0;i<20;i++)
            System.out.print(res[i]+" ");
        System.out.println();
        passArray();//third pass
        System.arraycopy(res, 0, a, 0, 20);
        pass++;
        System.out.println("The sorted array after third pass is as follows:");
        for(int i=0;i<20;i++)
            System.out.print(res[i]+" ");
        System.out.println();
        passArray();//fourth pass
        System.arraycopy(res, 0, a, 0, 20);
        System.out.println("The sorted array after LSD Radix sort is as follows:");
        for(int i=0;i<20;i++)
            System.out.print(res[i]+" ");
    }

    void passArray(){
        int pos = 0;
        for(int j=0;j<10;j++){
            for(int i=0;i<20;i++){
                rem = (int) (a[i]%(Math.pow(10, pass)));
                rem = (int) (rem/(Math.pow(10, (pass-1))));
                if(rem == j){
                    res[pos] = a[i];
                    pos++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        RadixSortLSD r = new RadixSortLSD();
        r.implementRadixSort();
    }
}
