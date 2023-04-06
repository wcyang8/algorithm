package baekjoon.wc_bj_17298;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 자신보다 큰 오른쪽 수 중 가장 왼쪽에 있는 수를 전부 구해보자.
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] ans = new int[N];
		
		Stack<int[]> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty() && stack.peek()[1] < cur){
				ans[stack.pop()[0]] = cur;
			}
			stack.add(new int[] {i,cur});
		}
		while(!stack.isEmpty()) {
			ans[stack.peek()[0]] = -1;
			stack.pop();
		}
		for(int i = 0; i < N; i++) sb.append(ans[i]).append(' ');
		System.out.println(sb);
	}

}
