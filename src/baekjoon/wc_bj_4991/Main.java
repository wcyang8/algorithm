package baekjoon.wc_bj_4991;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] room;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int w, h;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            room = new char[w][h];

            Set<Integer> dirty = new HashSet<>();
            int si = 0;
            int sj = 0;

            for(int i = 0; i < w; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < h; j++){
                    room[i][j] = st.nextToken().charAt(0);
                    if(room[i][j] == '*') dirty.add(i * h + j);
                    if(room[i][j] == 'o'){
                        si = i;
                        sj = j;
                    }
                }
            }

            System.out.println(bfs(dirty, si, sj));
        }
    }

    private static boolean bfs(Set<Integer> dirty, int i, int j) {
        int res = 0;
        boolean[][] visit = new boolean[w][h];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i,j,0});
        visit[i][j] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int[] d : dir){

            }
        }
    }
}
