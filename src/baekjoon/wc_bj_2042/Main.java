package baekjoon.wc_bj_2042;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] tree = new long[N + 1];

		long[] A = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = Long.parseLong(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			update(tree, i, A[i]);
		}
//		System.out.println(Arrays.toString(tree));

		for (int r = 1; r <= M + K; r++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) { // a = 1이면 update
				update(tree, (int) b, c - A[(int) b]);
				A[(int)b] = c;
			} else if (a == 2) { // a = 2이면 구간합 출력
				sb.append(sum1toI(tree, (int) c) - sum1toI(tree, (int) b - 1)).append("\n");
			}
		}
		System.out.print(sb);
	}

	static void update(long[] tree, int i, long diff) {
		while (i < tree.length) {
			tree[i] += diff; // 차이를 더한다.
			i += (i & -i); // L[i] 값 만큼 더해가면서 그 차이를 더해준다.
		}
//		System.out.println(Arrays.toString(tree));
	}

	static long sum1toI(long[] tree, int i) {
		long sum = 0;

		while (i > 0) {
			sum += tree[i];
			i -= i & -i;
		}
		return sum;
	}

}
