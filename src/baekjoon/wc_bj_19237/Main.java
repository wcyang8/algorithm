package baekjoon.wc_bj_19237;

import java.time.LocalDate;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
//        System.out.println(LocalDate.now().minusDays(1));
        System.out.println(Period.between(LocalDate.parse("2023-12-05"), LocalDate.now()).toTotalMonths());
    }

}
