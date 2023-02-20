package baekjoon.wc_bj_18222;

/**
 * k보다 큰 2^n를 잡아서
 * k = 2^(n-1) + h 라고 하면
 * whatChar(k) = whatChar(2^(n-1)+h) = 1 - whatChar(h) 가 된다.
 * 재귀를 통해 whatChar(1) 까지 도달하면 0을 리턴한다.
 * 시간복잡도는 O(logN)
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long k = sc.nextLong();

		System.out.println(whatChar(k));
	}

	public static long whatChar(long k) {
		int cnt = 0;
		while ((k >> cnt) > 1) {
			cnt++;
		}
		if (cnt == 0)
			return 0;
		if (k - ((long)1 << cnt) == 0)
			return (cnt % 2 == 0) ? 0 : 1;
		return 1 - whatChar(k - ((long)1 << cnt));
	}
}