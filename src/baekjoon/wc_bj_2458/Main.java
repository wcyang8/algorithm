package baekjoon.wc_bj_2458;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N][N];

		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], 1000);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;

			dp[from][to] = 1;
		}

		for (int p = 0; p < N; p++) {
			for (int i = 0; i < N; i++) {
				if (p == i)
					continue;
				for (int j = 0; j < N; j++) {
					if (i == j || j == p)
						continue;
					dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p][j]);
				}
			}
		}

		int res = 0;
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				if (dp[i][j] == 1000 && dp[j][i] == 1000) {
					flag = false;
					break;
				}
			}
			if (flag)
				res++;
		}
		System.out.println(res);
	}

}
