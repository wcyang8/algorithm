package baekjoon.wc_bj_1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 요약 1. RGB 거리에는 집이 N개 있다. 2. 1번집 ~ N번집 3. 거리 = 선분 4. 조건 만족 4.1. 1,2번 집은 색이 다름.
 * 4.2. N번 집의 색은 N-1번 집의 색과 다름. 4.3. i번 집의 색은 i-1번, i+1번 집의 색과 다름. 5. 각 집을 각 색으로
 * 칠하는 비용이 존재. 6. 모든 집을 칠하는 비용의 최솟값
 * 
 * 유의 1. N은 2~1000 2. 비용은 0~1000
 * 
 * 값 1. 1000개의 집을 1000으로 칠해도 100만이므로 int 사용
 * 
 * 풀이 1. dp[i] = dp[i-1] * 2 + dp[i-2]
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int[][] colorCost = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				colorCost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] cost = new int[N];
		int[] last = new int[N];

		Arrays.fill(cost, Integer.MAX_VALUE);

		int second1 = 0;
		for (int k = 0; k < 3; k++) {
			// 1번째 집의 3 색 중에 가장 작은거
			if (cost[0] > colorCost[0][k]) {
				second1 = last[0];
				last[0] = k;
				cost[0] = colorCost[0][k];
			}else if(colorCost[0][second1] > colorCost[0][k] && colorCost[0][k] > cost[0]) {
				second1 = k;
			}
		}

		// 2번째 집의 3색 중에 가장 작은거
		int second2 = 0;
		for (int k = 0; k < 3; k++) {
			if (cost[1] > colorCost[1][k] + cost[0]) {
				second2 = last[1];
				last[1] = k;
				cost[1] = colorCost[1][k] + cost[0];
			}else if(colorCost[1][second2] > colorCost[1][k] && colorCost[1][k] > colorCost[1][last[1]]) {
				second2 = k;
			}
		}

		// 만약 작은게 이전 것과 같다면
		if (last[1] == last[0]) {
			// 1번째 집의 2번째 + 2번째 집의 1번째 vs 1번째 집의 1번째 + 2번째 집의 2번째
			if (colorCost[0][second1] + colorCost[1][last[1]] < cost[0] + colorCost[1][second2]) {
				cost[1] = colorCost[0][second1] + colorCost[1][last[1]];
			} else {
				cost[1] = cost[0] + colorCost[1][second2];
				last[1] = second2;
			}
		}

		for (int i = 2; i < N; i++) {
			int second = 2;
			for (int k = 0; k < 3; k++) {
				if (cost[i] > colorCost[i][k] + cost[i - 1]) {
					second = last[i];
					last[i] = k;
					cost[i] = colorCost[i][k] + cost[i - 1];
				}else if(colorCost[i][second] > colorCost[i][k] && colorCost[i][k] > colorCost[i][last[i]]) {
					second = k;
				}
			}
			if (last[i] == last[i - 1]) {
				int already = 0;
				for (int k = 0; k < 3; k++) {
					if (k == last[i] || k == last[i - 2])
						continue;
					already = k;
				}
				// i-1번째 집의 2번째 + i번째 집의 1번째 vs i-1번째 집의 1번째 + i번째 집의 2번째
				if (cost[i - 2] + colorCost[i - 1][already] + colorCost[i][last[i]] < cost[i - 1]
						+ colorCost[i][second]) {
					cost[i] = cost[i - 2] + colorCost[i - 1][already] + colorCost[i][last[i]];
				} else {
					cost[i] = cost[i - 1] + colorCost[i][second];
					last[i] = second;
				}
			}
		}
		System.out.println(Arrays.toString(cost));
		System.out.println(Arrays.toString(last));
		System.out.println(cost[N - 1]);
	}
}
