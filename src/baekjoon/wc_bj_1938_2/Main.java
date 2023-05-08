package baekjoon.wc_bj_1938_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int n, bx, by, bRow = 0;
    static int ex, ey, eRow = 0;
    static char[][] map;
    static int[][][] ansMap;
    static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 왼쪽 대각선부터 시계방향
    static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 기둥 옮기기
        // 지형 : 0은 빈칸 / 1은 기둥
        // U 위로, D 아래, L 왼, R 오른, T 중심으로 90도 회전
        // -> 움직일 위치에 다른 기둥(1) 이 없어야 함
        n = Integer.parseInt(br.readLine());

        int cntB = 0, cntE = 0;
        map = new char[n][n];
        ansMap = new int[n][n][2]; // 가로 0, 세로 1

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++)
                    ansMap[i][j][k] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B') {
                    if (cntB == 1) {
                        bx = i; // 중심점 찾기
                        by = j;
                    }

                    cntB++;
                } else if (map[i][j] == 'E') {
                    if (cntE == 1) {
                        ex = i; // 중심점 찾기
                        ey = j;
                    }

                    cntE++;
                }
            }
        }

        if (map[bx - 1][by] == 'B') // 가로 세로 체크
            bRow = 1;
        if (map[ex - 1][ey] == 'E')
            eRow = 1;

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ansMap[i][j][0] + " ");
            }
            System.out.println();
        }

        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ansMap[i][j][1] + " ");
            }
            System.out.println();
        }

        int ans = 0;
        if (ansMap[ex][ey][eRow] == -1)
            ans = 0;
        else
            ans = ansMap[ex][ey][eRow];
        sb.append(ans);
        System.out.println(sb);
    }

    // 중심점 기준으로 BFS -> 가로 세로일 때 체크
    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { bx, by, bRow });
        ansMap[bx][by][bRow] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 8; i += 2) {
                boolean check = false;
                for (int j = 0; j < 3; j++) {
                    int nx = cur[0] + dx[(i + j) % 8];
                    int ny = cur[1] + dy[(i + j) % 8];

                    // 범위 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        check = true;
                        break;
                    }

                    // 기둥 있으면 불가능
                    if (map[nx][ny] == '1') {
                        check = true;
                        break;
                    }
                }

                if (!check) {
                    if (ansMap[cur[0] + dx[i + 1]][cur[1] + dy[i + 1]][cur[2]] != -1)
                        continue;

                    queue.add(new int[] { cur[0] + dx[i + 1], cur[1] + dy[i + 1], cur[2] });
                    ansMap[cur[0] + dx[i + 1]][cur[1] + dy[i + 1]][cur[2]] = ansMap[cur[0]][cur[1]][cur[2]] + 1;
                }
            }

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    break;

                // 기둥 있으면 불가능
                if (map[nx][ny] == '1')
                    break;

                cur[2] = (cur[2] + 1) % 2;

                if (ansMap[cur[0]][cur[1]][cur[2]] != -1)
                    continue;

                queue.add(new int[] { cur[0], cur[1], cur[2] });
                ansMap[cur[0]][cur[1]][cur[2]] = ansMap[cur[0]][cur[1]][(cur[2] + 1) % 2] + 1;
            }
        }
    }
}