package baekjoon.wc_bj_5430;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			String P = br.readLine();
			
			int N = Integer.parseInt(br.readLine());
			
			Deque<Integer> X = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine(), "[[,]]");
			
			for(int i = 0; i < N; i++) X.add(Integer.parseInt(st.nextToken()));
			
			// 덱 뒤집기
			boolean rev = false;
			
			boolean error = false;
			for(int i = 0; i < P.length(); i++) {
				if(P.charAt(i) == 'R') {
					rev = !rev;
				}else if(P.charAt(i) == 'D') {
					if(X.size() < 1) {
						sb.append("error").append("\n");
						error = true;
						break;
					}
					pop(X, rev);
				}
			}
			if(error) continue;
			
			sb.append("[");
			while(!X.isEmpty()) {
				if(rev)sb.append(X.pollLast()).append(X.isEmpty()?"":",");
				else sb.append(X.pollFirst()).append(X.isEmpty()?"":",");
			}
			sb.append("]\n");
		}
		System.out.println(sb);
	}

	private static void pop(Deque<Integer> x, boolean rev) {
		//rev = true
		if(rev) {
			x.pollLast();
		}else {
			x.pollFirst();
		}
	}

}
