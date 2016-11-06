package puzzle8;

import search.Statistics;
import java.util.InputMismatchException;
import java.util.Scanner;
import search.AStar;
import search.BFS;
import search.IDS;

/**
 *
 * @author Panos Mats
 */
public class EightPuzzleSolver {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int times = 0;
        int searchAlgorithm = 0;

        /* set how many times to run the 8-puzzle solver  */
        while (times <= 0 || times > 10) {
            System.out.println("Enter how many times you want to solve 8-Puzzle(1-10): ");
            try {
                times = input.nextInt();
            } catch (InputMismatchException e) {
                times = 0;
                String f = input.next();
            }
        }

        /* set the algorithm to solve it with */
        while (searchAlgorithm <= 0 || searchAlgorithm > 3) {
            System.out.println("Select the search algorith you want to solve it with: ");
            System.out.println("1. BFS");
            System.out.println("2. IDS");
            System.out.println("3. A*");
            try {
                searchAlgorithm = input.nextInt();
            } catch (InputMismatchException e) {
                searchAlgorithm = 0;
                String f = input.next();
            }
        }

        int totalNodesCreated = 0;
        int totalSolved = 0;
        
        switch (searchAlgorithm) {
            case 1:     /* Breadth First Search */
                for (int i = 0; i < times; i++) {
                    BFS bfs = new BFS();
                    Statistics feedback = new Statistics();
                    feedback = bfs.search(feedback);
                    totalNodesCreated += feedback.getTotalNodesCreated();
                    if (feedback.isSolved()) {
                        totalSolved++;
                    }
                }
                System.out.println("Average number of nodes created using BFS is : " + totalNodesCreated / times);
                System.out.println("Solvability : " + totalSolved + "/" + times + " puzzles solved.");
                break;
            case 2:     /* Iterative Deepening Search */
                for (int i = 0; i < times; i++) {
                    IDS ids = new IDS();
                    Statistics feedback = new Statistics();
                    feedback = ids.search(feedback);
                    totalNodesCreated += feedback.getTotalNodesCreated();
                    if (feedback.isSolved()) {
                        totalSolved++;
                    }
                }
                System.out.println("Average number of nodes created using IDS is : " + totalNodesCreated / times);
                System.out.println("Solvability : " + totalSolved + "/" + times + " puzzles solved.");
                break;
            case 3:     /* A-Star */
                for (int i = 0; i < times; i++) {
                    AStar astar = new AStar();
                    Statistics feedback = new Statistics();
                    feedback = astar.search(feedback);
                    totalNodesCreated += feedback.getTotalNodesCreated();
                    if (feedback.isSolved()) {
                        totalSolved++;
                    }
                }
                System.out.println("Average number of nodes created using A* is : " + totalNodesCreated / times);
                System.out.println("Solvability : " + totalSolved + "/" + times + " puzzles solved.");
                break;
            default:
                System.out.println("if i am here something went truly wrong :/ ");
                break;
        }

    }

}
