package baekjoon.wc_bj_9466;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] student;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            int N = Integer.parseInt(br.readLine());
            student = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) student[i] = Integer.parseInt(st.nextToken());

            boolean[] visit = new boolean[N+1];
            int[] cycle = new int[N + 1];

            for(int i = 1; i <= N; i++){
                // 이미 방문했거나 사이클이면 제외
                if(visit[i]) continue;
                dfs(cycle, visit, i);
            }

            int ans = 0;
            for(int i = 1; i <= N; i++){
//                System.out.print(cycle[i] + " ");
                if(cycle[i] == 1) ans++;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static int dfs(int[] cycle, boolean[] visit, int cur) {
//        System.out.println("dfs : " + cur);
        visit[cur] = true;

        // 다음거가 방문안했으면? -> 넘어가
        // 다음거를 방문했다 -> 싸이클을 방문 했다 -> 1 / 방문 안했다 -> 2
        if(!visit[student[cur]]) {
            int endpoint = dfs(cycle, visit, student[cur]);
            if(endpoint != -1) {
                cycle[cur] = 2;
                if(cur == endpoint) return -1;
                else return endpoint;
            } else {
                cycle[cur] = 1;
                return -1;
            }
        }else if(cycle[student[cur]] == 0){
            cycle[cur] = 2;
            if(cur == student[cur]) return -1;
            return student[cur];
        }else {
            cycle[cur] = 1;
            return -1;
        }
    }
}
