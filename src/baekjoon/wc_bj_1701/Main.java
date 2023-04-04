package baekjoon.wc_bj_1701;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		
		int len = S.length();
		
		int[] pi = new int[len];
		
		int max = 0;
		
		pi[0] = 0;
		int j = 0;
		for(int i = 1; i < len; i++) {
			if(S.charAt(i) == S.charAt(j)) {
				++j;
				pi[i] = j;
				max = Math.max(max, pi[i]);
			}else if(j > 0){
				--i;
				j = pi[j-1];
			}
		}
		
		int[] pi2 = new int[len];
		pi2[0] = 0;
		j = 0;
		for(int i = 1; i < len; i++) {
			if(S.charAt(len-1-i) == S.charAt(len-1-j)) {
				++j;
				pi2[i] = j;
				max = Math.max(max, pi2[i]);
			}else if(j > 0){
				--i;
				j = pi2[j-1];
			}
		}
		
//		System.out.println(Arrays.toString(pi));
//		System.out.println(Arrays.toString(pi2));
		System.out.println(max);
	}
}
