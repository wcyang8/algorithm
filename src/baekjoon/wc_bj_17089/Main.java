package baekjoon.wc_bj_17089;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. N명의 사람
 * 2. 서로 친구인 세 사람을 고른다.
 * 3. 서로를 뺀 친구 수가 가장 많은 세 사람의 친구 수의 최소값을 구해보자.
 * 
 * 풀이
 * 1. a,b,c 싸이클 찾고
 * 2. a,b,c 와 연결된 친구 수 (a,b,c 제외)
 * 
 * @author SSAFY
 *
 */



public class Main {

	static List<Integer>[] adjList;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		
		adjList = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) min = Math.min(min,select(i));		// i번째 사람부터 검색
		
		System.out.println(min == Integer.MAX_VALUE? -1: min);
	}
	private static int select(int first) {
		int min = Integer.MAX_VALUE;
		
		for(int second : adjList[first]) {
			if(visited[second]) continue;
			for(int third : adjList[second]) {
				if(visited[third]) continue;
				if(adjList[third].contains(first)) {
					min = Math.min(min, adjList[first].size() + adjList[second].size() + adjList[third].size() - 6);
				}
			}
		}
		
		return min;
	}

}
