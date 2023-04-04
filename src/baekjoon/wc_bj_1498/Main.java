package baekjoon.wc_bj_1498;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String S = br.readLine();

		int len = S.length();

		int[] pi = new int[len];

		pi[0] = 0;
		int j = 0;
		for (int i = 1; i < len; i++) {
			if (S.charAt(i) == S.charAt(j)) {
				j++;
				pi[i] = j;
			} else if (j > 0) {
				j = pi[j - 1];
				i--;
			}
		}
		
		int[] res = new int[len];
		for(int i = 1; i < len; i++) {
			int cnt = 2;
			j = i;
			while(pi[j] > (j+1)/2) {
				if(res[j] != 0) {
					res[i] = res[j] + cnt - 2;
					break;
				}
				j = pi[j-1];
				++cnt;
			}
			if(res[i] != 0) {
				sb.append(i+1).append(" ").append(res[i]).append("\n");
				continue;
			}
			if((j+1)%2 == 0 && pi[j] == (j+1)/2) {
				sb.append(i+1).append(" ").append(cnt).append("\n");
				res[i] = cnt;
			}
		}
		
		
		System.out.println(sb);
	}
	
}
