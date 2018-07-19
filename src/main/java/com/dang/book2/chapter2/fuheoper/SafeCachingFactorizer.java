package com.dang.book2.chapter2.fuheoper;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 该Servlet具备多个状态变量，一个用于记录被分解的number，另一个状态用于缓存因式分解的结果，且每个状态均为线程安全类管理<br>
 *     但多个线程安全对象的复合操作也不是线程安全，整个复合操作应该保持原子性
 */
public class SafeCachingFactorizer implements Servlet {

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

    /**
     * 用synchronized 内置锁同步某个方法，并发性非常差
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public synchronized void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger number = extractFromRequest(req);
        BigInteger[] factors = null;
        //比较方法是equals而不是==
        if (number.equals(lastNumber.get())) {
            factors = lastFactorors.get();
        }else{
            factors = factor(number);
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
