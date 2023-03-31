package baekjoon.wc_bj_10942_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 1차원 배열로 해결?
 * 		정답 배열로 저장
 * 		그러나 100만개가 최대이므로 오히려 정답 배열이 더 커진다.
 * 	- 1차원 배열로 줄이려면 dp에서 하나의 값만 구해도 될 때 사용.
 * 
 * 2. 미리 전부 구해놓기
 * 		오른쪽 위부터 내려간다.
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] dp = new boolean[N][N];
		
		for(int i = N-1; i >= 0; i--) {
			for(int j = N-1; j >= 0; j--) {
				if(i >= j) {
					dp[i][j] = true;
					continue;
				}
				if(arr[i] == arr[j] && dp[i+1][j-1]) dp[i][j] = true;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken()) - 1;
			int E = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(dp[S][E]? 1:0).append("\n");
		}
		System.out.print(sb);
	}
}