package baekjoon.wc_bj_17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Case implements Comparable<Case>{
	int selected;
	int gap;
	public Case(int selected, int gap) {
		super();
		this.selected = selected;
		this.gap = gap;
	}
	@Override
	public int compareTo(Case o) {
		return Integer.compare(this.gap, o.gap);
	}
}

public class Main {

	static int N, sum;
	static int[] popul;
	static int[][] adjList;
	static List<Case> caseList = new ArrayList<Case>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N : 구역 개수
		N = Integer.parseInt(br.readLine());
		popul = new int[N];
		adjList = new int[N][];
		
		//인구수 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
			sum += popul[i];
		}
		
		//인접리스트 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			adjList[i] = new int[Integer.parseInt(st.nextToken())];
			for(int j = 0; j < adjList[i].length; j++) {
				adjList[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		//경우의 수 전부 caseList에 삽입
		makeCase();
		Collections.sort(caseList);
		
		for(Case c : caseList) {
			if(isAble(c.selected) && isAble((1<<N) - 1 - c.selected) ) {
				System.out.println(c.gap);
				return;
			}
		}
		System.out.println(-1);
		return;
	}
	static void makeCase() {			//각 경우의 수를 구해서 gap 기준 정렬
		for(int mask = 1; mask < (1<<N); mask++) {
			int caseSum = 0;
			for(int i = 0; i < N; i++) {
				if((mask & (1<<i)) != 0) caseSum += popul[i];
			}
			if((caseSum -= (sum-caseSum)) >= 0) caseList.add(new Case(mask,caseSum));
		}
	}
	static boolean isAble(int selected) {
		int visited = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		int i = 0;
		for(; i < N; i++) if((selected & (1<<i)) != 0) break;
		
		q.add(i);
		visited |= (1<<i);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : adjList[cur]) {
				if(((visited & (1<<next)) == 0) && ((selected & (1<<next)) != 0)) {
					visited |= (1<<next);
					q.add(next);
				}
			}
		}
		if(selected == visited) return true;
		return false;
	}
}
