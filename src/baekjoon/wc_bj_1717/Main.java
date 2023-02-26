package baekjoon.wc_bj_1717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] set = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			set[i] = i;
		}
		
		int cmd,a,b,ca,cb;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ca = a;
			cb = b;
			while(set[ca] != ca) {
				ca = set[ca];
			}
			while(set[cb] != cb) {
				cb = set[cb];
			}
			if(cmd == 1) {
				if(ca == cb) sb.append("YES\n");
				else sb.append("NO\n");
			}
			if(cmd == 0) {
				set[cb] = ca;
			}
		}
		System.out.println(sb);
	}

}
