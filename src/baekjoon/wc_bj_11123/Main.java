package baekjoon.wc_bj_11123;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] visited;
    static String[] map;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int H, W;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = new Integer(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            H = new Integer(st.nextToken());
            W = new Integer(st.nextToken());

            visited = new boolean[H][W];
            map = new String[H];

            for(int i = 0; i < H; i++){
                map[i] = br.readLine();
            }

            int ans = 0;
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(map[i].charAt(j) == '#' && !visited[i][j]){
                        dfs(i,j);
                        ++ans;
                    }
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;

        for(int[] d : dir){
            int ni = i + d[0];
            int nj = j + d[1];
            if(ni >= 0 && ni < H && nj >= 0 && nj < W){
                if(map[ni].charAt(nj) == '#' && !visited[ni][nj]){
                    dfs(ni,nj);
                }
            }
        }
    }


}
