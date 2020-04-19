package search;

import java.util.ArrayList;
import java.util.List;

public class SearchStatus {
    private String status;
    private List<State> soln;

    public SearchStatus(String status, List<State> soln){
        this.status = status;
        this.soln = soln;
    }

    public String getStatus(){
        return status;
    }

    public List<State> getSoln(){
        return soln;
    }
}

class Node {
    private State s;
    private Node prev;
    private Double cost;

    public Node(State s, Node prev){
        this.s = s;
        this.prev = prev;
        cost = null;
    }

    public Node(State s, Node prev, double cost){
        this.s = s;
        this.prev = prev;
        this.cost = cost;
    }

    public List<State> getSolution(){
        List<State> solution = new ArrayList<>();
        unwind(solution);
        return solution;
    }

    public void unwind(List<State> solution){
        solution.add(s);
        if (prev == null){
            return;
        }
        prev.unwind(solution);
    }

    public State getState(){
        return s;
    }

    public double getCost(){
        return cost;
    }

    @Override
    public boolean equals(Object n) {

        if (!(n instanceof Node)){
            return false;
        }

        return s.equals(((Node) n).getState());
    }

    @Override
    public String toString() {
        return s.toString();
    }
}