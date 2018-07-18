package com.dang.book2.chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * UnsafeCountingFactorizer 中的计数过程要想正确是需要 read->n+1->write 这3个步骤是原子不可分割的,我们称这样多个步骤的操作为复合操作<br>
 * 故SafeCountingFactorizer 提供AtomicLong类型的计数器，保证read->n+1->write过程的原子性<br>
 * 结论：在某个类中添加一个且仅一个状态时，若该状态为线程安全类，则该类为线程安全的
 */
public class SafeCountingFactorizer implements Servlet {

    //使用一个线程安全的类，对线程安全类的操作都是线程安全（原子）的
    //Servlet的状态就是计数器的状态，计数器是线程安全的，故Servlet是安全的
    private final AtomicLong hitCount = new AtomicLong(0);

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
        hitCount.incrementAndGet();
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
