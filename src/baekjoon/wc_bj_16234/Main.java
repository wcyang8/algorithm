package baekjoon.wc_bj_16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    public static void main(String[] args) throws Exception{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int[][] country = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 열린 국경선이 있는지 check
        boolean check = false;
        // 인구이동이 발생한 날짜
        int day = 0;
        do {
            // 국경선 모두 열기
            int num = 1;
            int[][] visited = new int[N][N];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j] > 0) continue;
                    list.add(bfs(country, visited, i, j, num++));
                }
            }
            //System.out.println(list);
            // 인구 이동
            check = false;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(country[i][j] == list.get(visited[i][j] - 1)) continue;
                    //System.out.println(country[i][j] + " " + list.get(visited[i][j] - 1));
                    country[i][j] = list.get(visited[i][j] - 1);
                    check = true;
                }
            }
            if(check)day++;
        }while(check);

        System.out.println(day);
    }

    private static int bfs(int[][] country, int[][] visited, int i, int j, int num) {
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i,j});
        visited[i][j] = num;
        int sum = country[i][j];
        int cnt = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int[] d : dir){
                int ni = cur[0] + d[0];
                int nj = cur[1] + d[1];
                if(ni >= 0 && ni < N && nj >= 0 && nj < N
                        && visited[ni][nj] == 0
                        && Math.abs(country[cur[0]][cur[1]] - country[ni][nj]) >= L
                        && Math.abs(country[cur[0]][cur[1]] - country[ni][nj]) <= R){
                    q.add(new int[]{ni, nj});
                    visited[ni][nj] = num;
                    sum += country[ni][nj];
                    cnt++;
                }
            }
        }
        return sum / cnt;
    }
}
