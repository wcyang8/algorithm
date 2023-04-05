package baekjoon.wc_bj_1701;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] S = br.readLine().toCharArray();

		int len = S.length;

		int[] pi = new int[len];

		int max = 0;
		for (int k = 0; k < len - 1; k++) {
			pi[0] = 0;
			int j = 0;
			for (int i = 1; i < len-k; i++) {
				if (S[i+k] == S[j+k]) {
					++j;
					pi[i] = j;
					max = Math.max(max, pi[i]);
				} else if (j > 0) {
					--i;
					j = pi[j - 1];
				}else {
					pi[i] = j;
				}
			}
		}

		System.out.println(max);
	}
}
