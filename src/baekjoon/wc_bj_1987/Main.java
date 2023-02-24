package baekjoon.wc_bj_1987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
        char[][] board = new char[R][];
        
        for(int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        //입력 완료
        Queue<Integer> q = new ArrayDeque<Integer>();
        int usedAlphabet  = (1<<(board[0][0] - 'A'));
        int ci, cj, ni, nj;
        
        q.add(0);    //0,0을 넣어준다.
        q.add(0);
        q.add(usedAlphabet);
        while(!q.isEmpty()) {
            ci = q.poll();
            cj = q.poll();
            usedAlphabet = q.poll();
            
            for(int k = 0; k < 4; k++) {
                ni = ci + d[k][0];
                nj = cj + d[k][1];
                
                if(ni >= 0 && ni < R && nj >= 0 && nj < C) {
                    if((usedAlphabet & (1<<(board[ni][nj] - 'A'))) == 0) {
                        q.add(ni);
                        q.add(nj);
                        q.add(usedAlphabet | 1<<(board[ni][nj] - 'A'));
                    }
                }
            }
        }
        
        int cnt = 0;
        for(int i = 0; i < 26; i++) {
            if((usedAlphabet & (1<<i)) != 0) cnt++;
        }
        
        System.out.println(cnt);
    }

}