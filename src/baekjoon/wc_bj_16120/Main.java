package baekjoon.wc_bj_16120;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		Stack<Character> st = new Stack<Character>();

		int Pcnt = 0;
		boolean Aflag = false;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'P') Pcnt++;
			if(s.charAt(i) == 'A') {
				if(Pcnt >= 2)
					Aflag = true;
			}
			st.add(s.charAt(i));
		}
	}

}
