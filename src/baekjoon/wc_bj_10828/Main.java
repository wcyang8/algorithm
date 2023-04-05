package baekjoon.wc_bj_10828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			if(cmd.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				
				stack.add(x);
			}else if(cmd.equals("top")) {
				sb.append(stack.isEmpty()?-1:stack.peek()).append('\n');
			}else if(cmd.equals("size")) {
				sb.append(stack.size()).append('\n');
			}else if(cmd.equals("empty")) {
				sb.append(stack.isEmpty()?1:0).append('\n');
			}else if(cmd.equals("pop")) {
				sb.append(stack.isEmpty()?-1:stack.pop()).append('\n');
			}
		}
		System.out.println(sb);
	}

}
