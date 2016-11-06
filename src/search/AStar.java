package search;

import puzzle8.Board;
import puzzle8.Node;
import puzzle8.Target;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import puzzle8.Sample;

/**
 *
 * @author Panos Mats
 */
public class AStar {

    /**
     * List containing all visited states
     */
    private final ArrayList<Node> visitedStates = new ArrayList<>();

    /**
     * Priority Queue used to fill with a* nodes Every added node is sorted by
     * best cost available so the head of the queue is always the best node
     */
    private final Queue<Node> q = new PriorityQueue<>(350000, idComparator);

    /**
     *
     * Comparator used for the priority queue. Sets the order in which the nodes
     * will be sorted by In this case we want to sort by cost which is
     * determined by the manhattan distance heuristic in class Node.
     *
     */
    public static Comparator<Node> idComparator = new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return (int) (n1.getCost() - n2.getCost());
        }
    };

    /**
     * Unique target for this class to search
     *
     * 1 2 3
     * 8 0 4
     * 7 6 5
     *
     */
    private final Target targetState;

    /**
     * Unique random root for this class to start search from
     */
    private final Node root;

    /**
     * Constructor for Breadth First Search - Creates new root node with new
     * random board - Creates a target object to search for
     */
    public AStar() {
        //Sample s = new Sample();
        root = new Node(new Board());
        targetState = new Target();
    }

    /**
     * Implements A* search for an AStar Object
     *
     * @param stats statistics object to fill with useful data while running
     * @return given statistics object filled with stats
     */
    public Statistics search(Statistics stats) {

        /* Add root node to start creating search tree */
        getQ().add(root);
        System.out.println("============================================");
        System.out.println("Random root node created: " + root.getBoard().toString2());
        System.out.println("============================================");
        System.out.println(this.getQ().peek().getBoard().toString());
        System.out.println("A* is searching..");

        /* Run as long as there are nodes in queue */
        while (!getQ().isEmpty()) {

            Node bestNode = getQ().poll();
            stats.setTotalNodesCreated(stats.getTotalNodesCreated() + 1);

            /* just a heartbeat every 5k nodes 
             if (stats.getTotalNodesCreated() % 5000 == 0) {
             System.out.println("..." + stats.getTotalNodesCreated() + " nodes checked!");
             }
             /*
            
             /* If node is NOT already visited expand child nodes and check for target */
            if (!isVisitedBefore(bestNode)) {
                /* Expand the child nodes and add them the to the queue */
                ArrayList<Node> childNodes = bestNode.expandChildNodes();
                for (Node childNode : childNodes) {
                    getQ().add(childNode);
                }
                /* Check current node for target */
                if (bestNode.getBoard().isTarget(targetState)) {
                    stats.setSolved(true);
                    Node nodeTree = bestNode;
                    System.out.println("A* found solution!");
                    /* Print the solution path, run as long as there is a parent (until root node)  */
                    System.out.println("Moves to solution:");
                    System.out.println(nodeTree.getBoard().toString());
                    while (nodeTree.getParentNode() != null) {
                        System.out.println(nodeTree.getParentNode().getBoard().toString());
                        nodeTree = nodeTree.getParentNode();
                    }
                    System.out.println("Total path of " + bestNode.getDepth() + " moves(depth).");
                    System.out.println("A* explored " + stats.getTotalNodesCreated() + " nodes this time!");

                    break;
                }
            }

            /* Add this node to visited nodes to avoid re-checking */
            getVisitedStates().add(bestNode);
        }

        if (!stats.isSolved()) {
            System.out.println("No solution available for current starting state!");
        }

        return stats;
    }

    /**
     * @return list of visited boards
     */
    public ArrayList<Node> getVisitedStates() {
        return visitedStates;
    }

    /**
     * @return the queue used for bfs
     */
    public Queue<Node> getQ() {
        return q;
    }

    /* 
     Check visited states for repetitiveness 
     */
    private boolean isVisitedBefore(Node n) {
        for (Node nodeVisited : getVisitedStates()) {
            if (n.getBoard().equals(nodeVisited.getBoard()) && (n.getCost() >= nodeVisited.getCost())) {
                return true;
            }
        }
        return false;
    }
}
