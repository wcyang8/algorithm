package baekjoon.wc_bj_1251;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String origin = br.readLine();

        PriorityQueue<String> pq = new PriorityQueue<>();

        for(int i = 1; i < origin.length() - 1; i++){
            for(int j = i+1; j < origin.length(); j++){
                StringBuilder temp = new StringBuilder();
                StringBuilder sb = new StringBuilder(origin.substring(0,i));
                temp.append(sb.reverse());
                sb = new StringBuilder(origin.substring(i,j));
                temp.append(sb.reverse());
                sb = new StringBuilder(origin.substring(j,origin.length()));
                temp.append(sb.reverse());

                pq.add(temp.toString());
//                System.out.println(temp);
            }
        }

        System.out.println(pq.poll());
    }
}
