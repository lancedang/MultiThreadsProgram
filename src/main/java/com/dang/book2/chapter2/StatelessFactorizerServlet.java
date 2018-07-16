package com.dang.book2.chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Created by Dangdang on 2018/7/17.
 * 无状态的对象一定是线程安全的
 * <br>
 * 通常，线程安全性的需求并非来源于对线程的直接使用，而是使用像Servlet这样的框架，该Servlet是无状态的，它既不包含任何域，
 * 也不包含任何对其他类中域的引用
 */
public class StatelessFactorizerServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //局部变量，线程安全
        BigInteger number = extractFromRequest(req);
        BigInteger[] factors = factor(number);
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
