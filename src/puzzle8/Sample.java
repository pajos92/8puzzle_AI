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
public class Sample {

    private final int[][] _SAMPLE = new int[3][3];

    public Sample() {
        //0,1,8,5,7,2,4,6,3
        // [2 8 3;1 6 4;7 9 5];
        //3,2,1,5,8,6,7,0,4

        _SAMPLE[0][0] = 3;
        _SAMPLE[0][1] = 2;
        _SAMPLE[0][2] = 1;
        _SAMPLE[1][0] = 5;
        _SAMPLE[1][1] = 8;
        _SAMPLE[1][2] = 6;
        _SAMPLE[2][0] = 7;
        _SAMPLE[2][1] = 0;
        _SAMPLE[2][2] = 4;
    }

    public int[][] getSampleBoard() {
        return _SAMPLE;
    }

}
