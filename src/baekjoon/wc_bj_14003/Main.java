package baekjoon.wc_bj_14003;

/**
 * 새로 들어오는데 len보다 작은 곳에서 갱신
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		
		Deque<Integer> res = new ArrayDeque<Integer>();
		Deque<Integer> temp = new ArrayDeque<Integer>();
		res.addLast(A[1]);
		dp[1] = A[1];
		int len = 1;
		for(int i = 2; i <= N; i++) {
			if(A[i] > dp[len]){
				res.add(A[i]);
				dp[++len] = A[i];
				continue;
			}
			int left = 1;
			int right = len;
			while(left < right) {
				int mid = (left + right)/2;
				if(dp[mid] < A[i]) left = mid + 1;
				else right = mid;
			}
			if(left == len) {
				res.pollLast();
				res.addLast(A[i]);
			}
			dp[left] = A[i];
		}
		
//		System.out.println(Arrays.toString(dp));
		
		System.out.println(len);
		
		while(!res.isEmpty()) {
			System.out.print(res.pollFirst()+" ");
		}
		System.out.println();
	}

}
