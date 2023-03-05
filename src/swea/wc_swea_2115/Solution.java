package swea.wc_swea_2115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] honey = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[] temp = new int[M];
			int[][] rev = new int[N][N-M+1];		// (i,j)부터 연속된 M개의 벌통 최대치를 저장할 배열
			
			for(int i = 0; i < N; i++) {			// 연속된 벌통 최대치 구하기
				for(int j = 0; j < N-M+1; j++) {
					System.arraycopy(honey[i], j, temp, 0, M);
					for(int mask = 0; mask < (1<<M); mask++) {		// 벌통 선택 (부분집합)
						int sum = 0;
						int powSum = 0;
						for(int bit = 0; bit < M; bit++) {
							if((mask & (1<<bit)) != 0) {
								sum += temp[bit];
								if(sum > C) break;					// 꿀 최대 양 C 이하
								powSum += temp[bit] * temp[bit];	// 제곱 합
							}
						}
						rev[i][j] = Math.max(rev[i][j], powSum);
					}
				}
			}
			
			int totalMax = 0;				// 총합 최대 수익 구하기.
			for(int i = 0; i < N; i++) {			// 2명의 일꾼이 일할 벌통 선택 (조합 - for문)
				for(int j = 0; j < N-M+1; j++) {
					for(int ni = i; ni < N; ni++) {
						for(int nj = 0; nj < N-M+1; nj++) {
							if(ni == i && nj - j < M) continue;
							totalMax = Math.max(totalMax, rev[i][j] + rev[ni][nj]);
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(totalMax).append('\n');
		}
		System.out.println(sb);
	}
}
