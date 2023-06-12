# Shortest Path Between Cities - Dijkstra's Algorithm

## Introduction
The Shortest Path Between Cities program is an implementation of Dijkstra's algorithm to find the shortest path between two cities in a transportation network. The program uses a simplified version of Ontario's land transportation network and provides functionalities to perform various tasks using graph algorithms.

## Program Overview
The program consists of the following components:

TransportNet Class: This class represents the transportation network graph. It has data attributes to store the graph and methods to perform operations on the graph.
readData Method: This method, defined within the TransportNet class, reads information from CSV text files (cities.txt and distances.txt) and creates the weighted undirected graph using an adjacency matrix.
findShortestPath Method: This method, also defined within the TransportNet class, implements Dijkstra's algorithm to find the shortest path between two given cities in the graph. It returns the shortest path as a sequence of city names and the total cost of the path.
MST Method: This method, within the TransportNet class, uses Kruskal's idea to return a minimum spanning tree of the graph and calculates the total cost of the tree.
Main Function: The main function creates an instance of the TransportNet class, calls the readData method to create the graph, and performs the following tasks:
Calls the findShortestPath method to find the shortest paths between specific cities and writes the results into a text file named results.txt.
Calls the MST method, writes the returned results to results.txt, and uses file IO for output.

## Running the Program
To run the program, follow these steps:

1. Ensure you have Java installed on your system.
2. Download the source code files to your local machine.
3. Open a terminal or command prompt and navigate to the directory containing the program files.
4. Compile the Java files using the following command:
javac TransportNet.java
5. Run the program using the following command:
java TransportNet
6. The program will execute the tasks described in the main function and generate the results.txt file with the output. 
Note: Make sure the CSV files cities.txt and distances.txt are present in the same directory as the program files.



    <img width="589" alt="Screenshot 2023-06-12 at 9 03 53 AM" src="https://github.com/topmansam/Dijkstras_algorithm/assets/61563625/c9c5752c-af98-49a4-bd82-8d7f356fdeca">





