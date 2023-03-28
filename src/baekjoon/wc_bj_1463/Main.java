package baekjoon.wc_bj_1463;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 요약
 * 1. 정수 X에 사용할 수 있는 연산
 * 	1.1. 3으로 나누어 떨어지면 3으로 나눔
 * 	1.2. 2로 나누어 떨어지면 2로 나눔
 * 	1.3. 1을 뺌.
 * 2. 세 연산을 최소로 사용해 1을 만들자.
 * 
 * 유의
 * 1. N은 1이상 10^6 이하
 * 
 * 값
 * 1. 전부 뺄 때 연산 횟수가 최대인데, 그럼 최대 10^6이므로 int 사용
 * 
 * 풀이
 * 1. 1부터 시작해서 저장.
 * 2. n -> 연산 후 값 3가지를 전부 비교해서 dp[n]에 최소값 넣어주기.
 * 3. N 도착하면 끝.
 * 
 * 배운 것
 * 1. 점화식을 잘 세우자.
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		
		dp[1] = 0;
		q.add(1);
		
		while(!q.isEmpty()) {
			int n = q.poll();
			
			if(n == N)break;
			
			if(n*3 <= N && dp[n*3] == 0) {
				dp[n*3] = dp[n] + 1;
				q.add(n*3);
			}
			if(n*2 <= N && dp[n*2] == 0) {
				dp[n*2] = dp[n] + 1;
				q.add(n*2);
			}
			if(n+1 <= N && dp[n+1] == 0) {
				dp[n+1] = dp[n] + 1;
				q.add(n+1);
			}
		}
		
		System.out.println(dp[N]);
	}
}
