package swea.wc_swea_5607;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static long p = 1_234_567_891;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			long[] fact = new long[N + 1];

			fact[0] = 1;
			for (int i = 1; i <= N; i++) {
				fact[i] = (fact[i - 1] * i) % p;
			}
			long res = ((fact[N] * pow(fact[K], p - 2)) % p) * pow(fact[N - K], p - 2) % p;
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}

	static long pow(long x, long time) {
		if (time == 0)
			return 1;
		if (time == 1)
			return x;

		long half = time / 2;
		long odd = time % 2;
		long down = pow(x, half);

		return ((down * down) % p) * pow(x, odd) % p;
	}

}
