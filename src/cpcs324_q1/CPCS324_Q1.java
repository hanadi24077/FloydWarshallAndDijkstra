
package cpcs324_q1;

import java.util.*;
import java.lang.*;
import java.io.*;
public class CPCS324_Q1 {
    
    


    final static int INF = 99999, V = 10 ,s = 0, d = 3, NILL = -1;
   
  
    void floydWarshall(int graph[][])
    {
         // store the shortest distance from src to i
        int dist[][] = new int[V][V];
        int i, j, k;
  
        // Initialize the solution matrix same as input graph matrix.
       
        
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];
  
        
        // Add all vertices one by one to the set of intermediate vertices.
        
        for (k = 0; k < V; k++)
        {
                         System.out.println("----D("+k+")-----");
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                    //print it     
                    if (dist[i][j] == INF) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(dist[i][j] + "   ");
                    }
                }
                System.out.println();
            }

        }
  
        // Print the shortest distance matrix
        printSolution(dist);
    }
    
  
    void printSolution(int dist[][])
    {
        System.out.println("\n \nThe following matrix shows the shortest "+
                         "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }
    
    
    
    
    
    
     static void Dijkstra(int[][] Graph, int n, int _s, int _d)
    {
 
        int i, u, v, count;
        int[] dist = new int[n];
        int[] Blackened = new int[n];
        int[] pathlength = new int[n];
        int[] parent = new int[n];
 
        // The parent Of the source vertex is always equal to nill
        parent[_s] = NILL;
 
        // first, we initialize all distances to infinity.
        for (i = 0; i < n; i++)
            dist[i] = INF;
 
         // Distance of source vertex from itself is always 0
        dist[_s] = 0;
        
         // Find shortest path for all vertices
        for (count = 0; count < n - 1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices not yet processed. u is always equal to src in first iteration
            u = MinDistance(dist, Blackened);
 
            // if MinDistance() returns INFINITY, then it means that the source vertex is not a root
            if (u == INF)
                break;
            else
            {
                // Mark the picked vertex as Blackened
                Blackened[u] = 1;
                
                 // Update dist value of the adjacent vertices of the picked vertex.
                for (v = 0; v < n; v++)
                {
                    /* Update dist[v] only if is not picked yet, there is an
                       edge from u to v, and total weight of path from src to 
                      v through u is smaller than current value of dist[v]*/
                    
                    
                    if (Blackened[v] == 0 && Graph[u][v] != 0
                        && dist[u] + Graph[u][v] < dist[v])
                    {
                        parent[v] = u;
                        pathlength[v] = pathlength[parent[v]] + 1;
                        dist[v] = dist[u] + Graph[u][v];
                    }
                    else if (Blackened[v] == 0 && Graph[u][v] != 0
                            && dist[u] + Graph[u][v] == dist[v]
                            && pathlength[u] + 1 < pathlength[v])
                    {
                        parent[v] = u;
                        pathlength[v] = pathlength[u] + 1;
                    }
                }
            }
        }
        
        
      for(int o=0;o<n;o++){
        // Printing the path
       
        if (dist[o] != INF){
            System.out.println("The Path from the source verticx = 0 to "+o);
            PrintPath(parent, o);
            System.out.print("  |---length = " + dist[o]);}
       
        else{
            System.out.println("There is not path between vertex " +
                                _s + " to vertex " + _d);}
        System.out.println();
    }
    }
 
     
     // A function to find the vertex with minimum distance value, from the set of vertices not yet included in shortest path tree
    static int MinDistance(int[] dist, int[] Blackened)
    {
         // Initialize min value
        int min = INF, min_index = -1, v;
        for (v = 0; v < V; v++)
            if (Blackened[v] == 0 && dist[v] < min)
            {
                min = dist[v];
                min_index = v;
            }
        return min == INF ? INF : min_index;
    }
 
    // Function to print the path
    static void PrintPath(int[] parent, int _d)
    {

        if (parent[_d] == NILL)
        {
            System.out.print(_d);
            
            return;
        }
        PrintPath(parent, parent[_d]);
      
        System.out.print("->" + _d);
        
    }
 
  
    // Driver program to test above function
    public static void main (String[] args)
    {
         Scanner input = new Scanner(System.in);
        
        //create a graph 
                          //1
        int graph[][] = { {0  , 10,  INF, INF,INF ,5  ,INF,INF,INF, INF},
                          //2
                          {INF, 0 ,   3, INF, 3  ,INF ,INF,INF,INF,INF},
                          //3
                          {INF, INF, 0,  4  , INF,INF,INF,5,INF,INF},
                          //4
                          {INF  , INF,  INF, 0,INF ,INF  ,INF,INF,4, INF},
                          //5
                           {INF  , INF,  4, INF,0 ,INF  ,2,INF,INF, INF},
                           //6
                           {INF  , 3,  INF, INF,INF ,0  ,INF,INF,INF, 2},
                           //7
                           {INF  , INF, INF , 7,INF ,INF  ,0,INF,INF, INF},
                           //8
                           {INF  , INF,  INF, 4,INF ,INF  ,INF,0,3, INF},
                           //9
                           {INF  , INF,  INF, INF,INF ,INF  ,INF,INF,0, INF},
                           //10
                           {INF  , 6,  INF, INF,INF ,INF  ,8,INF,INF, 0}
                
                        };
        
        //intialize an object of the class
        CPCS324_Q1 a = new CPCS324_Q1();
        
      
        loop: while(true){
              System.out.println("-----------------------------------------------------------\nPlease choose which algorithm you want :\n"
                + "1- floyd Warshall\n"
                + "2- Dijkstra\n"
        +"3- Break");
              
        int choise = input.nextInt();
        
        switch(choise){
            case 1:
                 // start time
                double initial_time4 = System.currentTimeMillis();
                // calling the funcation 
                   a.floydWarshall(graph);
                   //finish time of the algorithm
                double final_time4 = System.currentTimeMillis();

                //print the total time consumed by the algorithm
                System.out.println("Total runtime of floyd Warshall's Algorithm: " + (final_time4 - initial_time4) + " ms.");
                break;
            case 2:
                  // start time
                double initial_time2 = System.currentTimeMillis();
                
                //calling the funcation 
                a.Dijkstra(graph, V, s, d);
                
                    //finish time of the algorithm
                double final_time2 = System.currentTimeMillis();

                //print the total time consumed by the algorithm
                System.out.println("Total runtime of  Dijkstra's Algorithm: " + (final_time2 - initial_time2) + " ms.");
                break;
                
            case 3:
                break loop;
        }
  
   
     
        
    }

    }  
        
    
}
