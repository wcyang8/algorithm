package swea.wc_swea_5604;

/**
 * 풀이
 * 1. 1부터 N까지의 모든 페이지 수 합 = 
 * 
 * 
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] num = new int[10];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < 10; i++) num[i] = num[i-1]+i;
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			long A = Integer.parseInt(st.nextToken());
			long B = Integer.parseInt(st.nextToken());
			
			System.out.println(ssum(B));
			System.out.println(ssum(A));
			
			sb.append("#").append(tc).append(" ").append(ssum(B)-ssum(A)).append("\n");
		}
		System.out.println(sb);
	}
	private static long ssum(long X) {
		long res = 0;
		long n = 1;
		while(X/n >= 1) {
			long curS = X / n;
			long curR = X % (n/10);
			long curRR = curS - (X/(n*10))*10;
			res += curS * num[9] * (n/10) + curRR*curR + (curRR>0?(num[(int)curRR-1]*(n/10)):0);
			n *= 10;
		}
		return res;
	}
}
