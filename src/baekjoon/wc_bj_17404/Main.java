package baekjoon.wc_bj_17404;

import java.io.*;
import java.util.*;
public class Main {
    static String[] intToMonth = {"","Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
    static Map<String,Integer> month = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());
        String week = br.readLine(); // 요일
        init();
        int weeki = month.get(week) - 1;

        int days = countDays(m2, d2) - countDays(m1,d1);

        System.out.println(days/7 + (days%7 + (weeki - 1))/7);
    }
    static int[] dayOfMonth = {0,31,29,31,30,31,30,31,31,30,31,30,31};
    private static int countDays(int month, int day){
        int count = 0;
        for(int m = 1; m < month; m++){
            count += dayOfMonth[m];
        }
        count += day;
        return count;
    }
    private static void init(){
        for(int i = 1; i <= 7; i++){
            month.put(intToMonth[i],i);
        }
    }
}
