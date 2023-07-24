package baekjoon.wc_bj_15685;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[101][101];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

//            Stack<Integer> s1 = new Stack<>();
//            Stack<Integer> s2 = new Stack<>();

            int t = 0;
//            s1.add(d);
//            Queue<Integer> q = new ArrayDeque<>();
//            Stack<Integer>
            List<Integer> prev = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            prev.add(d);
            visited[x][y] = true;
            System.out.println(x + " " + y);
            while(t <= g){
                for(int p : prev){
                    x += dir[p][0];
                    y += dir[p][1];

                    visited[x][y] = true;
                    System.out.println(x + " " + y);
                    stack.add((p+1)%4);
                }
//                    while(!q.isEmpty()){
//                        int k = q.poll();
//                        x += dir[k][0];
//                        y += dir[k][1];
//                        visited[x][y] = true;
//                        System.out.println(x + " " + y);
//                    }
//                if(t % 2 == 0){
//                    // 이동
//                    while(!s1.isEmpty()){
//                        int k = s1.pop();
//                        x += dir[k][0];
//                        y += dir[k][1];
//                        visited[x][y] = true;
//                        q.add(k);
//                        s2.add((k+1)%4);
//                        System.out.println(x + " " + y);
//                    }
//                }else{
//                    while(!s2.isEmpty()){
//                        int k = s2.pop();
//                        x += dir[k][0];
//                        y += dir[k][1];
//                        visited[x][y] = true;
//                        q.add(k);
//                        s1.add((k+1)%4);
//                        System.out.println(x + " " + y);
//                    }
//                }
                while(!stack.isEmpty()) prev.add(stack.pop());
                ++t;
            }
            System.out.println();
        }
        int ans = 0;
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                System.out.print((visited[i][j]?1:0) + " ");
                if(visited[i][j]){
                    if(visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]){
                        ++ans;
                    }
                }
            }
            System.out.println();
        }
        System.out.println(ans);
    }
}
