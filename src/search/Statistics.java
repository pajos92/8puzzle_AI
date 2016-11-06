package search;

/**
 * Class Statistics to keep track of every search data
 *
 * @author Panos Mats
 */
public class Statistics {

    private int totalNodesCreated;
    private boolean solved;

    public Statistics() {
        this.totalNodesCreated = 0;
        this.solved = false;
    }

    public Statistics(int totalNodesCreated, boolean solved) {
        this.totalNodesCreated = totalNodesCreated;
        this.solved = solved;
    }

    public int getTotalNodesCreated() {
        return totalNodesCreated;
    }

    public void setTotalNodesCreated(int totalNodesCreated) {
        this.totalNodesCreated = totalNodesCreated;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

}
