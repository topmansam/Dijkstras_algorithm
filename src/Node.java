import java.util.Comparator;

class Node implements Comparator < Node > {
    public int node;
    public int cost;
    public Node() {

    }

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int compare(Node nodeA, Node nodeB) {
        if (nodeA.cost < nodeB.cost)
            return -1;
        if (nodeA.cost > nodeB.cost)
            return 1;
        return 1;
    }
}