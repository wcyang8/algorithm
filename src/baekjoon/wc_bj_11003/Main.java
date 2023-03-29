package baekjoon.wc_bj_11003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 *
 */

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<int[]> dq = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int[] cur = {i, Integer.parseInt(st.nextToken())};
			while(!dq.isEmpty() && dq.peekLast()[1] > cur[1]) {
				dq.pollLast();
			}
			dq.addLast(cur);
			while(dq.peekFirst()[0] <= i - L) {
				dq.pollFirst();
			}
			sb.append(dq.peekFirst()[1]).append(" ");
		}
		System.out.println(sb);
	}

}
