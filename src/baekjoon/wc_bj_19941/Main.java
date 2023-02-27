package baekjoon.wc_bj_19941;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String Table = br.readLine();
		
		boolean[] visited = new boolean[N];

		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			if(Table.charAt(i) == 'P') {
				for(int j = i - K; j <= i + K; j++) {
					if(j < 0 || j >= N) continue;
					if(!visited[j] && (Table.charAt(j) == 'H')) {
						visited[i] = true;
						visited[j] = true;
						cnt++;
						break;
					}
				}
			}
		}
		System.out.println(cnt);
	}

}
