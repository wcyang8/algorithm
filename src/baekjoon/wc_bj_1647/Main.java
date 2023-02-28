package baekjoon.wc_bj_1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class pair implements Comparable<pair>{
	int dest;
	int cost;
	public pair(int dest, int cost) {
		super();
		this.dest = dest;
		this.cost = cost;
	}
	@Override
	public int compareTo(pair o) {
		return (this.cost > o.cost)? 1 : (this.cost == o.cost)? 0 : -1;
	}
	
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//N 입력
		int M = Integer.parseInt(st.nextToken());	//M 입력
		
		int house[] = new int[N];			//집 생성
		
		List[] road = new LinkedList[N];	//간선 생성
		for(int i = 0; i < N; i++) {
			road[i] = new LinkedList<pair>();	//간선 초기화
		}
		
		int A, B, C;
		for(int i = 0; i < M; i++) {		//간선 입력
			st = new StringTokenizer(br.readLine());	//1개의 간선 입력
			
			A = Integer.parseInt(st.nextToken());		//A 받기
			B = Integer.parseInt(st.nextToken());		//B 받기
			C = Integer.parseInt(st.nextToken());		//C 받기
			
			road[A].add(new pair(B,C));
		}
		
	}

}
