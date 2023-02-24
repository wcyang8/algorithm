package baekjoon.wc_bj_1759;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static String aeiou = "aeiou";
	static int L, C;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int alphabet = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			char c = st.nextToken().charAt(0);
			alphabet |= (1<<(c - 'a'));						//들어있는 알파벳 전부 넣기
		}
		if(!condition(alphabet,C)) return;					//자음 2개 모음 1개 이하면 불가능이므로 종료
		
		comb(alphabet, 0, 0, 0);
		System.out.println(sb);
	}

	static void comb(int alphabet, int password, int cnt, int cur) {
		if(cnt == L) {
			if(!condition(password,L)) return;				//password가 자음 2개 모음 1개 이하면 불가능
			for(int i = 0; i < 26; i++) {					//답에 password 입력
				if((password & (1<<i)) != 0) sb.append((char)('a'+i));
			}
			sb.append('\n');
		}
		for(int i = cur; i < 26; i++) {						//password 
			if((alphabet & (1<<i)) != 0){					//alphabet에 있는 거 넣어주기
				comb(alphabet, password | (1<<i), cnt+1, i+1);
			}
		}
		
	}
	
	static boolean condition(int alphabet, int len) {
		int mcnt = 0;
		for(int i = 0; i < 26; i++) {
			if((alphabet &(1<<i)) != 0) {		//패스워드에 있는 문자가 
				char c = (char)('a' + i);
				if(aeiou.indexOf(c) != -1) mcnt++;		//모음이면 count
			}
		}
		if(mcnt < 1 || len-mcnt < 2) return false;		//조건에 안맞으면 return
		return true;
	}
}
