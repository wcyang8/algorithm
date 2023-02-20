package baekjoon.wc_bj_1062;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

		static int N, M, com;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]);
		
		M -= 5;
		
		int alphabet = 0;
		
		if(M < 0) {
			System.out.println(0);
			return;
		}
		
		String antic = "antic";
		int[] word = new int[N];
		for(int i = 0; i < N; i++) {						//N개의 단어 입력
			for(char c : br.readLine().toCharArray()) {			//antic 제거하고 남은 문자들
				if(antic.indexOf(c) != -1) continue;
				alphabet |= 1<<(c-'a');						//antic 제외 문자 중복 없도록 alphabet에 추가
				word[i] |= 1<<(c-'a');						//word 별로 사용하는 문자 비트마스크로 저장
			}
		}
		int cnt = 0;
		for(int i = 0; i < 26; i++) {
			if((alphabet & (1<<i)) != 0) cnt++;
		}
		if(cnt <= M) {
			System.out.println(N);
			return;
		}
		
		int max = comb(word, alphabet, 0, 0, 0);
		System.out.println(max);
	}

	public static int comb(int[] word, int alphabet, int mask, int cnt, int cur) {
		if(cnt == M) {
			
			int wc = 0;
			for(int i = 0; i < N; i++) {
				if((word[i] & ~mask) == 0) wc++;
			}
			return wc;
		}
		int max = 0;
		for(int i = cur; i < 26; i++) {
			if((alphabet & (1<<i)) != 0)
				max = Math.max(max, comb(word, alphabet, mask|(1<<i), cnt+1, i+1));
		}
		return max;
	}
}