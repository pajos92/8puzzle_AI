package search;

import puzzle8.Board;
import puzzle8.Node;
import puzzle8.Target;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Panos Mats
 */
public final class BFS {

    /**
     * List containing all visited states
     */
    private final ArrayList<Node> visitedStates = new ArrayList<>();
    /**
     * Queue used to fill with bfs nodes
     */
    private final Queue<Node> q = new LinkedList<>();

    /**
     * Unique target for this class to search
     *
     * 1 2 3
     * 8 0 4
     * 7 6 5
     *
     */
    private final Target target;

    /**
     * Unique random root for this class to start search from
     */
    private final Node root;

    /**
     * Constructor for Breadth First Search Creates new root node with new
     * random board Creates a target object to search for
     */
    public BFS() {
        //Sample sample = new Sample();
        root = new Node(new Board());
        target = new Target();
    }

    /**
     * Implements breadth first search for a BFS Object
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
        System.out.println(root.getBoard().toString());
        System.out.println("BFS is searching..");

        /* Run as long as there are nodes in queue */
        while (!getQ().isEmpty()) {

            Node nextNode = getQ().remove();
            stats.setTotalNodesCreated(stats.getTotalNodesCreated() + 1);

            // just a heartbeat every 10k nodes 
            if (stats.getTotalNodesCreated() % 10000 == 0) {
                System.out.println("..." + stats.getTotalNodesCreated() + " nodes checked!");
            }

            /* If node is NOT already visited expand child nodes and check for target */
            if (!isVisitedBefore(nextNode)) {
                /* Expand the child nodes and add them the to the queue */
                ArrayList<Node> childNodes = nextNode.expandChildNodes();
                for (Node childNode : childNodes) {
                    getQ().add(childNode);
                }
                /* Check current node for target */
                if (nextNode.getBoard().isTarget(target)) {
                    System.out.println("BFS found solution!");
                    stats.setSolved(true);

                    /* Print the solution path  */
                    Node nodeTree = nextNode;
                    System.out.println("Moves to solution:");
                    System.out.println(nodeTree.getBoard().toString());

                    /* run as long as there is a parent node (until root node) */
                    while (nodeTree.getParentNode() != null) {
                        System.out.println(nodeTree.getParentNode().getBoard().toString());
                        nodeTree = nodeTree.getParentNode();
                    }
                    System.out.println("Total path of " + nextNode.getDepth() + " moves(depth).");
                    System.out.println("BFS explored " + stats.getTotalNodesCreated() + " nodes this time!");
                    break;
                }
            }
            /* Add this node to visited nodes to avoid re-checking */
            getVisitedStates().add(nextNode);
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
        for (Node nodeVisited : this.getVisitedStates()) {
            if (n.getBoard().equals(nodeVisited.getBoard())) {
                return true;
            }
        }
        return false;
    }
}
