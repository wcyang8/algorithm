package baekjoon.wc_bj_18222;

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