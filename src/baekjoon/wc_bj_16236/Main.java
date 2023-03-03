package baekjoon.wc_bj_16236;

/**
 * 시간 초과 -> 무한루프를 체크.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class node implements Comparable<node>{
	int i;
	int j;
	int dist;
	public node(int i, int j, int dist) {
		super();
		this.i = i;
		this.j = j;
		this.dist = dist;
	}
	@Override
	public int compareTo(node o) {			// 거리 오름차순 -> i 오름차순 -> j 오름차순
		return (this.dist < o.dist)? -1: (this.dist > o.dist)? 1 : (this.i < o.i)? -1 : (this.i > o.i)? 1 : (this.j < o.j)? -1 : (this.j > o.j)? 1:0;
	}
	@Override			// test code
	public String toString() {
		return "node [i=" + i + ", j=" + j + ", dist=" + dist + "]";
	}
	
}

public class Main {

	static int N, bsi, bsj, nti, ntj, curLV = 2, curEXP = 0;
	static int[][] d = {{-1,0},{0,-1},{0,1},{1,0}};
	static int[][] Area;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		Area = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				Area[i][j] = Integer.parseInt(st.nextToken());
				if(Area[i][j] == 9) {
					bsi = i;
					bsj = j;
				}
			}
		}
		System.out.println(simulation());
	}

	static int simulation() {
		int T = 0;
		int t;
		while((t = findTarget()) != -1) {
			T += t;
			eat();
//			printArea(T);							// Debug code
		}
		return T;
	}
	static int findTarget() {						// BFS
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<node> q = new PriorityQueue<node>();
		nti = ntj = -1;
		q.add(new node(bsi,bsj,0));
		visited[bsi][bsj] = true;
		while(!q.isEmpty()) {
			System.out.println(q);
			node cur = q.poll();
			int ci = cur.i;
			int cj = cur.j;
			if(Area[ci][cj] > 0 && Area[ci][cj] < curLV && Area[ci][cj] != 9) {			// 예외를 생각 못했음 -> 무한루프 -> 시간초과 라고 생각함.
				nti = ci;
				ntj = cj;
				return cur.dist;
			}
			for(int k = 0; k < 4; k++) {
				int ni = ci + d[k][0];
				int nj = cj + d[k][1];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
					if(!visited[ni][nj] && Area[ni][nj] <= curLV) {			// 다음 영역의 물고기가 현재 레벨보다 낮으면
//						if(Area[ni][nj] != 0 && Area[ni][nj] != curLV) {
//							nti = ni;
//							ntj = nj;
//							return cur.dist + 1;
//						}
						q.add(new node(ni,nj,cur.dist+1));
						visited[ni][nj] = true;
					}
				}
			}
		}
		return -1;
	}
	static void eat() {
		Area[bsi][bsj] = 0;			// 위치 이동
		Area[nti][ntj] = 9;
		bsi = nti;
		bsj = ntj;
		
		curEXP++;					// 경험치 1 증가
		if(curEXP >= curLV) {		// 경험치가 꽉 찼으면 레벨업
			curEXP = 0;
			curLV++;
		}
	}
	static void printArea(int T) {					//Debug code
		for(int[] a : Area) {
			for(int i : a) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println("===============");
		System.out.println("LV : "+curLV+" EXP : "+curEXP+" totalTime : "+T);
		System.out.println("===============");
	}
}
