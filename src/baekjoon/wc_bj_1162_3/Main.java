package baekjoon.wc_bj_1162_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 풀이
 * 1. 도로를 i개 지웠을 때 최소 거리 vs 도로를 i+1개 지웠을 때 최소 거리
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dist = new int[N][K]; // 1부터 i까지 최단거리를 저장할 배열 (K개에 따라)

		Arrays.fill(dist, Integer.MAX_VALUE); // int 최대값으로 초기화

		List<int[]>[] adjList = new ArrayList[N]; // 인접 리스트 배열 초기화
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>(); // 인접 리스트 생성
		}

		for (int i = 0; i < M; i++) { // 간선 받기
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { to, cost });
			adjList[to].add(new int[] { from, cost });
		}
		
		
	}

}
