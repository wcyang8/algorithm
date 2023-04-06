package swea.wc_swea_8458;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			long[] dist = new long[N];

			long max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				dist[i] = Math.abs(x) + Math.abs(y);
				max = Math.max(dist[i], max);
			}

//			System.out.println(Arrays.toString(dist));
			boolean ok = true;
			boolean odd = (dist[0] % 2 == 1);
			for (int i = 1; i < N; i++) {
				if ((odd && dist[i] % 2 == 0) || (!odd && dist[i] % 2 == 1)) {
					ok = false;
					break;
				}
			}
			if (!ok) {
				sb.append("#").append(tc).append(" ").append(-1).append("\n");
				continue;
			}

//			long left = 0;
//			long right = 80000;
////			System.out.println(right);
//			while (left < right) {
////				System.out.println(left+" "+right);
//				long mid = (left + right) / 2;
//				long val = (mid * mid + mid) / 2;
////				System.out.println(val);
//				if (val < max) {
//					left = mid + 1;
//				} else {
//					right = mid;
//				}
//			}
//			System.out.println(mid);

			long left = (long)Math.ceil((Math.sqrt(8*max + 1)-1) / 2);
			
			long val = (left * left + left) / 2;

			if ((val - max) % 2 == 1) {
				++left;
				if (left % 2 == 0)
					++left;
			}
			sb.append("#").append(tc).append(" ").append(left).append("\n");
		}
		System.out.println(sb);
	}
}
