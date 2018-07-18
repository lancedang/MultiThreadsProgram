package com.dang.book2.chapter2;

/**
 * Created by Dangdang on 2018/7/18.
 * 竞态条件-先检查后运行的一个常用体现是"延迟初始化"的场景，延迟初始化的一个体现是单例模式，但单例模式是自己创建自己额实例，<br>
 * 延迟初始化不限制创造什么类型的实例，也可以是其他类型的对象，而为什么要延迟的原因是某个对象的初始化特别耗时、耗费资源，所以不到需要的时候<br>
 * 不去创建它
 */
public class LazyInitRace {
    //引用某各类型,将被延迟初始化
    private ExpensiveObject target;

    public ExpensiveObject getInstance() {
        //先检查后判断
        if (target == null) {
            target = new ExpensiveObject();
        }
        //hashcode 标记是否是同一个实例，可以将getInstance方法标记 synchronized ，对比验证
        System.out.println(target.getClass().getSimpleName() + "|" +target.hashCode());
        return target;
    }

}

class ExpensiveObject {
    //可以将对象的构造方法用sleep模拟构造耗时，体现expensive，不加sleep也能体现出来
    public ExpensiveObject() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
