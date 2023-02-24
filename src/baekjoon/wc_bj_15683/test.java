package baekjoon.wc_bj_15683;

import java.util.Arrays;

public class test {

	static int[][] map;
    static int[][] copyMap;
    public static void main(String[] args) {
        map = new int[10][9];

        copyMap = new int[10][9];

        for(int i = 0; i < 10; i++) {
        	for(int j = 0; j < 9; j++) {
        		map[i][j] = i*10 + j;
        	}
        }
//        for(int i = 0 ; i < 10; i++) {
//            System.arraycopy(map, 0, copyMap, 0, 10);
//        }
        copy();
        System.out.println(Arrays.deepToString(map));
        System.out.println(Arrays.deepToString(copyMap));
    }
    static void copy() {
        for(int i = 0 ; i < 10; i++) {
            System.arraycopy(map, 0, copyMap, 0, 10);
        }
    }

}
