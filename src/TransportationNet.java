
//import javax.xml.soap.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*This is a simple edge class to represent the edges. I declare start city and end city as integers because I will be
getting the index value of it from the cities txt file. */
class Edge {
    public int start;
    public int end;
    public int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

}
public class TransportationNet {
    FileWriter write;
    int vertex;
    int weight;
    int parent[];
    int arr[][]; //2d array of edges
    int distance[];
    Node source;
    String city[]; // 1d array of cities
    String[] spl; // array that i split the file into
    PriorityQueue < Node > pq;
    ArrayList < Edge > totalEdges = new ArrayList < > (); //Array list to track my total edges
    ArrayList < Edge > mst = new ArrayList < > (); // Array list to hold my edges


    public TransportationNet(int vertex) {
        this.vertex = vertex;
        city = new String[vertex];
        distance = new int[vertex];
        PriorityQueue < Edge > pQueue = new PriorityQueue < > ();
    }
    /* I attempted to implement the Dijkstra algorithm, but I did not understand it enough to code it. I know that
     I would need a Node class, and a compare method, but that was pretty much it.This is my first time using priority Queue,
      so I ran into trouble with the syntax. The most I was able to was write the algorithm out in pseudocode.


    1. Add starting point to the queue and set distance = 0;
    2.while nodes visited != the number of vertices, remove the minimum distance node from priority queue, and add it
    to separate list.
    3. Repeat this process until the priority queue is empty.
     */
    public void findShortestPath(int v) {
        for (int i = 0; i < vertex; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
         //pq.add(new Node(source, 0));
    }
    /*This method adds an edge to my totalEdges array list*/
    public void addEdge(int start, int end, int weight) {
        Edge edge = new Edge(start, end, weight);
        totalEdges.add(edge);


    }
    /*This method will recursively call itself until an element is referencing itself*/
    public int find(int i) {
        while (parent[i] != i)
            i = parent[i];
        return i;


    }
    /*This function merges 2 subsets i and j into a single set*/
    public void unite(int i, int j) {
        int x;
        int y;
        x = find(i);
        y = find(j);
        parent[x] = y;
    }
    /* This method loops through the vertices assigning itself to the parent array.*/
    public void setParent(int[] parent) {
        for (int i = 0; i < vertex; i++) {
            parent[i] = i;
        }
    }

    /* This method is used to implement kruskal algorithm. I first loop through all n-1 edges. Perform the find function on
    subset x and subset y, and then unionize the two sets.
     */

    public void MST() throws IOException {
        // Create priority queue that will compare the weight of each edge.
        PriorityQueue < Edge > mstQueue = new PriorityQueue < > (totalEdges.size(), Comparator.comparingInt(a -> a.weight));
        //loop through all edges and add them to the mstQueue array list.
        for (int j = 0; j < totalEdges.size(); j++) {
            mstQueue.add(totalEdges.get(j));

        }
        parent = new int[vertex];
        setParent(parent);
        int i = 0;
        while (i < vertex - 1) {
            Edge edge = mstQueue.remove(); //remove edge from queue
            //Ensure that the edge will not create cycle
            int x = find((edge.start));
            int y = find((edge.end));
            // If not then add to our mst array list, and also add the weight of each edge to find total cost.
            if (x != y) {

                weight = weight + edge.weight;
                mst.add(edge);
                unite(x, y); // merge 2 subsets
                i++;

                write.write("Edge:" + i + " source: " + edge.start + " destination: " + edge.end + " cost: " + edge.weight + "\n");

            }
        }
        printMstGraph(mst);

        System.out.println("Total Cost:" + weight);
        write.write("Total Cost:" + weight);
        write.close();
    }

    /* Method to read the 2 text files. I read the cities text file into a 1d array city[]. I read the distances text file
    into a 2d array arr.
     */
    public void readData() {
        String c;
        int i = 0;
        try {

            Scanner scan = new Scanner(new File("Cities.txt"));

            while (scan.hasNextLine()) {
                c = scan.nextLine();


                city[i] = c;

                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;


        try {

            Scanner scan = new Scanner(new File("Distances.txt"));
            arr = new int[city.length][city.length];
            while (scan.hasNextLine()) {


                spl = scan.nextLine().split(",");


                arr[getIndex(spl[1])][getIndex(spl[0])] = Integer.parseInt(spl[2]); // get index of the source and destination.
                addEdge(getIndex(spl[1]), getIndex(spl[0]), Integer.parseInt(spl[2])); // add source, destination, and weight to Edge.


                write = new FileWriter("results.txt");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /* print a 2d array graph*/
    public void printGraph(int arr[][]) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);

                System.out.println();
            }

        }
    }
    /* Print the mst graph*/
    public void printMstGraph(ArrayList < Edge > e) {

        for (int i = 0; i < e.size(); i++) {
            Edge edge = e.get(i);
            System.out.println("Edge:" + i + " source: " + edge.start + " destination: " + edge.end + " cost: " + edge.weight);
        }
    }
    /* This is method returns the index of the city. I pass this method in when my readData method to create my 2d array. */
    int getIndex(String s) {
        for (int i = 0; i < city.length; i++) {

            if (city[i].equals(s)) { //If the index value equals the city name return the index value
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {


        TransportationNet t = new TransportationNet(21);
        try {



            t.readData(); // call readData method
            t.MST(); // Call mst method

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}