package utils;

public abstract class Queue<T> {
    public abstract void push(T t);
    public abstract T pop();
    public abstract boolean contains(T t);
}