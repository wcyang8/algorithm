package baekjoon.wc_bj_1987_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987_양우철 {

	static char[][] board;
	static int R,C;
	static int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][];
        
        for(int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        
        System.out.println(dfs(0,0,(1<<(board[0][0]-'A'))));
        
    }
    static int dfs(int i, int j, int usedAlphabet) {
    	int result = 0;
    	for(int k = 0; k < 4; k++) {
    		int ni = i + d[k][0];
    		int nj = j + d[k][1];
    		if(ni >= 0 && ni < R && nj >= 0 && nj < C) {
    			if((usedAlphabet & (1<<(board[ni][nj]-'A'))) == 0) {
    				result = Math.max(result, dfs(ni,nj,(usedAlphabet | (1<<(board[ni][nj]-'A')))));
    			}
    		}
    	}
    	if(result == 0) {
    		for(int check = 0; check < 26; check++) {
    			if((usedAlphabet & (1<<check)) != 0) result++;
    		}
    	}
    	return result;
    }
}