package baekjoon.wc_bj_19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
//    static int[][][] state;
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

        // 상어 위치로 simul
        max = simul(state,0 ,0);

//        testPrint(state);
        // 정답 출력
        System.out.println(max);
    }

    private static int simul(int[][][] state, int si, int sj) {
        int[][][] curState = new int[4][4][2];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 2; k++){
                    curState[i][j][k] = state[i][j][k];
                }
            }
        }

        // 상어가 그 칸의 물고기를 먹는다.
        int eat = curState[si][sj][0];
        int sd = curState[si][sj][1];
        curState[si][sj][0] = 0;
        curState[si][sj][1] = -1;
//        testPrint(curState, si, sj, sd);
        // 물고기 1번부터 이동
        for(int k = 1; k <= 16; k++){
            int ci = -1;
            int cj = -1;
            // 현재 물고기의 위치를 찾음
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(curState[i][j][0] == k){
                        ci = i;
                        cj = j;
                        break;
                    }
                }
            }
            // 상어가 먹은 경우
            if(ci == -1 && cj == -1) continue;

            // 현재 물고기의 방향
            int cd = curState[ci][cj][1];

            // 45도 회전
            for(int n = 0; n < 8; n++){
                int ni = ci + dir[cd][0];
                int nj = cj + dir[cd][1];
                // 벽이 아니고
                if(ni >= 0 && ni < 4 && nj >= 0 && nj < 4){
                    // 상어가 아니면
                    if(ni != si || nj != sj){
                        break;
                    }
                }
                // 그 외엔 방향 재설정
                cd = (cd + 1) % 8;
                if(n == 7) cd = -1;
            }
            // 갈 수 있는 곳이 없으면 그대로
            if(cd != -1){
                // 있으면
                // 현재 위치의 방향을 cd로 바꾼다.
                curState[ci][cj][1] = cd;

                // move & swap
                int[] temp = new int[2];

                int ni = ci + dir[cd][0];
                int nj = cj + dir[cd][1];

                temp[0] = curState[ni][nj][0];
                temp[1] = curState[ni][nj][1];

                curState[ni][nj][0] = curState[ci][cj][0];
                curState[ni][nj][1] = curState[ci][cj][1];

                curState[ci][cj][0] = temp[0];
                curState[ci][cj][1] = temp[1];
            }
        }
//        testPrint(curState, si, sj, sd);
        int max = 0;
        int nsi = si + dir[sd][0];
        int nsj = sj + dir[sd][1];
        // 상어가 움직일 수 있나?
        while(nsi >= 0 && nsi < 4 && nsj >= 0 && nsj < 4){
            // 움직일 수 있으면
//            int t1 = curState[nsi][nsj][0];
//            int t2 = curState[nsi][nsj][1];
            // 다음 자리의 물고기를 먹으러 간다.
            if(curState[nsi][nsj][0] != 0) max = Math.max(max, simul(curState, nsi, nsj));
//            curState[nsi][nsj][0] = t1;
//            curState[nsi][nsj][1] = t2;
            nsi += dir[sd][0];
            nsj += dir[sd][1];
        }
        return eat + max;
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
    private static void testPrint(int[][][] state, int si, int sj, int sd){
        System.out.println("====테스트 시작====");
        System.out.println("dir : "+ sd);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(i == si && j == sj){
                    System.out.print("S ");
                    continue;
                }
                System.out.print(state[i][j][0] + " ");
            }
            System.out.println();
        }
        System.out.println("====테스트 끝====");
    }

}
