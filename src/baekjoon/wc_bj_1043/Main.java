package baekjoon.wc_bj_1043;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 진실을 아는 사람 앞에서는 거짓말 불가능
 * 2. 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수있는 파티 개수의 최댓값은?
 * 
 * 입력
 * 1. 사람 수 N, 파티 수 M
 * 2. 진실을 아는 사람의 수와 번호가 주어짐
 * 3. 파티마다 오는 사람 수와 번호가 주어짐.
 * 4. N, M은 1~50, 진실을 아는 사람 수 0~50, 각 파티마다 오는 사람 수 1~50
 * 
 * 풀이
 * 1. 진실을 이야기 하면 -> 그 파티의 모든 사람은 진실을 아는 사람이 됨.
 * 2. 진실 아는 사람 전부 visited
 * 3. 파티 첫 줄부터 받으며 같이 있는 사람을 전부 visited
 * 4. 전부 실행한 후 한번 더 탐색하여 전부 진실을 모르는 사람이면 cnt++
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
		
		st = new StringTokenizer(br.readLine());
		
		boolean[] know = new boolean[N+1];					// 진실을 아는 사람
		List<Integer>[] adjList = new ArrayList[N+1];		// 같은 파티에 있는 사람
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
		int[][] party = new int[M+1][];			// 파티에 참가하는 사람
		
		int t = Integer.parseInt(st.nextToken());	// 처음부터 진실을 아는 사람의 수
		
		int[] ak = new int[t];			// 처음부터 진실을 아는 사람의 배열. (BFS 시작 정점)
		
		for(int i = 0; i < t; i++) {
			ak[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= M; i++) {		// 파티 입력
			st = new StringTokenizer(br.readLine());
			
			int pn = Integer.parseInt(st.nextToken());		// 현재 파티에 온 사람 수
			
			party[i] = new int[pn];			// 파티에 사람 수 만큼 배열 할당
			
			for(int j = 0; j < pn; j++) {	// 파티에 온 사람 번호 저장
				party[i][j] = Integer.parseInt(st.nextToken());
			}
			
			if(pn > 1) {		// 한 파티에 온 사람이 2명 이상이면
				int pj = 0;		// 이전 사람
				for(int j = 1; j < pn; j++) {
					adjList[party[i][pj]].add(party[i][j]);			// 이전 사람과 현재 사람 연결
					adjList[party[i][j]].add(party[i][pj]);
				}
			}
		}
		// 입력 완료
		
		// 알고리즘 시작
		// BFS
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int k : ak) {
			q.add(k);			// 처음부터 알고 있는 사람 q에 저장
			know[k] = true;		// know 처리
		}
		while(!q.isEmpty()){
			int cur = q.poll();
			
			for(int i : adjList[cur]) {
				if(!know[i]) {	// 모르면
					know[i] = true;	// know
					q.add(i);	// 다음 정점 방문
				}
			}
		}
		
		int cnt = 0;
		for(int i = 1; i <= M; i++) {		// 파티 탐색
			boolean lie = true;
			for(int j : party[i]) {
				if(know[j]) {				// 하나라도 아는 사람이 있으면
					lie = false;			// 거짓말 불가능
					break;
				}
			}
			if(lie) ++cnt;		// 거짓말 가능하면 cnt++
		}
		System.out.println(cnt);
	}

}
