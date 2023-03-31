package swea.wc_swea_3307;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[] A = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int[] C = new int[N + 1];

			int len = 1;
			C[1] = A[0];
			for (int i = 0; i < N; i++) {
				if (A[i] > C[len]) {
					C[++len] = A[i];
					continue;
				}
				int start = 1;
				int end = len;
				while (start < end) {
					int mid = (start + end) / 2;
					if (A[i] > C[mid]) {
						start = mid + 1;
					} else {
						end = mid;
					}
				}
				C[start] = A[i];
			}
			sb.append('#').append(tc).append(' ').append(len).append('\n');
		}
		System.out.println(sb);
	}

}
