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
public class BinarySearchTree {
    public boolean isBST = true;
//Entering the array containing integer elements to be checked for Binary Search Tree    
    public int[] a = {50,35,65,20,-1,60,75,10,25,-1,-1,58,63,-1,80,8,-1,23,27,-1,-1,-1,-1,55,56,62,64,-1,-1,79,81};
    public int[] b = new int[a.length + 1];//creating new array to store contents of array a for checking purposes
    public int j;
    
    //This function checks for BST validatin of entered array
    public void isArrayBST(){
        b[0] = a.length;        // Initial value at b[0] is stored as size of array a
        for (int i=1; i<=a.length; i++)
            b[i] = a[i-1];
        //This snippet checks for left and right child of an element. For an array to be BST, for
        //each element, it should be greater than its left child and less than its right child
        for (int i=1; i<=b[0]/2;i++){
            if(b[i] > 0){
                j = 2*i;
                if(b[j] > 0 && b[j+1] > 0){
                    if(b[i] < b[j] || b[i] > b[j+1]){
                        isBST = false;
                        //printing confirmation message of BST validation
                        System.out.println("The given array does not form a Binary Search Tree");
                        break;
                        
                    }
                }
            }
        }
        
        //printing confirmation message of BST validation
        if(isBST)
            System.out.println("The given array forms a Binary Search Tree");
        
    }
          
    public static void main(String[] args) {
        // TODO code application logic here
       BinarySearchTree B = new BinarySearchTree();
       B.isArrayBST();
    }
}
