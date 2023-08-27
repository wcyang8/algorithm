package baekjoon.wc_bj_18428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] corridor, dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N;
    static boolean flag = false;
    static List<int[]> tList = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        corridor = new int[N][N];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                switch(s.charAt(2*j)){
                    case 'X':
                        corridor[i][j] = 0;
                        break;
                    case 'S':
                        corridor[i][j] = 1;
                        break;
                    case 'T':
                        corridor[i][j] = 2;
                        tList.add(new int[]{i,j});
                        break;
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(corridor[i][j] == 0) backT(i,j,1);
            }
        }
        System.out.println(flag?"YES":"NO");
    }

    private static void backT(int i, int j, int cnt) {
        if(flag) return;
        corridor[i][j] = 3;
        if(cnt == 3){
//            printTest();
            flag = check();
            corridor[i][j] = 0;
            return;
        }
        int pos = i * N + j + 1;
        for(int k = pos; k < N*N; k++){
            int ni = k / N;
            int nj = k % N;

            if(corridor[ni][nj] == 0){
                backT(ni, nj, cnt + 1);
            }
        }
        corridor[i][j] = 0;
    }

    private static boolean check() {
        for(int[] pos: tList){
            for(int[] d: dir){
                int i = pos[0] + d[0];
                int j = pos[1] + d[1];
                while(isOk(i,j)){
                    i += d[0];
                    j += d[1];
                }
                if(i >= 0 && i < N && j >= 0 && j < N && corridor[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isOk(int i, int j) {
        if(i >= 0 && i < N && j >= 0 && j < N){
            if(corridor[i][j] == 0){
                return true;
            }
        }
        return false;
    }

    private static void printTest(){
        System.out.println("===check===");
        for(int x = 0; x < N; x++){
            for(int y = 0; y < N; y++){
                System.out.print(corridor[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("===========");
    }
}
