/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class Kruskals {
    int[][] adjMatrix = new int[10][10];
    int[] resultingArray = new int[10];
    int[] edgeStore = new int[20];
    int[] spTree = new int[20];
    int[] weight = new int[10];
    int[] tr = new int[20];
    int minVertex = 0, c = 0, minVertex1 = 0, minVertex2 = 0, rc = 0, wc = 0, sc = 0, head = 0;
    int[] stack = new int[20];
    
    /*implementation of adjacent matrix here
    
    */
    void implementAdjacentMatrix(){
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                adjMatrix[i][j] = 0;
        for(int i=0;i<10;i++)
            fillRows(i);
        System.out.println("The adjacency matrix for the given graph is as below");
        System.out.println();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++)
                System.out.print(adjMatrix[i][j]+"  ");
            System.out.println();
        }
    }
    
    /*finding the minimum wieght present in the given edges of graphs
    
    */
    int findnextMin(){
        int min = 100;
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                if(adjMatrix[i][j] > 0 && adjMatrix[i][j] < min){
                    min = adjMatrix[i][j];
                    minVertex1 = i;
                    minVertex2 = j;
                }
            }
        return min;
    }
    
    /*Kuskals logic implementation.
    Check the vertices for minimum edge and see if it forms a cycle on including that edge. Store the 
    wieght in a separate array to show it later.
    */
    
    void implementKruskal(){
        while(wc != 9){
        int currentMin = findnextMin();
        boolean check;
        if (minVertex1 > minVertex2) {
            minVertex = minVertex2;
            
            check = checkCycle(minVertex);
        }
        else{
            minVertex = minVertex1;
            
            check = checkCycle(minVertex);
        }
        if(!check){//if cycle do not exist for this pair of vertices
            if(!presentInResult(minVertex1)){
                resultingArray[rc] = minVertex1;
                rc++;
                
                }
            if(!presentInResult(minVertex2)){
            resultingArray[rc] = minVertex2;
            rc++;
            
            }
            
            weight[wc] = currentMin;
            wc++;
            spTree[sc] = minVertex1;
            sc++;
            spTree[sc] = minVertex2;
            sc++;
            
        }
        adjMatrix[minVertex1][minVertex2] = -1;//marking these weights as done
        adjMatrix[minVertex2][minVertex1] = -1;//marking these weights as done
        clearStack();
        }
    }
    
    boolean checkCycle(int vertex){
        int oldNode = vertex;
        int newNode = next(oldNode);
        stack[c] = newNode;
        c++;
        tr[head] = newNode;
        head++;
        if(c == 1 ){
            stack[c] = minVertex;
            c++;
            tr[head] = minVertex;
            head++;
        }
        while(newNode != -2){
            if(newNode != minVertex1 && newNode != minVertex2 && newNode != 100){
                oldNode = newNode;
                newNode = next(oldNode);
                stack[c] = newNode;
                c++;
                tr[head] = newNode;
                head++;
            }else
                return true;
            if(newNode == -2)
                return false;
        }
        if(newNode == -2)
                return false;
                            
        return true;
    }
    /*gets the next node for given vertex
    
    */
    int next(int node){
        int nextNode = -2;
        for(int i=0;i<sc;i++){
            if(spTree[i] == node){
                if(i%2 == 0)
                    if(presentInResult(spTree[i+1]) && !presentInStack(spTree[i+1]))
                        return spTree[i+1];
                if(i%2 == 1)
                    if(presentInResult(spTree[i-1]) && !presentInStack(spTree[i-1]))     
                        return spTree[i-1];
                            
            }
        }
        if(nextNode == -2 && c != 0){
              c--;
        if(checkCycle(stack[c]))
              return 100;
        }
        return nextNode; 
    }
    
    boolean presentInResult(int n){
        for(int i=0;i<10;i++){
            if(resultingArray[i] == n)
                return true;
        }
        return false;
    }
    
    boolean presentInStack(int n){
        for(int i=0;i<20;i++){
            if(tr[i] == n)
                return true;
        }
        return false;
    }
    
    void clearStack(){
        for(int i=0;i<20;i++){
            stack[i]=-3;
            tr[i]=-3;
            
        }
        c = 0;
        head = 0;
    }

    private void fillRows(int i) {
        switch(i){
            case 0: adjMatrix[i][1] = 5;
                    adjMatrix[i][4] = 3;
                    adjMatrix[i][5] = 9;
                    break;
                
            case 1: adjMatrix[i][0] = 5;
                    adjMatrix[i][2] = 12;
                    adjMatrix[i][5] = 2;
                    adjMatrix[i][6] = 6;
                    break;
                
            case 2: adjMatrix[i][1] = 12;
                    adjMatrix[i][3] = 4;
                    adjMatrix[i][6] = 7;
                    adjMatrix[i][7] = 8;
                    break;
                    
            case 3: adjMatrix[i][2] = 4;
                    adjMatrix[i][7] = 5;
                    break;
                
            case 4: adjMatrix[i][0] = 3;
                    adjMatrix[i][5] = 5;
                    adjMatrix[i][8] = 10;
                    break;
                
            case 5: adjMatrix[i][0] = 9;
                    adjMatrix[i][1] = 2;
                    adjMatrix[i][4] = 5;
                    adjMatrix[i][6] = 7;
                    adjMatrix[i][8] = 12;
                    adjMatrix[i][9] = 10;
                    break;
                
            case 6: adjMatrix[i][1] = 6;
                    adjMatrix[i][2] = 7;
                    adjMatrix[i][5] = 7;
                    adjMatrix[i][7] = 9;
                    adjMatrix[i][8] = 12;
                    adjMatrix[i][9] = 6;
                    break;
                
            case 7: adjMatrix[i][2] = 8;
                    adjMatrix[i][3] = 5;
                    adjMatrix[i][6] = 9;
                    adjMatrix[i][9] = 4;
                    break;
                
            case 8: adjMatrix[i][4] = 10;
                    adjMatrix[i][5] = 12;
                    adjMatrix[i][6] = 12;
                    adjMatrix[i][9] = 3;
                    break;
                
            case 9: adjMatrix[i][5] = 10;
                    adjMatrix[i][6] = 6;
                    adjMatrix[i][7] = 4;
                    adjMatrix[i][8] = 3;
                    break;
               
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Kruskals k = new Kruskals();
        k.implementAdjacentMatrix();
        k.implementKruskal();
        k.printresult();
    }

    private void printresult() {
        int wt = 0;
        System.out.println("The resulting Minimum spanning tree edges with weights are as below:");
        System.out.println("(Note: 15 should be read as Edge connecting vertices 1 and 5 and so on)");
        System.out.println();
        for(int i=0;i<sc;i=i+2){
            System.out.print(spTree[i]);
            System.out.print(spTree[i+1]+"-->");
            System.out.println(weight[i/2]);
            
        }
        for(int j=0;j<10;j++)
            wt = wt + weight[j];
        System.out.println("Total weight = " + wt);
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                adjMatrix[i][j] = 0;
        for(int i=0;i<sc;i=i+2){
            adjMatrix[spTree[i]][spTree[i+1]] = weight[i/2];
            adjMatrix[spTree[i+1]][spTree[i]] = weight[i/2];
        }
        System.out.println("The Resulting MST matrix for the given graph is as below");
        System.out.println();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++)
                System.out.print(adjMatrix[i][j]+"  ");
            System.out.println();
        }
    }
    
}
