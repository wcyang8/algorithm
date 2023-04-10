package baekjoon.wc_bj_17136;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int min = 25;
	static int[][] paper = new int[10][10];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] r = new int[5];
		stick(0, r);
	}
	private static void stick(int pos, int[] r) {
		if(pos == 100) {
			int sum = 0;
			for(int i : r) sum += i;
			min = Math.min(min, sum);
			return;
		}
		int i = pos / 10;
		int j = pos % 10;
		
		if(paper[i][j] == 0) {
			int[] nr = r.clone();
			stick(pos+1, nr);
			return;
		}
		
		if(paper[i][j] == 1) {
			for(int k = 0; k < 5; k++) {
				for(int l = 0; l < k;)
			}
		}
	}
}
