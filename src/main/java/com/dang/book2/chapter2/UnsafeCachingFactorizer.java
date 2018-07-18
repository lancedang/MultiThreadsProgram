package com.dang.book2.chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 该Servlet具备多个状态变量，一个用于记录被分解的number，另一个状态用于缓存因式分解的结果，且每个状态均为线程安全类管理
 */
public class UnsafeCachingFactorizer implements Servlet {

    //因为因式分解的数值是BigInteger类型，所以用AtomicReference来封装，否则直接用AtomicLong也可以
    private AtomicReference<BigInteger> lastNumber;

    //注意泛型参数是数组类型
    private AtomicReference<BigInteger[]> lastFactorors = new AtomicReference();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger number = extractFromRequest(req);
        BigInteger[] factors = null;
        //比较方法是equals而不是==
        if (number.equals(lastNumber.get())) {
            factors = lastFactorors.get();
        }else{
            factors = factor(number);
            //当所要分解值与上次不同时，重新计算并保存结果，
            // 保存的单个动作如保存number，保存数组都是原子的，但两个原子操作在多线程环境下是可以被分隔的
            // 但逻辑上需要满足15=3*5，不能15保存好了，但3和5没有保存或保存成其他值
            //这种同时保存的多个条件具有某种关联，称为"不变性条件",本质是"复合操作的增强版"
            //除了多个操作需要原子进行外，还要满足多个操作间的不变性
            //结论：要保证状态一致性需要在某个原子操作中更新所有相关的状态变量
            lastNumber.set(number);
            lastFactorors.set(factors);
        }

        encodeToResponse(res, factors);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    /**
     * 提取被分解的数
     *
     * @param servletRequest
     * @return
     */
    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        String numberStr = servletRequest.getParameter("number");
        BigInteger number = new BigInteger(numberStr);
        return number;
    }

    /**
     * 执行因数分解的动作
     *
     * @param number
     * @return
     */
    private BigInteger[] factor(BigInteger number) {
        BigInteger[] bigIntegers = new BigInteger[2];
        bigIntegers[0] = new BigInteger("11");
        bigIntegers[1] = new BigInteger("22");
        return bigIntegers;
    }

    /**
     * 将因数分解结果写到response中
     *
     * @param response
     * @param factors
     */
    public void encodeToResponse(ServletResponse response, BigInteger[] factors) {
        try {
            PrintWriter writer = response.getWriter();
            writer.write(factors.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
