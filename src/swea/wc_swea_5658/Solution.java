package swea.wc_swea_5658;
/**
 * 문제 이해
 * 1. 16진수가 적힌 보물상자
 * 2. 뚜껑은 시계방향 회전
 * 3. 각 변에는 동일한 개수의 숫자
 * 4. 각 변은 시계 방향 순으로 읽으면 된다.
 * 5. 자물쇠의 비밀번호는 N개의 숫자로 만들 수 있는 모든 수 중 K번째로 큰 수를 10진수로 만든 수.
 * 6. 비밀번호를 찾아보자.
 * 유의
 * 1. 중복되는 수는 카운트 하지 않는다.
 * 2. N은 4의 배수. 8이상 28이하의 정수
 * 3. K는 생성 가능한 수의 개수보다 크게 주어지지 않는다.
 *	// 생각 못한 부분
 * 4. 7자리 16진수 = (2^4)^7 = 2^28 이므로 int 출력.
 * Idea
 * 1. Set에 다 넣은 다음 sort? -> TreeSet에 넣자.
 * 2. N/4 - 1 회전만 조사하면 됨.
 * 결론 : N/4 회전 후, TreeSet에 add = O(NlogN)
 * N은 최대 28이라 사실 의미가 별로 없다..
 * @author SSAFY
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String box = br.readLine();
			
			sb.append('#').append(tc).append(' ').append(getPw(box+box,N,K)).append('\n');
		}
		System.out.print(sb);
	}

	private static int getPw(String box, int N, int K) {
		TreeSet<Integer> ts = new TreeSet<>();
		
		for(int rot = 0; rot < N/4; rot++) {	// 회전 N/4회
			for(int i = 0; i < 4; i++) {		// i번째 면
				int cur = i * (N/4) + rot;		// 현재 위치부터 잘라내기
				ts.add(Integer.parseInt(box.substring(cur, cur+N/4), 16));		// 현재 위치부터 길이 N/4만큼 자르기
			}
		}
		for(int i = 0; i < K-1; i++) ts.pollLast();	// 앞의 9자리 제거
		return ts.pollLast();
	}
}
