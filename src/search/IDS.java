/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import puzzle8.Board;
import puzzle8.Node;
import puzzle8.Target;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Panos Mats
 */
public class IDS {

    /**
     * List containing all visited states
     */
    private final ArrayList<Node> visitedStates = new ArrayList<>();

    /**
     * Stack used to fill with ids nodes
     */
    private final Stack<Node> stack = new Stack<>();

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
     * Constructor for Iterative Deepening Search Creates new root node with new
     * random board and adds it to ids stack
     */
    public IDS() {
        //Sample sample = new Sample();
        root = new Node(new Board());
        target = new Target();
    }

    /**
     * Implements iterative deepening search for a IDS Object
     *
     * @param stats statistics object to fill with useful date while running
     * @return statistics object with collected data
     */
    public Statistics search(Statistics stats) {

        System.out.println("============================================");
        System.out.println("Random root node created: " + root.getBoard().toString2());
        System.out.println("============================================");
        System.out.println(root.getBoard().toString());
        System.out.println("IDS is searching..");

        int maxDepth = 100;
        for (int depth = 0; depth < maxDepth; depth++) {

            getStack().add(root);
            getVisitedStates().clear();
            /* Run as long as there are nodes in stack */
            while (!getStack().isEmpty()) {

                Node nextNode = getStack().pop();
                stats.setTotalNodesCreated(stats.getTotalNodesCreated() + 1);

                // just a heartbeat every 25k nodes 
                if (stats.getTotalNodesCreated() % 25000 == 0) {
                    System.out.println("..." + stats.getTotalNodesCreated() + " nodes checked!");
                }

                /* If node is NOT already visited expand child nodes and check for target */
                if (!isVisitedBefore(nextNode)) {
                    /* if you don't exceed current searching depth */
                    if (nextNode.getDepth() <= depth) {

                        /* Expand the child nodes and add them the to the stack */
                        ArrayList<Node> childNodes = nextNode.expandChildNodes();
                        for (Node childNode : childNodes) {
                            getStack().push(childNode);
                        }
                    }
                    /* Check current node for target */
                    if (nextNode.getBoard().isTarget(target)) {
                        System.out.println("IDS found solution!");
                        stats.setSolved(true);

                        /* Print the solution path */
                        Node nodeTree = nextNode;
                        System.out.println("Moves to solution:");
                        System.out.println(nodeTree.getBoard().toString());

                        /* run as long as there is a parent node (until root node)  */
                        while (nodeTree.getParentNode() != null) {
                            System.out.println(nodeTree.getParentNode().getBoard().toString());
                            nodeTree = nodeTree.getParentNode();
                        }
                        System.out.println("Total path of " + nextNode.getDepth() + " moves(depth).");
                        System.out.println("IDS explored " + stats.getTotalNodesCreated() + " nodes this time!");
                        break;
                    }
                    /* Add this node to visited nodes to avoid re-checking */
                    getVisitedStates().add(nextNode);
                }
                if (stats.isSolved()) {
                    break;
                }

            }
            if (stats.isSolved()) {
                break;
            }
            //System.out.println("Raising depth to " + (depth + 1) + ".");
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
     * @return the queue used for ids
     */
    public Stack<Node> getStack() {
        return stack;
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
