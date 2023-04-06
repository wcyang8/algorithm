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

	static long p = 1_234_567_891;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] fact = new long[N+1];
		
//		System.out.println(pow(5,3));
		
		fact[0] = 1;
		for(int i = 1; i <= N; i++) {
			fact[i] = (fact[i-1]*i) % p;
		}
		long res = ((fact[N] * pow(fact[K] , p-2)) % p)*pow(fact[N-K],p-2)%p;
		System.out.println(res);
	}
	static long pow(long x, long time) {
		if(time == 0) return 1;
		if(time == 1) return x;
		
		long half = time/2;
		long odd = time%2;
		long down = pow(x, half);
//		System.out.println(x+" "+half+" "+odd+" "+down);
		
		return ((down * down) % p)*pow(x,odd)%p;
	}
}
