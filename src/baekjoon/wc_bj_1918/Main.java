package baekjoon.wc_bj_1918;

/**
 * +(*)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String S = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if(c >= 'A' && c <= 'Z') {
				sb.append(c);
			}else if(c == '(') {
				stack.add(c);
			}else if(c == ')') {
				while(!stack.isEmpty() && stack.peek() !='(') {
					sb.append(stack.pop());
				}
				if(!stack.isEmpty()) stack.pop();
			}else if(c == '*' || c == '/') {
				while(!stack.isEmpty() && (stack.peek() == '*'||stack.peek() == '/')) {
					sb.append(stack.pop());
				}
				stack.add(c);
			}else if(c == '+' || c == '-') {
				while(!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.add(c);
			}
		}
		while(!stack.isEmpty()) sb.append(stack.pop());
		System.out.println(sb);
	}

}
