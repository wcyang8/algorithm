package baekjoon.wc_bj_1525;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int[][] puzzle = new int[3][3];

        final int ORIGIN = 87654321;

        int zero = -1;
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                puzzle[i][j] = Integer.parseInt(st.nextToken());
                if(puzzle[i][j] == 0) zero = i * 3 + j;
            }
        }
        int a = puzzleToInt(puzzle);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(a);
        q.add(0);
        q.add(zero);
        Map<Integer, Integer> visit = new HashMap<Integer, Integer>();
        visit.put(a, 0);

        while(!q.isEmpty()){
            int num = q.poll();
            int cnt = q.poll();
            int curZero = q.poll();

            if(num == ORIGIN){
                System.out.println(cnt);
                return;
            }

            int[][] cur = intToPuzzle(num);
            int czi = curZero / 3;
            int czj = curZero % 3;

            for(int[] d : dir){
                int nzi = czi + d[0];
                int nzj = czj + d[1];

                if(nzi >= 0 && nzi < 3 && nzj >= 0 && nzj < 3){
                    int[][] copy = new int[3][3];
                    for(int i = 0; i < 3; i++) System.arraycopy(cur[i], 0, copy[i], 0, 3);

                    int temp = copy[czi][czj];
                    copy[czi][czj] = copy[nzi][nzj];
                    copy[nzi][nzj] = temp;

                    int t = puzzleToInt(copy);
                    if(t == ORIGIN){
                        System.out.println(cnt + 1);
                        return;
                    }

                    Integer v = visit.get(t);
                    if(v == null || cnt + 1 < v){
                        q.add(t);
                        q.add(cnt+1);
                        q.add(nzi * 3 + nzj);
                        visit.put(t, cnt+1);
                    }
                }
            }
        }

        System.out.println(visit.getOrDefault(ORIGIN, -1));
    }

    private static int puzzleToInt(int[][] puzzle){
        int res = 0;
        int cnt = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                res += puzzle[i][j] * Math.pow(10, cnt++);
            }
        }
        return res;
    }

    private static int[][] intToPuzzle(int num){
        int[][] puzzle = new int[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                puzzle[i][j] = num % 10;
                num /= 10;
            }
        }
        return puzzle;
    }
}
