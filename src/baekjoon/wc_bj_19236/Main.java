package baekjoon.wc_bj_19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int[][][] state = new int[4][4][2];

        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                    state[i][j][0] = Integer.parseInt(st.nextToken());
                    state[i][j][1] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        // 최댓값 구하기
        int max = 0;

        // 상어 위치와 방향으로 simul
        max = simul(state,0 ,0);
    }

    private static int simul(int[][][] state, int si, int sj) {
        // 물고기 1번부터 이동
        for(int k = 1; k <= 16; k++){
            int ci = -1;
            int cj = -1;
            // 현재 물고기의 위치를 찾음
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(state[i][j][0] == k){
                        ci = i;
                        cj = j;
                        break;
                    }
                }
            }
            // 45도 회전
            int cd = state[ci][cj][1];

            for(int n = 0; n < 8; n++){
                int ni = ci + dir[cd][0];
                int nj = cj + dir[cd][1];
                // 벽이 아니고
                if(ni >= 0 && ni < 4 && nj >= 0 && nj < 4){
                    // 상어가 아니면
                    if(ni != si && nj != sj){
                        break;
                    }
                }
                // 그 외엔 방향 재설정
                cd = (cd + 1) % 8;
                if(n == 7) cd = -1;
            }
            // 갈 수 있는 곳이 없으면 그대로
            if(cd != -1){
                // 있으면 move
            }
        }
        // 상어가 움직일 수 있나?
        // 없으면 break;
    }

    // 방향을 정해주는 메소드
//    private static int fishDirection(int[][][] state, int ci, int cj) {
//        int cd = state[ci][cj][1];
//        for(int i = 0; i < 8; i++) {
//            // 가는 곳이 벽인가?
//            if(checkWall(ci,cj,cd)) continue;
//            // 가는 곳이 상어인가?
//            if(checkShark())
//        }
//    }

}
