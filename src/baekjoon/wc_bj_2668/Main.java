package baekjoon.wc_bj_2668;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 요약 1. 위 아래의 수 집합 쌍 중에 일치하는 집합을 구해보자.
 * 
 * 풀이 1. 위의 수를 차례로 탐색 2. 위의 수 x와 연결된 아래 수 y라 하자. 3. y를 다시 윗 수로 하는 아랫 수를 찾기를
 * 반복한다. 4. y가 다시 나오기 전까지 x가 나오면 그 수 x와 나왔던 수들을 추가한다. 5. visit 처리하고 다음 수를 탐색한다.
 * 
 * @author 양우철
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		// 표 생성
		int[] f = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			f[i] = Integer.parseInt(br.readLine());
		}

		// 정답 리스트
		List<Integer> ans = new ArrayList<>();

		// 1번부터 탐색
		for (int i = 1; i <= N; i++) {		// i : 현재 정점
			boolean[] visited = new boolean[N + 1];
			int t = f[i];					// f[i] 부터 방문 시작
			while (true) {
				visited[t] = true;			// 방문 정점 visited
				if (f[t] == i) {			// 다음 정점이 현재 노드로 돌아오면
					ans.add(i);				// 현재 정점을 정답 집합에 추가
					break;
				}
				if(visited[f[t]])break;		// 다음 정점이 방문 상태이면 현재 정점은 정답 집합에 들어갈 수 없음
				t = f[t];					// 다음 정점 방문
			}
		}

		sb.append(ans.size()).append("\n");	// 정답 정점들의 개수
		for(int i : ans) {
			sb.append(i).append("\n");		// 정답 정점들
		}
		System.out.println(sb);				// 출력
	}

}
