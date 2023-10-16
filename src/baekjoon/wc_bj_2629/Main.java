package baekjoon.wc_bj_2629;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 양팔 저울과 몇개의 추가 주어진다.
 * 2. 주어진 구슬의 무게를 확인할 수 있는지를 결정한다.
 * 
 * 풀이
 * 1. 추의 무게로 dp
 * 2. (탐색한 추 번호, 무게) = 가능 여부
 * 3. (탐색한 추 번호, 무게) = (탐색한 추 번호 - 1, 무게) or (탐색한 추 - 1, 무게 - 추무게)
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] weight = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = 0;
		for(int i = 0; i < N; i++){
			weight[i] = Integer.parseInt(st.nextToken());
			S += weight[i];
		}

		boolean[][] dp = new boolean[N][S+1];

		dp[0][0] = true;
		dp[0][weight[0]] = true;
		for(int i = 1; i < N; i++){
			for(int j = 0; j <= S; j++){
				dp[i][j] = dp[i-1][Math.abs(j - weight[i])] || dp[i-1][j];
				if(j+weight[i] <= S) dp[i][j] = dp[i][j] || dp[i-1][j+weight[i]];
			}
		}

		int M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++){
			boolean check = false;
			int k = Integer.parseInt(st.nextToken());
			if(k <= S) {
				for (int j = 0; j < N; j++) {
					if (dp[j][k]) {
						check = true;
						break;
					}
				}
			}
			sb.append(check?'Y':'N').append(' ');
		}
		System.out.println(sb);
	}
}
