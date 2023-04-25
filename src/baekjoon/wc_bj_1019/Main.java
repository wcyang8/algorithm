package baekjoon.wc_bj_1019;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 요약
 * 1. 전체 페이지 수가 N인 책이 있다.
 * 2. 1~N까지 각 숫자가 전체 페이지 번호에서 모두 몇 번 나오는지 구해보자.
 * 
 * 풀이
 * 1. 149235의 개수를 구해보자
 * 2. N = 9일때
 * 3. i번째 자리에서의 숫자 j의 개수
 * 4. 전부 1 (마지막에 0만 1개 빼주자)
 * 5. N = 99일 때
 * 6. 00 ~ 99
 * 7. [1][1~9] += 10, [0][1~9] += 10
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = N;
		
		int[] cnt = new int[10];
		int t = 1;
		while(N > 0) {
			int[] x = new int[10];
			int A = N / 10;
			int B = N % 10;
			
			for(int i = 0; i < 10; i++) x[i] += A;
			for(int i = 0; i <= B; i++) x[i] += 1;
			
			for(int i = 0; i < 10; i++) x[i] *= t;
			x[0] -= t;
			x[B] -= t - 1 - M % t;
			for(int i = 0; i < 10; i++) cnt[i] += x[i];
			t *= 10;
			N = A;
		}
		for(int i : cnt) {
			System.out.print(i+" ");
		}
	}

}
