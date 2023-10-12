package baekjoon.wc_bj_17825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 칸에 말 4개
 * 말은 겹칠 수 없다.
 * 말은 이동 시 마다 칸의 점수가 추가된다.
 */

public class Main {
    static int[] dice = new int[10];
    static int[] horse = new int[4];
    static List<Integer>[] adjList = new ArrayList[33];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 10; i++) dice[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 33; i++) adjList[i] = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            adjList[i].add(2*i);
            adjList[i].add(i + 1);
        }
        adjList[5].add(21);
        adjList[10].add(24);
        adjList[15].add(26);

        adjList[21].add(13);
        adjList[21].add(22);

        adjList[22].add(16);
        adjList[22].add(23);

        adjList[23].add(19);
        adjList[23].add(29);

        adjList[24].add(22);
        adjList[24].add(25);

        adjList[25].add(24);
        adjList[25].add(29);

        adjList[26].add(28);
        adjList[26].add(27);

        adjList[27].add(27);
        adjList[27].add(28);

        adjList[28].add(26);
        adjList[28].add(29);

        adjList[29].add(25);
        adjList[29].add(30);

        adjList[30].add(30);
        adjList[30].add(31);

        adjList[31].add(35);
        adjList[31].add(20);

        adjList[20].add(40);
        adjList[20].add(32);

        adjList[32].add(0);

        System.out.println(backT(0, 0));
    }

    private static int backT(int cnt, int score) {
//        System.out.println(cnt + " 번 | 점수 : " + score);
        if(cnt == 10) {
            return score;
        }
        int res = score;
        // 말 이동
        for(int i = 0; i < 4; i++){
            if(horse[i] >= 32) continue;
            int next = horse[i];
            for(int move = 0; move < dice[cnt]; move++){
                if(next >= 32) break;
                if(move == 0 && adjList[next].size() == 3){
                    next = adjList[next].get(2);
                    continue;
                }
                next = adjList[next].get(1);
            }
            // 이동 후 위치에 말이 있으면 안됨
            boolean flag = false;
            for(int j = 1; j <= 3; j++){
                if(next != 32 && next == horse[(i+j) % 4]) {
                    flag = true;
                    break;
                }
            }
            if(flag) continue;

            int before = horse[i];
            horse[i] = next;
//            System.out.println("다음칸 : "+next);
//            System.out.println(adjList[next].get(0));
            res = Math.max(res, backT(cnt + 1, score + adjList[next].get(0)));
            horse[i] = before;
        }
        return res;
    }
}
