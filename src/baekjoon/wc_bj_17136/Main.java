package baekjoon.wc_bj_17136;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int min = 26;
	static int[][] paper = new int[10][10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] r = new int[5];
		stick(0, r);
		System.out.println(min == 26? -1 : min);
	}

	private static void stick(int pos, int[] r) {
		if (pos == 100) { // 맵 끝에 도달하면 백트래킹 끝
			int sum = 0;
			for (int i : r)
				sum += i;
			min = Math.min(min, sum);
			return;
		}
		int i = pos / 10;
		int j = pos % 10;

		if (paper[i][j] != 1) { // paper가 0이면 다음 칸으로
			int[] nr = r.clone();
			stick(pos + 1, nr);
			return;
		}
		// paper 탐색
		int t = 0;
		for(int k = 0; k < 5; k++) {
			boolean check = true;		// k x k 탐색
			for(int l = 0; l <= k; l++) {
				if(i+k >= 10 || i+l >= 10 || j+k >= 10 || j+l >= 10 ||
						paper[i+k][j+l] != 1 || paper[i+l][j+k] != 1) {
					check = false;
					break;
				}
			}
			if(!check) break;
			if(r[k] < 5) {
				for(int l = 0; l <= k; l++) {
					paper[i+k][j+l] = 2;
					paper[i+l][j+k] = 2;
				}
				int[] nr = r.clone();
				++nr[k];
				stick(pos + 1, nr);
				t = k;
			}
		}
		for(int k = 0; k <= t; k++) {
			for(int l = 0; l <= k; l++) {
				paper[i+k][j+l] = 1;
				paper[i+l][j+k] = 1;
			}
		}
	}
}
