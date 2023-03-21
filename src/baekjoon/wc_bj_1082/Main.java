package baekjoon.wc_bj_1082;

/**
 * 요약
 * 1. 숫자를 구매하기 위해 M원을 준비했다.
 * 2. 0부터 N-1까지 숫자가 있다. (N <= 10)
 * 3. 숫자 i의 가격은 P_i 이다.
 * 4. 같은 숫자 여러개 구매 가능.
 * 5. 0으로는 시작할 수 없다.
 * 
 * 풀이
 * 1. 자릿수가 큰게 1순위 (0이 아닌 수 중에 가장 큰 수를 첫자리로 고른다.)
 * 2. 가장 싼 것 중에 가장 높은걸로 채운다.
 * 3. 남은 돈으로 높은 자리 수부터 갈아낀다.
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class pair implements Comparable<pair>{
	int index;
	int cost;
	
	public pair(int index, int cost) {
		super();
		this.index = index;
		this.cost = cost;
	}

	public int compareTo(pair o) {
		return (this.cost - o.cost) == 0? (o.index - this.index): (this.cost-o.cost);
	}

	@Override
	public String toString() {
		return "pair [index=" + index + ", cost=" + cost + "]";
	}
}

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		pair[] cost = new pair[N];
		pair[] cost2 = new pair[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cost[i] = new pair(i, Integer.parseInt(st.nextToken()));
			cost2[i] = cost[i];
		}
		
		int M = Integer.parseInt(br.readLine());
		
		Arrays.sort(cost);
		//System.out.println(Arrays.toString(cost));
		
		List<Integer> roomNum = new ArrayList<>();
		
		// 0이 아닌 것 중 가장 싼걸로 맨 윗자리 채우기.
		if(cost[0].index == 0) {
			if(cost[1].cost > M) {
				System.out.println(0);
				return;
			}
			M -= cost[1].cost;
			roomNum.add(cost[1].index);
		}
		
		// 가장 싼걸로 나머지 채우기
		while(M > 0) {
			M -= cost[0].cost;
			roomNum.add(cost[0].index);
		}
		
		// M을 초과해서 구매한 경우 돌려주기
		if(M < 0) {
			M += cost[0].cost;
			roomNum.remove(roomNum.size()-1);
		}
//		System.out.println(roomNum.toString());
		
//		System.out.println(M);
		// 첫자리부터 갈아끼우기
		for(int i = 0; i < roomNum.size(); i++) {
			if(M == 0) break;
			for(int j = N-1; j >= 0; j--) {
				//System.out.println(cost2[j].cost +" "+ cost2[roomNum.get(i)].cost +" " + M);
				if(cost2[j].cost - cost2[roomNum.get(i)].cost <= M) {
					M -= (cost2[j].cost - cost2[roomNum.get(i)].cost);
					roomNum.set(i, j);
					break;
				}
			}
		}
		
		for(int i : roomNum) sb.append(i);
		System.out.println(sb);
		return;
	}

}
