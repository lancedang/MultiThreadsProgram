package com.dang.book2.c6taskrun.timer.multitask;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dangdang on 2018/8/22.
 */
public class SecondUtil {
    public static int getCurrentSecond() {

        //此3行只是为了获得当前时间秒数是多少，便于直观结果查看
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentSeconds = calendar.get(Calendar.SECOND);

        return currentSeconds;
    }
}
