package baekjoon.wc_bj_1305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int L = Integer.parseInt(br.readLine());
		String ad = br.readLine();

		int[] pi = new int[L];

		pi[0] = 0;
		int j = 0;
		for (int i = 1; i < L; i++) {
			if (ad.charAt(i) == ad.charAt(j)) {
				j++;
				pi[i] = j;
			} else {
				if (j > 0) {
					j = pi[j - 1];
					i--;
				}
			}
		}
		System.out.println(L-pi[L-1]);
	}

}
