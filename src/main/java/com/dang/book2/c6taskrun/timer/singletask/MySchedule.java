package com.dang.book2.c6taskrun.timer.singletask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Dangdang on 2018/8/20.
 */
public class MySchedule {
    public static void main(String[] args) {

        //此3行只是为了获得当前时间秒数是多少，便于直观结果查看
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentSeconds = calendar.get(Calendar.SECOND);

        //定义main开始时间，便于检验timer delay时间
        System.out.println(Thread.currentThread().getName() + " start at " + currentSeconds);

        //delaySchedule();
        //delayRepeatedSchedule();
        assignAbsoluteStartTimeSchedule();

        System.out.println(Thread.currentThread().getName() + " end at " + currentSeconds);
    }

    /**
     * 延迟执行，参数为延迟毫秒数
     */
    public static void delaySchedule() {
        MyTask task = new MyTask();
        Timer timer = new Timer();
        //延迟执行，参数为延迟million second
        timer.schedule(task, 1000 * 3);
    }

    /**
     * 先延迟后周期执行
     */
    public static void delayRepeatedSchedule() {
        MyTask task = new MyTask();
        Timer timer = new Timer();

        //先延迟，后重复执行，前面参数是delay，后面是period周期毫秒
        //当任务task执行所需时间超过period时，以task执行时间为准，
        //即，当task需要6s才能结束，则period变成6s
        timer.schedule(task, 1000 * 5, 1000 * 4);
    }

    /**
     * 定义指定在某个绝对时间开始执行的周期任务
     */
    public static void assignAbsoluteStartTimeSchedule() {
        MyTask task = new MyTask();
        Timer timer = new Timer();

        Calendar calendar = Calendar.getInstance();
        //定义ask开始执行的绝对时间
        calendar.set(2018, Calendar.AUGUST, 21, 00, 29, 50);
        Date startTime = calendar.getTime();

        //绝对时间开始，后重复执行，前面参数是Date类型，后面是period周期毫秒
        timer.schedule(task, startTime, 1000 * 4);
    }

}
