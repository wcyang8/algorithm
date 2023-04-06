package baekjoon.wc_bj_11401_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 이항계수 문제
 * 2. N C K 를 풀어보자.
 * 3. 
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] fact = new long[N+1];
		
		fact[0] = 1;
		for(int i = 1; i <= N; i++) {
			fact[i] = (fact[i-1]*i);
//			System.out.println(fact[i]);
		}
		long res = (fact[N] / fact[K])/ fact[N-K];
		System.out.println(res);
	}

}
