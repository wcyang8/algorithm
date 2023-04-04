package baekjoon.wc_bj_4354;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String S = br.readLine();
			if(S.equals("."))break;
			
			int len = S.length();
			
			int[] pi = new int[len];
			
			
			pi[0] = 0;
			int j = 0;
			for(int i = 1; i < len; i++) {
				if(S.charAt(i) == S.charAt(j)) {
					++j;
					pi[i] = j;
				}else if(j > 0) {
					--i;
					j = pi[j-1];
				}
			}
//			System.out.println(Arrays.toString(pi));
			
			int ans = 1;
			j = len - 1;
			while(pi[j] > (j+1)/2) {
				j = pi[j-1];
				++ans;
			}
			if((j+1)%2 == 0 && pi[j] == (j+1)/2) ++ans;
			else ans = 1;
			sb.append(ans).append("\n");
			
		}
		System.out.println(sb);
	}

}
