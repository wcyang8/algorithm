package baekjoon.wc_bj_6591;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			if(N == 0 && K == 0) break;
			
			if(K > N-K) K = N-K;
			long ans = 1;
			for(long i = 1; i <= K; i++) {
				ans *= N - i + 1;
				ans /= i;
			}
			
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
//	static int comb(int N, int K) {
//		if(K > N/2) return comb(N, N-K);
//		if(K == 0) return 1;
//		if(K == 1) return N;
//		
//		if(dp[N][K] != 0) return dp[N][K];
//		
//		return comb(N-1,K) + comb(N-1,K-1);
//	}
}
