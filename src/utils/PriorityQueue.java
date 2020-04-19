package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** Uses a binary heap */
public class PriorityQueue<T> extends Queue<T> {

    private List<T> heap;
    private Function<T> f;

    /** Method for testing */
    public static void main(String[] args){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Function<Integer>() {
            @Override
            public double evaluate(Integer o) {
                return (double) o;
            }
        });

        Random r = new Random();
        for(int i = 0; i < 10; i++){
            pq.push(r.nextInt(10));
        }

        Scanner scan = new Scanner(System.in);

        pq.printHeap();
        while (true){
            int toPush = scan.nextInt();
            if (toPush == -1){
                break;
            }
            pq.pop();
            pq.printHeap();
        }

    }

    public PriorityQueue(Function<T> f){
        heap = new ArrayList<>();
        heap.add(null);
        this.f = f;
    }

    @Override
    public void push(T item) {
        heap.add(item);

        int i = heap.size()-1;
        while ((i > 1) && (f.evaluate(heap.get(i/2)) < f.evaluate(heap.get(i)))){
            T temp = heap.get(i/2);
            heap.set(i/2, item);
            heap.set(i, temp);
            i/=2;
        }
    }

    @Override
    public T pop() {
        if (heap.size() == 1){
            return null;
        } else if (heap.size() == 2){
            return heap.remove(1);
        }

        T toReturn = heap.get(1);
        T toFix = heap.remove(heap.size()-1);

        heap.set(1, toFix);
        int i = 1;

        while (((2*i < heap.size()) && (f.evaluate(heap.get(i)) < f.evaluate(heap.get(2*i)))) ||
                ((2*i+1 < heap.size()) && (f.evaluate(heap.get(i)) < f.evaluate(heap.get(2*i+1))))){
            // Switch with the larger value
            T toSwitch;
            if ((2*i+1 >= heap.size()) || (f.evaluate(heap.get(2*i)) > f.evaluate(heap.get(2*i+1)))){
                toSwitch = heap.get(2*i);
                heap.set(2*i, heap.get(i));
                heap.set(i, toSwitch);
                i*=2;
            } else {
                toSwitch = heap.get(2*i+1);
                heap.set(2*i+1, heap.get(i));
                heap.set(i, toSwitch);
                i = 2*i + 1;
            }
        }

        return toReturn;
    }

    @Override
    public boolean contains(T x){
        return heap.contains(x);
    }

    public void printHeap(){
        System.out.println(heap);
    }

    public T inHeap(T x){
        return heap.get(heap.indexOf(x));
    }

    /** Change object x in heap to object y and then fix the heap */
    public void modify(T x, T y){
        int i = heap.indexOf(x);
        heap.set(i, y);

        if (f.evaluate(y) > f.evaluate(heap.get(i/2))){
            // Fix upwards
            while ((i > 1) && (f.evaluate(heap.get(i/2)) < f.evaluate(heap.get(i)))){
                T temp = heap.get(i/2);
                heap.set(i/2, y);
                heap.set(i, temp);
                i/=2;
            }
        } else if ((f.evaluate(y) < f.evaluate(heap.get(2*i))) || (f.evaluate(y) < f.evaluate(heap.get(2*i+1)))){
            // Fix downwards
            while (((2*i < heap.size()) && (f.evaluate(heap.get(i)) < f.evaluate(heap.get(2*i)))) ||
                    ((2*i+1 < heap.size()) && (f.evaluate(heap.get(i)) < f.evaluate(heap.get(2*i+1))))){
                // Switch with the larger value
                T toSwitch;
                if ((2*i+1 >= heap.size()) || (f.evaluate(heap.get(2*i)) > f.evaluate(heap.get(2*i+1)))){
                    toSwitch = heap.get(2*i);
                    heap.set(2*i, heap.get(i));
                    heap.set(i, toSwitch);
                    i*=2;
                } else {
                    toSwitch = heap.get(2*i+1);
                    heap.set(2*i+1, heap.get(i));
                    heap.set(i, toSwitch);
                    i = 2*i + 1;
                }
            }
        }
    }
}
