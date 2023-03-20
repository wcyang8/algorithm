package baekjoon.wc_bj_2001_2;

/**
 * 1. 갔던 곳을 또 방문할 필요가 있는가?
 * 2. 보석이 있으면 있다.
 * 3. 없으면?
 * 4. 보석을 먹으면서 이동하고, q에는 현재 상태와 현재 v를 저장.
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] jewelIsland = new int[K];
		for(int i = 0; i < K; i++) {
			jewelIsland[i] = Integer.parseInt(br.readLine());
		}
		
		List<int[]>[] adjList = new ArrayList[N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new int[] {to, weight});
			adjList[to].add(new int[] {from, weight});
		}
		
		
	}
}
