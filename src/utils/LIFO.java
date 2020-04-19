package utils;

import java.util.ArrayList;
import java.util.List;

public class LIFO<T> extends Queue<T> {

    private List<T> arr;

    public LIFO(){
        arr = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        arr.add(item);
    }

    @Override
    public T pop() {
        if (arr.size() == 0){
            return null;
        }
        return arr.remove(arr.size()-1);
    }

    public boolean contains(T x){
        return arr.contains(x);
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}