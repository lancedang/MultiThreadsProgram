package com.dang.book2.chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Created by Dangdang on 2018/7/18.
 * 无状态对象中加入一个状态，此类与chapter1中的UnsafeSequence本质是一致的，都是对同一个对象的成员变量进行操作，<br>
 * 主要区别是UnSafeSequence是普通的共享对象，而Servlet属于Java web 框架，在运行时仍是单个Servlet对象处理多个请求<br>
 * n++这种属于典型的竞态条件的一种，即后面的动作取决于前面执行的结果，简言之先检查后执行（check-than-act),但检查-运行中间有时间差<br>
 * 从而给其他动作的插入传告了机会，导致前面结果失效
 */
public class UnsafeCountingFactorizer implements Servlet {

    private int hitCount;

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
        BigInteger[] factors = factor(number);
        //记录命中次数
        hitCount++;
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
