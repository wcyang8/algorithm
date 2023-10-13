package baekjoon.wc_bj_4991;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] room;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int w, h, num;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            room = new char[w][h];

            Map<Integer, Integer> dirty = new HashMap<>();
            int si = 0;
            int sj = 0;
            for(int i = 0; i < w; i++){
                String s = br.readLine();
//                System.out.println(s);
                for(int j = 0; j < h; j++){
                    room[i][j] = s.charAt(j);
                    if(room[i][j] == '*') dirty.put(i * h + j, num++);
                    if(room[i][j] == 'o'){
                        si = i;
                        sj = j;
                    }
                }
            }
            int ret = bfs(dirty, si, sj);
            sb.append(ret).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs(Map<Integer, Integer> dirty, int i, int j) {
        boolean[][][] visit = new boolean[w][h][1024];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i,j,0,0});
        visit[i][j][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
//            System.out.println(cur[0] + " " + cur[1] + " " + cur[2] + " " + cur[3]);

            for(int[] d : dir){
                // 이동
                int ni = cur[0] + d[0];
                int nj = cur[1] + d[1];

                if(check(ni, nj) && room[ni][nj] != 'x' && !visit[ni][nj][cur[2]]){
//                    System.out.println(ni + " " + nj);
                    visit[ni][nj][cur[2]] = true;
                    // 다음 칸이 청소 안한 더러운 칸
                    if(room[ni][nj] == '*' && (cur[2] & (2 << dirty.get(ni * h + nj))) == 0){
//                        System.out.println("====");
//                        System.out.println(next);
//                        System.out.println(next);
//                        System.out.println(tmp + " " + ni + " " + nj);
                        int next = cur[2] + (2 << dirty.get(ni * h + nj));
                        visit[ni][nj][next] = true;
                        if(next == (2 << num) - 1) return cur[3] + 1;
                        q.add(new int[]{ni, nj, next, cur[3] + 1});
                    }else{
                        q.add(new int[]{ni, nj, cur[2], cur[3] + 1});
                    }
                }
            }
        }
        return -1;
    }

    private static boolean check(int i, int j){
        if(i >= 0 && i < w && j >= 0 && j < h) return true;
        else return false;
    }
}
