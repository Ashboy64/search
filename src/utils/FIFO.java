package utils;

import java.util.ArrayList;
import java.util.List;

public class FIFO<T> extends Queue<T> {

    private List<T> arr;

    public FIFO(){
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
        return arr.remove(0);
    }

    @Override
    public boolean contains(T x){
        return arr.contains(x);
    }
}
