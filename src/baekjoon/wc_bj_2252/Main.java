package baekjoon.wc_bj_2252;
/**
 * 요약
 * 1. N명의 학생을 키 순서대로 줄 세우기 
 * 2. 키를 M번 비교하여, 앞에 서야하는 학생을 나타낸다.
 * 
 * 풀이 
 * 1. 위상정렬을 사용하여 푼다.
 * 
 * 풀기 전 고려사항 
 * 1. 값이 전부 int범위 이내인가? O 
 * 2. 입력이 연결그래프만 주어지나? X 
 * 3. 위상정렬 조건 - 비순환 유향그래프
 * 	3.1. 비순환 그래프만 주어지나? X
 * 
 * 풀고 난 후
 * 1. List index로 for문 돌릴 때, 현재 index remove하면 뒤의 index가 전부 줄어드므로 조심.
 * @author SSAFY
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] adjList = new ArrayList[N+1];		// 인접리스트
		int[] degree = new int[N+1];		// 진입 차수
		
		for (int v = 1; v <= N; v++) {					// 인접리스트 초기화 
			adjList[v] = new ArrayList<Integer>();
		}
		
		for(int e = 0; e < M; e++) {					// 인접리스트 입력
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			degree[to]++;
		}

		
		Queue<Integer> q = new ArrayDeque<Integer>();	// 진입 차수가 0인 정점을 전부 q에 넣어준다
		for(int v = 1; v <= N; v++) {
			if(degree[v] == 0) {
				q.add(v);
			}
		}
		// BFS 활용 위상정렬
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(' ');
			for(int i = 0; i < adjList[cur].size(); i++) {
				int next = adjList[cur].get(i);
				if(--degree[next] == 0) q.add(next);	// 뺐을 때
				adjList[cur].remove(i);
				i--;
			}
		}
		boolean flag = true;
		for(int v = 1; v <= N; v++) {
			if(degree[v] > 0) {
				flag = false;
				break;
			}
		}
		System.out.println(flag?sb:"");
	}
}
