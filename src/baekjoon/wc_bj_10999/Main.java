package baekjoon.wc_bj_10999;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
			update(tree, i, i, A[i]);
//			System.out.println(Arrays.toString(tree));
		}
//		System.out.println(Arrays.toString(tree));

		for (int r = 1; r <= M + K; r++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) { // a = 1이면 update
				long d = Long.parseLong(st.nextToken());
				update(tree, b, c, d);
//				System.out.println(Arrays.toString(tree));
			} else if (a == 2) { // a = 2이면 구간합 출력
				sb.append(sum1toI(tree, c) - sum1toI(tree, b - 1)).append("\n");
			}
		}
		System.out.print(sb);
	}

	static void update(long[] tree, int i, int j, long diff) {
//		System.out.println(i+" "+j +" "+diff);
		for (int k = i; k <= j; k++) {
			tree[k] += diff * Math.min((k & -k), k - i + 1); // 차이를 더한다.
		}
		int next = (int)(Math.log(j) / Math.log(2)) + 1;
//		System.out.println(next);
		while((1<<next) <= tree.length-1) {
			tree[1<<next] += diff * (j-i+1);
			++next;
		}
	}

	static long sum1toI(long[] tree, int i) {
		long sum = 0;

		while (i > 0) {
			sum += tree[i];
			i -= (i & -i);
		}
		return sum;
	}

}
