package baekjoon.wc_bj_1074;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		int N = Integer.parseInt(sArr[0]);
		int r = Integer.parseInt(sArr[1]);
		int c = Integer.parseInt(sArr[2]);
		
		System.out.println(get(N , r, c));
	}

	static int get(int N, int r, int c) {
		if(N == 0) return 0;
		int half = 1<<(N-1);
		int cnt = 0;
		if(r >= half) {
			r -= half;
			cnt += 2;
		}
		if(c >= half) {
			c -= half;
			cnt += 1;
		}
		return half*half*cnt + get(N-1, r, c);
	}
}
