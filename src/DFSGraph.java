/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class DFSGraph {
    int[][] graphArray = new int[10][10];
    int[] result = new int[10];
    int[] stack = new int[10];
    int rsize = 0, ssize = 0;
    int top = stack[0];
    
    void push(int element){
        ssize++;
        stack[ssize-1] = element;
        top = stack[ssize-1];
    }
    
    void pop(){
        ssize--;
        top = stack[ssize];
    }
    
    /*
    This method checks if the vertices linked to a particular vertex are visitied or not
    */
    boolean checkElementVisited(int element){  
        for(int i =0; i < 10; i++){
            if(result[i] == element)
                return true;
            }
        return false;
    }
    
    /*
    This method gets the next vertex from the list of all vertices attached to a given vertex. Like, if 2, 4, 7 are connected to vertex 1, then it 
    returns the minimum unvisited vertex of those vertices. If all are visited, it returns 11.
    */
    int getNextVertex(int n){
        boolean b = true;
        for(int j = 1; graphArray[n-1][j] != 0 && b ; j++){
            b = checkElementVisited(graphArray[n-1][j]);
                if(!b){
                    return graphArray[n-1][j];
                }
        }
        return 11;//means all elements of this vettex are visited
    }
    
    /*
    This method implements the Depth First Search traversal of the graph as shown in attached pic.
    */
    void implementDFS(){
        int n = graphArray[0][0], n2, i = 1;
        push(n);
        result[0] = n;
        
        while(ssize != 0){
            n2 = getNextVertex(n);
            if(n2>10){
                pop();//reduce size also in stack pop function by 1
                n = top;
            } else
            {
                push(n2);//increase size by 1 in stack push funtion itself
                n = n2;
                result[i] = n2;
                i++;
            }
        }
    }
    
    void printResult(){
        for(int i =0; i<10; i++)
            System.out.print(result[i] + " --> ");
    }
    
    void implementGraph(){
        for(int i = 0; i < 10; i++){
            graphArray[i][0] = i+1;
            createList(i);
        }
    }

    private void createList(int i) {
        switch(i){
            case 0: graphArray[i][1] = 2;
                    graphArray[i][2] = 4;
                    graphArray[i][3] = 7;
                    graphArray[i][4] = 0;
                    break;
                
            case 1: graphArray[i][1] = 1;
                    graphArray[i][2] = 4;
                    graphArray[i][3] = 5;
                    graphArray[i][4] = 6;
                    graphArray[i][5] = 7;
                    graphArray[i][6] = 9;
                    graphArray[i][7] = 0;
                    break;
                
            case 2: graphArray[i][1] = 6;
                    graphArray[i][2] = 7;
                    graphArray[i][3] = 0;
                    break;
                    
            case 3: graphArray[i][1] = 1;
                    graphArray[i][2] = 2;
                    graphArray[i][3] = 8;
                    graphArray[i][4] = 9;
                    graphArray[i][5] = 0;
                    break;
                
            case 4: graphArray[i][1] = 2;
                    graphArray[i][2] = 6;
                    graphArray[i][3] = 9;
                    graphArray[i][4] = 10;
                    graphArray[i][5] = 0;
                    break;
                
            case 5: graphArray[i][1] = 2;
                    graphArray[i][2] = 3;
                    graphArray[i][3] = 5;
                    graphArray[i][4] = 7;
                    graphArray[i][5] = 9;
                    graphArray[i][6] = 0;
                    break;
                
            case 6: graphArray[i][1] = 1;
                    graphArray[i][2] = 2;
                    graphArray[i][3] = 3;
                    graphArray[i][4] = 6;
                    graphArray[i][5] = 0;
                    break;
                
            case 7: graphArray[i][1] = 4;
                    graphArray[i][2] = 9;
                    graphArray[i][3] = 10;
                    graphArray[i][4] = 0;
                    break;
                
            case 8: graphArray[i][1] = 2;
                    graphArray[i][2] = 5;
                    graphArray[i][3] = 6;
                    graphArray[i][4] = 8;
                    graphArray[i][5] = 10;
                    graphArray[i][6] = 0;
                    break;
                
            case 9: graphArray[i][1] = 5;
                    graphArray[i][2] = 8;
                    graphArray[i][3] = 9;
                    graphArray[i][4] = 0;
                    break;
                
        }
    }
    
    void printGraphArray(){
        for(int i=0;i<10;i++){
            for(int j=0;graphArray[i][j]!=0;j++){
                System.out.print(graphArray[i][j]+" ");
                if(j==0)
                    System.out.print("--> ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        DFSGraph D = new DFSGraph();
        D.implementGraph(); //implementing graph as attached in figure
        System.out.println("Graph in adjacent list format");
        D.printGraphArray();    //showing adjacent representation of graph using array
        D.implementDFS();
        System.out.println("Resulting vertex array after DFS traversal");
        D.printResult();
    }
    
}
