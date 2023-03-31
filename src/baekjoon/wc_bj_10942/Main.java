package baekjoon.wc_bj_10942;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 팰린드롬 = 거꾸로 해도 같은 수
 * 2. N개의 자연수 중에 S~E번째 수가 팰린드롬을 이루는지 알아보자.
 * 
 * 값
 * 1. N : 1~2000
 * 2. 수 : 1~100000
 * 3. 질문 개수 M : 1~1000000
 * 
 * 유의
 * 1. 한 질문의 팰린드롬 탐색 = 최대 2000의 시간 복잡도를 가진다.
 * 2. 100만 x 2000 = 2억이므로 dp로 값을 저장하자.
 * 
 * 풀이
 * 1. N x N 배열로 dp 저장.
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] dp;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		dp = new int[N][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken()) - 1;
			int E = Integer.parseInt(st.nextToken()) - 1;
			
			int res = ans(S,E);
			sb.append(res== -1? 0:1).append("\n");
		}
		System.out.print(sb);
	}
	private static int ans(int s, int e) {
		if(s >= e) return 1;
		if(dp[s][e] != 0) return dp[s][e];
		
		if(arr[s] == arr[e] && ans(s+1,e-1) == 1) return dp[s][e] = 1;
		
		return dp[s][e] = -1;
	}

}
