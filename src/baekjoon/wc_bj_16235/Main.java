package baekjoon.wc_bj_16235;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가장 처음에 모든 칸에 양분 5가 들어있다.
 * 봄 : 나이만큼 양분을 먹는다. 나이가 어린 나무부터 양분을 먹는다. 양분이 부족하면 즉시 죽는다.
 * 여름 : 죽은 나무가 양분이 된다. 죽은 나무 나이 / 2 가 양분으로 그 칸에 추가된다.
 * 가을 : 나무가 번식한다. 나이가 5의 배수인 나무만 번식한다. 인접한 1개의 칸에 나이가 1인 나무가 생긴다.
 * 겨울 : 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이다.
 *
 * K 년 후 상도의 땅에 살아있는 나무의 개수는?
 */

public class Main {

    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] ground = new int[N][N];

        for(int i = 0; i < N; i++) Arrays.fill(ground[i], 5);

        int[][] A = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer>[][] tree = new ArrayList[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tree[i][j] = new ArrayList<Integer>();
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            tree[x][y].add(z);
        }

        // k년 반복
        for(int k = 0; k < K; k++){
            // 봄
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    // 나무가 있는 경우만
                    if(tree[i][j].size() > 0){
                        for(int t = tree[i][j].size()-1; t >= 0; t--) {
                            if (tree[i][j].get(t) <= ground[i][j]) {
                                // 영양분이 충분하면
                                // 성장
                                ground[i][j] -= tree[i][j].get(t);
                                tree[i][j].set(t, tree[i][j].get(t) + 1);
                            } else {
                                // 영양분이 부족하면
                                // 죽음
                                tree[i][j].set(t, -tree[i][j].get(t));
                            }
                        }
                    }
                }
            }
            // 여름
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int t = 0; t < tree[i][j].size(); t++) {
                        // 나무가 죽어 있는 경우
                        // 영양분이 된다
                        if (tree[i][j].get(t) < 0) {
                            ground[i][j] -= tree[i][j].get(t) / 2;
                            tree[i][j].set(t, 0);
                        }
                    }
                }
            }
            // 가을
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int t = 0; t < tree[i][j].size(); t++) {
                        // 나이가 5의 배수인 경우
                        if (tree[i][j].get(t) % 5 == 0) {
                            for(int[] d : dir){
                                int ni = i + d[0];
                                int nj = j + d[1];
                                if(ni >= 0 && ni < N && nj >= 0 && nj < N){
                                    tree[i][j].add(1);
                                }
                            }
                        }
                    }
                }
            }
            // 겨울
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    ground[i][j] += A[i][j];
                }
            }

            System.out.println("======ground======");
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    System.out.print(ground[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("==================");
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int t : tree[i][j]){
                        System.out.print(t + "|");
                    }
                    System.out.println("\t");
                }
                System.out.println();
            }
        }

        // 살아남은 나무의 수 계산
        int total = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                total += tree[i][j].size();
            }
        }

        System.out.println(total);
    }
}
