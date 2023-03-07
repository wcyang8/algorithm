package baekjoon.wc_bj_1541;

/**
 * 문제 이해.
 * 1. 양수, +, -, 괄호로 이루어진 식
 * 2. 괄호를 모두 지움.
 * 3. 괄호를 적절히 쳐서 식의 값을 최소로 만들어보자.
 * 유의
 * 1. 식의 길이는 50보다 작거나 같다.
 * 2. 5자리보다 많이 연속되는 수는 없다. -> 최대 6자리 이므로 int타입
 * Idea 1
 * 1. 그리디 사용
 * 2. -가 나오면 그 뒤의 수들은 전부 괄호를 활용해 뺄 수 있다.
 * @author SSAFY
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer stInt = new StringTokenizer(s,"+-");
		StringTokenizer stOp = new StringTokenizer(s,"0123456789");
		
		StringTokenizer st = stInt;
		int result = 0;					// 결과값 저장
		boolean minus = false;			// 마이너스면 계속 결과값에서 빼줌
		while(st.hasMoreTokens()) {
			if(st == stInt) {
				int temp = Integer.parseInt(st.nextToken());
				if(minus) temp *= -1;
				result += temp;
				st = stOp;
			}
			else {
				char temp = st.nextToken().charAt(0);
				if(temp == '-' && !minus) minus = true;
				st = stInt;
			}
		}
		System.out.println(result);
	}
}
