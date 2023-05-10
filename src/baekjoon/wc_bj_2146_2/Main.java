package baekjoon.wc_bj_2146_2;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 방향 정보
    static int res, ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N]; // 맵 초기화

        // 맵 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 섬마다 번호 매기기 & 섬 주변 추가
        while (true) {
            boolean[][] visited = new boolean[N][N];
            int num = 2;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((map[i][j] == 1 || map[i][j] == num) && !visited[i][j]) {
//                        System.out.println(i + " " + j);
                        BFS(map, visited, i, j, num++);
                    }
                }
            }

            res += 2;

            boolean check = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    if (map[i][j] == 0)
                        check = true;
            }

            if (!check)
                break;
        }

        System.out.println(ans);
    }

    private static void BFS(int[][] map, boolean[][] visited, int i, int j, int k) {
        int N = map.length; // 맵 크기
        Queue<Integer> q = new ArrayDeque<>();
        q.add(i);
        q.add(j);
        map[i][j] = k;
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int ci = q.poll(); // x
            int cj = q.poll(); // y

            if (map[ci][cj] < 0) {
                map[ci][cj] *= -1;
                continue;
            }

            for (int[] d : dir) {
                int ni = ci + d[0];
                int nj = cj + d[1];

                if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                    if (!visited[ni][nj]) {
                        if (map[ni][nj] == 1) { // 1이면 k로 바꾼다
                            map[ni][nj] = k;
                        } else if (map[ni][nj] == 0) { // 0이면 테두리 -> -k
                            map[ni][nj] = -k;
                        }
                        visited[ni][nj] = true;
                        q.add(ni);
                        q.add(nj);
                    }

                    if (Math.abs(map[ni][nj]) > k) {
                        ans = Math.min(ans, res);
//                        System.out.println(res);
//                        System.exit(0);
                    } else if (Math.abs(map[ni][nj]) < k) {
                        ans = Math.min(ans, res + 1);
//                        System.out.println(res + 1);
//                        System.exit(0);
                    }
                }
            }

//            for (int a = 0; a < N; a++) {
//                System.out.println(Arrays.toString(map[a]));
//            }
//            System.out.println();
        }
    }
}