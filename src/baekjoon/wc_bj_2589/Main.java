package baekjoon.wc_bj_2589;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 요약 1. N x M 맵 2. 각 칸은 육지 or 바다 3. 한 칸 이동 시 1시간 걸림 4. 보물은 최단거리 중 가장 긴 육지 두 곳에
 * 나뉘어 묻혀있다. 5. 두 보물 사이를 이동하는 시간을 구하시오.
 * 
 * 풀이 
 * 1. 플로이드 워샬로 각 정점 간의 최단거리를 구함 
 * 2. 그 중 최단 시간 저장하여 출력
 * -> 시간복잡도 문제로 실패 (50*50)^3 = 150억 이상
 * 
 * 풀이2
 * 1. L인 지점마다 BFS 돌려서 거리 최대값 리턴
 * 2. 거리 최대값 중 최대값 찾기
 * 3. 출력
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");

		int N = Integer.parseInt(sArr[0]);
		int M = Integer.parseInt(sArr[1]);

		String[] map = new String[N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}

//		int[][] dp = new int[N * M][N * M];
//
//		for (int i = 0; i < N * M; i++) {
//			Arrays.fill(dp[i], 100);
//		}
//		
//		for(int f = 0; f < N*M; f++) {
//			for(int t = 0; t < N*M; t++) {
//				int gap = Math.abs(f-t);
//				if((gap == 1 || gap == M) && map[f / M].charAt(f % M) == 'L' && map[t / M].charAt(t % M) == 'L') {
//					dp[f][t] = 1;
//					dp[t][f] = 1;
//				}
//			}
//		}
//		
//		for(int p = 0; p < N*M; p++) {
//			for(int f = 0; f < N*M; f++) {
//					if(p == f) continue;
//				for(int t = 0; t < N*M; t++) {
//					if(f == t || t == p) continue;
//					dp[f][t] = Math.min(dp[f][t], dp[f][p] + dp[p][t]);
//				}
//			}
//			for(int f = 0; f < N*M; f++) {
//				for(int t = 0; t < N*M; t++) {
//					System.out.print(dp[f][t]+" ");
//				}
//				System.out.println();
//			}
//		}
//		
//		int max = 0;
//		for(int f = 0; f < N*M; f++) {
//			for(int t = 0; t < N*M; t++) {
//				if(dp[f][t] == 100)continue;
//				max = Math.max(max, dp[f][t]);
//			}
//		}
//		
//		System.out.println(max);
	}
}
