package puzzle8;

import java.util.ArrayList;

/**
 *
 * @author Panos Mats
 */
public class Node {

    /**
     * board instance attached to this node object
     */
    private Board board;

    /**
     * parent node of this node instance
     */
    private Node parentNode;

    /**
     * list of all the child nodes of this node instance
     */
    private ArrayList<Node> childNodes = new ArrayList<>();

    /**
     * depth in which this node instance was created
     */
    private int depth;

    /**
     * cost of this node instance to check ( used only by A* )
     */
    private int cost;

    public Node() {
    }

    public Node(Board b) {
        this.board = b;
        this.depth = 0;
        this.cost = 0;
    }

    /**
     *
     * @return the list of all the available children expanded by this node
     * instance
     */
    public ArrayList<Node> expandChildNodes() {

        if (getBoard().checkAvailableUp()) {
            Node n1 = new Node(this.getBoard().moveUp());
            addChild(n1);
        }
        if (getBoard().checkAvailableDown()) {
            Node n2 = new Node(this.getBoard().moveDown());
            addChild(n2);
        }
        if (getBoard().checkAvailableLeft()) {
            Node n3 = new Node(this.getBoard().moveLeft());
            addChild(n3);
        }
        if (getBoard().checkAvailableRight()) {
            Node n4 = new Node(this.getBoard().moveRight());
            addChild(n4);
        }

        return this.getChildNodes();
    }

    /**
     *
     * @param n node instance to add as a child, sets the depth of the child as
     * depth + 1 and adds it in the list of all the child nodes
     */
    public void addChild(Node n) {
        n.setParentNode(this);
        n.setDepth(this.getDepth() + 1);
        this.getChildNodes().add(n);
    }

    /**
     * 
     * @return integer cost of this node instance ( manhattan distance )
     */
    public int getCost() {
        return this.getDepth() + calculateManhattanDistance(this.getBoard());
    }

    /**
     * 
     * @param b board to calculate the manhattan distance of target node
     * @return integer value of manhattan distance
     */
    private int calculateManhattanDistance(Board b) {
        int mdist = 0;
        for (int i = 0; i < b.getMatrix().length; i++) {
            for (int j = 0; j < b.getMatrix()[0].length; j++) {
                switch (b.getMatrix()[i][j]) {
                    case 1:
                        mdist += Math.abs(i - 0) + Math.abs(j - 0);
                        break;
                    case 2:
                        mdist += Math.abs(i - 0) + Math.abs(j - 1);
                        break;
                    case 3:
                        mdist += Math.abs(i - 0) + Math.abs(j - 2);
                        break;
                    case 4:
                        mdist += Math.abs(i - 1) + Math.abs(j - 2);
                        break;
                    case 5:
                        mdist += Math.abs(i - 2) + Math.abs(j - 2);
                        break;
                    case 6:
                        mdist += Math.abs(i - 2) + Math.abs(j - 1);
                        break;
                    case 7:
                        mdist += Math.abs(i - 2) + Math.abs(j - 0);
                        break;
                    case 8:
                        mdist += Math.abs(i - 1) + Math.abs(j - 0);
                        break;
                    default:
                        break;

                }
            }

        }
        return mdist;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(ArrayList<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getChildNodesCount() {
        return childNodes.size();
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

}
