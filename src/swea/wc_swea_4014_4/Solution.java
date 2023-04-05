package swea.wc_swea_4014_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. N*N 지형
 * 2. 각 숫자는 지형의 높이
 * 3. 활주로는 지형의 행 열 방향으로 건설
 * 3. 높이가 다르면 활주로가 끊어져서 경사로를 설치해야함.
 * 4. 경사로의 높이는 1, 길이는 X로 고정
 * 5. 활주로를 건설할 수 있는 경우의 수를 계산해보자.
 * 
 * 
 * @author SSAFY
 *
 */

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // testcase

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // N*N
            int X = Integer.parseInt(st.nextToken()); // 가로 길이

            int[][] map = new int[N][N]; // 초기화

            // 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> low = new Stack<>();

            // 가로 탐색
            for (int i = 0; i < N; i++) {
                boolean preDown = false;
                for (int j = 0; j < N; j++) {
                    // System.out.println("가로 " + i + "," + j + " = " + map[i][j]);
                    // System.out.println(stack);
                    // System.out.println(low);
                    if (stack.isEmpty()) {
                        if (low.size() == X) {
                            if (low.peek() - map[i][j] == 1 || low.peek() == map[i][j]) {
                                break;
                            } else {
                                low.clear();
                            }
                        }
                        stack.add(map[i][j]);
                    } else {
                        if (Math.abs(stack.peek() - map[i][j]) > 1) {
                            low.add(-1);
                            break;
                        }

                        if (stack.peek() > map[i][j]) {
                            if (low.isEmpty()) {
                                low.add(map[i][j]);
                            } else {
                                if (low.peek() == map[i][j]) {
                                    low.add(map[i][j]);
                                    if (low.size() == X) {
                                        stack.clear();
                                        stack.add(low.pop());
                                        preDown = true;

                                        low.clear();
                                    }
                                } else {
                                    low.add(-1);
                                    break;
                                }
                            }
                        } else if (stack.peek() < map[i][j]) {
                            if (preDown) {
                                stack.pop();
                                preDown = false;
                            }

                            if (stack.size() < X) {
                                low.add(-1);
                                break;
                            }

                            stack.clear();
                            stack.add(map[i][j]);
                        } else {
                            if (low.isEmpty())
                                stack.add(map[i][j]);
                            else {
                                low.add(-1);
                                break;
                            }
                        }
                    }
                }

                if (low.size() == 0) {
                    cnt++;
                    // System.out.println("ok");
                }

                stack.clear();
                low.clear();

                // System.out.println(i + " " + cnt);
            }

            // 세로 탐색
            for (int j = 0; j < N; j++) {
                boolean preDown = false;
                for (int i = 0; i < N; i++) {
                    // System.out.println("가로 " + i + "," + j + " = " + map[i][j]);
                    // System.out.println(stack);
                    // System.out.println(low);
                    if (stack.isEmpty()) {
                        if (low.size() == X) {
                            if (low.peek() - map[i][j] == 1 || low.peek() == map[i][j]) {
                                break;
                            } else {
                                low.clear();
                            }
                        }
                        stack.add(map[i][j]);
                    } else {
                        if (Math.abs(stack.peek() - map[i][j]) > 1) {
                            low.add(-1);
                            break;
                        }

                        if (stack.peek() > map[i][j]) {
                            if (low.isEmpty()) {
                                low.add(map[i][j]);
                            } else {
                                if (low.peek() == map[i][j]) {
                                    low.add(map[i][j]);
                                    if (low.size() == X) {
                                        stack.clear();
                                        stack.add(low.pop());
                                        preDown = true;
                                        low.clear();
                                    }
                                } else {
                                    low.add(-1);
                                    break;
                                }
                            }
                        } else if (stack.peek() < map[i][j]) {
                            if (preDown) {
                                stack.pop();
                                preDown = false;
                            }

                            if (stack.size() < X) {
                                low.add(-1);
                                break;
                            }

                            stack.clear();
                            stack.add(map[i][j]);
                        } else {
                            if (low.isEmpty())
                                stack.add(map[i][j]);
                            else {
                                low.add(-1);
                                break;
                            }
                        }
                    }
                }

                if (low.size() == 0) {
                    cnt++;
                    // System.out.println("ok");
                }

                stack.clear();
                low.clear();

                // System.out.println(i + " " + cnt);
            }

            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }
        System.out.print(sb);
    }

}