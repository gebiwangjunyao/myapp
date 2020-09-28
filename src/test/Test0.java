package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test0 {

    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.now();
        System.out.println(date1); //2018-02-11T13:02:49.957

        DateTimeFormatter dtformat1 =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS E");
        String fdate1 = dtformat1.format(date1);
        System.out.println(fdate1); // 2018/02/11 13:02:49.957 日

        DateTimeFormatter dtformat2 =
          DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒SSSミリ秒 E曜日");
        String fdate2 = dtformat2.format(date1);
        System.out.println(fdate2); // 2018年02月11日 13時02分49秒957ミリ秒 日曜日

        DateTimeFormatter dtformat3 =
            DateTimeFormatter.ofPattern("HHmm");
                String fdate3 = dtformat3.format(date1);
                System.out.println(fdate3); // 2018021113024
    }

}
