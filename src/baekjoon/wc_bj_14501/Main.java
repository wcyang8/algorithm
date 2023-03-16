package baekjoon.wc_bj_14501;


/**
 * 요약
 * 1. N+1일째 퇴사를 위해 남은 N일 동안 최대한 많은 상담을 하려고 한다.
 * 2. 하루에 하나씩 서로 다른 사람의 상담을 잡음
 * 3. 걸리는 기간 T_i, 받을 수 있는 금액 P_i
 * 4. 최대 수익을 구해보자.
 * 
 * 풀이
 * 1. 부분집합
 * 2. 그리디?
 * 3. DP? 어떻게 푸는지 모르겠음.
 * @author SSAFY
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] sangdam = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			sangdam[i][0] = Integer.parseInt(st.nextToken());	// 기간 T_i
			sangdam[i][1] = Integer.parseInt(st.nextToken());	// 이익 P_i
		}
		
		// 알고리즘 시작
		int max = 0;		// 최대 이익 저장
		for(int mask = 0; mask < (1<<N); mask++) {
			int used = 0;
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if((mask & (1<<i)) == 0) continue;	// 선택 안된 날짜면 다음거
				if((used & (1<<i)) != 0) break;		// 선택 된게 이미 예약 잡힌 날짜면 다음 경우의 수
				if((i+sangdam[i][0]) > N) continue;
				for(int j = 0; j < sangdam[i][0]; j++){		// 예약 안잡혔으면 예약 잡기
					used |= (1<<(i+j));
				}
				sum += sangdam[i][1];
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}
