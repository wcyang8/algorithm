package baekjoon.wc_bj_15685;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[101][101];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            int t = 0;
            List<Integer> prev = new ArrayList<>();
            prev.add(d);
            visited[x][y] = true;
            x += dir[d][0];
            y += dir[d][1];
            visited[x][y] = true;
            while(t < g){
                int l = prev.size();
                for(int p = l-1; p >= 0; p--){
                    int k = (prev.get(p) + 1) % 4;
                    x += dir[k][0];
                    y += dir[k][1];

                    visited[x][y] = true;
                    prev.add(k);
                }
                ++t;
            }
        }
        int ans = 0;
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(visited[i][j]){
                    if(visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]){
                        ++ans;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}