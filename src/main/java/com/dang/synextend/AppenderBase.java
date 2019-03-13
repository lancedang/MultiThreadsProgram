package com.dang.synextend;

public abstract class AppenderBase<E> implements Appender<E> {
    @Override
    public synchronized void doAppend(E o) {
        append(o);
    }

    public abstract void append(E e);
}
