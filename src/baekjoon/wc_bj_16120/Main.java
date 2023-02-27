package baekjoon.wc_bj_16120;

/**
 * 1. P가 들어온다. 
 * 	1.1. Pcnt 2미만, Aflag true -> Pcnt++, Aflag = false
 * 	1.2. Pcnt 2이상, Aflag true -> stack pop 3개, Pcnt, Aflag 초기화
 * 2. A가 들어온다.
 * 	2.1. Pcnt 2미만, Aflag false -> Pcnt 초기화
 * 	2.2. Pcnt 2이상, Aflag false -> Aflag = true
 * 	2.3. Aflag true -> Pcnt 초기화
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		Stack<Character> st = new Stack<Character>();

		int Pcnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'P') {
				if(!st.isEmpty() && st.peek() == 'A') {
					if(Pcnt >= 2) {
						for(int k = 0; k < 3; k++) st.pop();
						Pcnt--;
					}
				}
				else Pcnt++;
			}
			if(s.charAt(i) == 'A') {
				if(Pcnt < 2) Pcnt = 0;
				if(!st.isEmpty() && st.peek() == 'A') {
					System.out.println("NP");
					return;
				}
			}
			st.add(s.charAt(i));
		}
		if(st.size() == 1 && st.peek() == 'P') System.out.println("PPAP");
		else System.out.println("NP");
		return;
	}

}
