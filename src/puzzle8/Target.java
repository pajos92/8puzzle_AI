/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8;

/**
 *
 * @author Panos Mats
 */
public class Target {

    private final int[][] bTarget = new int[3][3];

    public Target() {
        bTarget[0][0] = 1;
        bTarget[0][1] = 2;
        bTarget[0][2] = 3;
        bTarget[1][0] = 8;
        bTarget[1][1] = 0;
        bTarget[1][2] = 4;
        bTarget[2][0] = 7;
        bTarget[2][1] = 6;
        bTarget[2][2] = 5;
    }

    public int[][] getbTarget() {
        return bTarget;
    }

    @Override
    public String toString() {
        return "[" + getbTarget()[0][0] + "][" + getbTarget()[0][1] + "][" + getbTarget()[0][2] + "]\n[" + getbTarget()[1][0] + "][" + getbTarget()[1][1] + "][" + getbTarget()[1][2] + "]\n[" + getbTarget()[2][0] + "][" + getbTarget()[2][1] + "][" + getbTarget()[2][2] + "]\n";
    }

}
