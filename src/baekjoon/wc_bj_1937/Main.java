package baekjoon.wc_bj_1937;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[][] forest = new int[N][N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[2] - o2[2]));
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                forest[i][j] = Integer.parseInt(st.nextToken());
                int[] temp = {i,j,forest[i][j]};
                pq.add(temp);
            }
        }

        int[][] check = new int[N][N];     // 위치마다 최대 이동 가능 거리 저장

//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                check[i][j] = 1;
//            }
//        }

        int max = 0;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(check[i][j] > 0) continue;
                max = Math.max(max,dfs(forest, check, i, j));
            }
        }
//        System.out.println(pq.peek()[2]);
//        while(!pq.isEmpty()){
//            int[] temp = pq.poll();
//            int i = temp[0];
//            int j = temp[1];
//            max = Math.max(max,dfs(forest, check, i, j, 1));
//        }
        System.out.println(max);
    }

    private static int dfs(int[][] forest, int[][] check, int x, int y){
        int max = 1;
        for(int[] d : dir){
            int nx = x + d[0];
            int ny = y + d[1];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N){
                if(forest[nx][ny] > forest[x][y]) {
                    if(check[nx][ny] == 0){
                        max = Math.max(max, 1 + dfs(forest, check, nx, ny));
                    }else{
                        max = Math.max(max, 1 + check[nx][ny]);
                    }
                }
            }
        }
        check[x][y] = max;
        return max;
    }
}
