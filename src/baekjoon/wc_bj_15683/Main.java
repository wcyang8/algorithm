package baekjoon.wc_bj_15683;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, camNum;
	static int[][] office;
	static int[][] temp;
	static List<Integer> camera;
	static int[][][] camDir;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());		//앞에 int 붙이는 실수 조심
		M = Integer.parseInt(st.nextToken());
		office = new int[N][M];						//원본 map
		temp = new int[N][M];						//복사 map
		camera = new ArrayList<Integer>();			//camera의 위치, type 저장해줄 List
		
		camDir = new int[5][][];					//카메라 타입 별 감시 방향
		camDir[0] = new int[][]{{0,1}};
		camDir[1] = new int[][] {{0,1},{0,-1}};
		camDir[2] = new int[][] {{0,1},{-1,0}};
		camDir[3] = new int[][] {{0,1},{-1,0},{0,-1}};
		camDir[4] = new int[][] {{0,1},{-1,0},{0,-1},{1,0}};
		
		int zero = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if(office[i][j] == 6) continue;
				if(office[i][j] > 0) {
					camera.add(i);					//카메라 위치 저장
					camera.add(j);
					camera.add(office[i][j]-1);		//카메라 종류 저장
				}
				else zero++;						//office 안의 0 개수
			}
		}
		camNum = camera.size() / 3;					//3칸을 쓰므로 3으로 나누면 사이즈
		
		int max = 0;								//각 조합별 감시중인 구역(-1) 최대값
		for(int state = 0; state < (1<<(2*camNum)); state++){		//4^(camNum) 완전 탐색
			reset();
			max = Math.max(max,simul(state));
		}
		System.out.println(zero - max);
	}
	static void reset() {					//조합마다 temp를 리셋해줄 함수
		for(int i = 0; i < N; i++) {
			System.arraycopy(office[i], 0, temp[i], 0, M);
		}
	}
	static int simul(int state) {					//시뮬레이션
		int result = 0;								//정답 저장할 변수
		for(int cam = 0; cam < camNum; cam++) {
			int ci = camera.get(cam*3 + 0);					//카메라 i좌표 load
			int cj = camera.get(cam*3 + 1);					//카메라 j좌표 load
			int type = camera.get(cam*3 + 2);				//카메라 type load
			int rot = (((state & (1<<(2*cam))) != 0)?1:0) + (((state & (1<<(2*cam + 1))) != 0)?2:0);		//회전 횟수 load
			for(int[] d: camDir[type]) {			//타입별 감시 방향 load
				int di = d[0];
				int dj = d[1];
				for(int r = 0; r < rot; r++) {		//90도 회전
					int tmp = di;
					di = dj;
					dj = -tmp;
				}									//회전 끝
				for(int s = 1; ci + di*s >= 0 && ci + di*s < N && cj + dj*s >=0 && cj + dj*s < M;s++) {		//설정된 방향으로 감시 시작
					if(temp[ci+di*s][cj+dj*s] == 6) break;			//벽을 만나면 감시 끝
					if(temp[ci+di*s][cj+dj*s] == 0) {				//빈 공간 & 감시 안된 구역이면
						result++;									//카운트 해주고
						temp[ci+di*s][cj+dj*s] = -1;				//-1로 값 변경
					}
				}
			}
		}
		return result;								//현재 시뮬레이션의 감시 영역 개수 return
	}
}
