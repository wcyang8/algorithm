package baekjoon.wc_bj_2457;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이
 * 1. 피는날 순 정렬
 * 2. 지는날 순 정렬
 * 3. 피는날이 3/1 보다 빠른 꽃 중에 피는 길이가 가장 긴 꽃
 * 4. 지는 날이 11/30 보다 뒤인 꽃 중에 피는 길이가 가장 긴 꽃
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int START = 3 * 32 + 1;
		final int END = 11 * 32 + 30;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] flowers = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			flowers[i][0] = sm * 32 + sd;
			flowers[i][1] = em * 32 + ed;
		}
		
		// 피는 날 순 정렬
		Arrays.sort(flowers, (o1, o2)-> (o1[0] - o2[0] != 0)?o1[0] - o2[0]:o1[1] - o2[1]);
		
		int peek = START;
		
		int res = 0;
		int last = 0;
		// 지는 날이 11/30보다 높으면 끝
		while(peek <= END) {
			// pq의 peek보다 피는 날이 먼저인 꽃 중에 지는 날이 가장 나중인 꽃
			int max = 0;
			int index = 0;
			for(int i = last; i < N && flowers[i][0] <= peek; i++) {
				if(flowers[i][1] > max) {
					max = flowers[i][1];
					index = i;
				}
			}
			if(max == 0) break;
			last = index + 1;
			peek = flowers[index][1];
			++res;
		}
		if(peek <= END) System.out.println(0);
		else System.out.println(res);
	}

}
