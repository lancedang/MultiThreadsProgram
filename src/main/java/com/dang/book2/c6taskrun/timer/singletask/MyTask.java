package com.dang.book2.c6taskrun.timer.singletask;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Dangdang on 2018/8/20.
 * Timer 中的task只需继承TimerTask抽象类
 */
public class MyTask extends TimerTask {
    @Override
    public void run() {

        //此3行只是为了获得当前时间秒数是多少，便于直观结果查看
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentSeconds = calendar.get(Calendar.SECOND);

        //taskTimeLessThanPeriod(currentSeconds);
        taskTimeLongerThanPeriod(currentSeconds);

    }

    /**
     * 情况1-定义任务所需时间小于Timer周期period时间长度的情况
     *
     * @param currentSeconds
     */
    public void taskTimeLessThanPeriod(int currentSeconds) {
        System.out.println(Thread.currentThread().getName() + " time task start at " + currentSeconds);
    }


    /**
     * 情况2-定义任务所需时间大于Timer周期period时间长度的情况
     *
     * @param currentSeconds
     */
    public void taskTimeLongerThanPeriod(int currentSeconds) {
        try {
            System.out.println(Thread.currentThread().getName() + " time task start at " + currentSeconds);

            //定义task执行时间需要6s，长于period的4s
            Thread.sleep(6000);
        } catch (InterruptedException e) {

        }
    }


}
