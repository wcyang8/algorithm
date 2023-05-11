package baekjoon.wc_bj_14003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		dq.addLast(A[1]);
		dp[1] = A[1];
		int len = 1;
		for(int i = 2; i <= N; i++) {
			if(A[i] > dp[len]){
				dq.add(A[i]);
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
				dq.pollLast();
				dq.addLast(A[i]);
			}
			dp[left] = A[i];
		}
		
//		System.out.println(Arrays.toString(dp));
		
		System.out.println(len);
		
		while(!dq.isEmpty()) {
			System.out.print(dq.pollFirst()+" ");
		}
		System.out.println();
	}

}
