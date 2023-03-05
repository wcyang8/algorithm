package baekjoon.wc_bj_16120;

/**
 * 1. P가 들어온다. 
 * 	1.1. Pcnt 2미만, top = A -> Pcnt++
 * 	1.2. Pcnt 2이상, top = A -> stack pop 3개, Pcnt--
 * 2. A가 들어온다.
 * 	2.1. Pcnt 2미만, top = P -> Pcnt 초기화
 * 	2.2. Pcnt 2이상, top = P -> 유지
 * 	2.3. top = A -> NP
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
		for (int i = 0; i < s.length(); i++) {	// 문자 하나씩 스택에 넣기
			if(s.charAt(i) == 'P') {				// 1. 문자 = P일 때
				if(!st.isEmpty() && st.peek() == 'A') {		// top = A면
					if(Pcnt >= 2) {							// 1.1. Pcnt가 2 이상일 때
						for(int k = 0; k < 3; k++) st.pop();	// 스택에서 3개 pop (PPA)
						Pcnt--;									// Pcnt 2 감소 1 증가 = 1감소
					}
				}
				else Pcnt++;								// 1.2. Pcnt가 2 미만이면 Pcnt 1 증가
			}
			if(s.charAt(i) == 'A') {				// 2. 문자 = A일 때
				if(Pcnt < 2) Pcnt = 0;					// 2.1. Pcnt 2미만, top = P -> Pcnt 초기화
				if(!st.isEmpty() && st.peek() == 'A') {	// 2.2. top = A -> A가 연속 2개 -> 무조건 NP
					System.out.println("NP");
					return;
				}
			}
			st.add(s.charAt(i));		// 스택에 추가
		}
		if(st.size() == 1 && st.peek() == 'P') System.out.println("PPAP");		// 다 넣었을 때, P만 남았으면 "PPAP" 문자열
		else System.out.println("NP");											// 그 외엔 "NP" 문자열
		return;
	}

}
