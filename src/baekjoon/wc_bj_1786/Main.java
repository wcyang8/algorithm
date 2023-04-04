package baekjoon.wc_bj_1786;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 요약
 * 1. KMP 연습문제
 * 
 * 풀이
 * 1. KMP를 사용해 풀자.
 * 2. 공백도 하나의 문자로 취급한다.
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String T = br.readLine();
		String P = br.readLine();
		
		int len = P.length();
		
		int[] pi = new int[len];
		List<Integer> list = new ArrayList<>();
		
		pi[0] = 0;
		int j = 0;
		for(int i = 1; i < len; i++) {
//			System.out.println(i + " : " + j);
			if(P.charAt(i) == P.charAt(j)) {
				++j;
				pi[i] = j;
			}else {
				if(j != 0) {
					--i;
					j = pi[j-1];
				}
			}
		}
//		for(int i : pi) System.out.print(i+ " ");
//		System.out.println();
		
		j = 0;
		for(int i = 0; i < T.length(); i++) {
//			System.out.println(i + " : " + j);
			if(T.charAt(i) != P.charAt(j)) {
				if(j != 0) {
					--i;
					j = pi[j-1];
				}
			}else {
				++j;
			}
			if(j == len) {
				list.add(i-len+2);
				j = pi[j-1];
			}
		}
		
		sb.append(list.size()).append('\n');
		for(int i: list) sb.append(i).append(' ');
		System.out.println(sb);
	}

}
