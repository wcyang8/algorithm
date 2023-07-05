package baekjoon.wc_bj_12904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        Deque<Character> dq = new ArrayDeque<>();

        for(int i = 0; i < T.length(); i++){
            dq.add(T.charAt(i));
        }

        boolean counter = false;

        while(S.length() < dq.size()){
            if(!counter){       // 정방향
                if(dq.pollLast() == 'B'){
                    counter = !counter;
                }
            }else{
                if(dq.pollFirst() == 'B'){
                    counter = !counter;
                }
            }
//            System.out.println(dq.toString());
        }

        char[] tmp = new char[dq.size()];

        int pos = 0;
        while(!dq.isEmpty()){
            if(!counter){
                tmp[pos++] = dq.pollFirst();
            }else{
                tmp[pos++] = dq.pollLast();
            }
        }

//        System.out.println(String.valueOf(tmp));

        if(S.equals(String.valueOf(tmp))){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
}