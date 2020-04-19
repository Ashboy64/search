package search;

import utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search {

    public static SearchStatus BFS(Problem p, State s){
        Queue<Node> frontier = new FIFO<Node>();
        Map<Node, Boolean> visited = new HashMap<>();
        Node curr = new Node(s, null);

        while (true){
            visited.put(curr, true);
            List<Action> actions = p.actions(curr.getState());

            for (Action a : actions){
                Node n = new Node(p.step(curr.getState(), a), curr);

                if (p.isGoal(n.getState())){
                    return new SearchStatus("found", n.getSolution());
                }

                if (!contains(visited, n) && !frontier.contains(n)){
                    frontier.push(n);
                }
            }

            curr = frontier.pop();

            if (curr == null){
                return new SearchStatus("not found", null);
            }
        }
    }

    public static SearchStatus DFS(Problem p, State s){
        Queue<Node> frontier = new LIFO<>();
        Map<Node, Boolean> visited = new HashMap<>();
        Node n = new Node(s, null);

        return recursiveDFS(p, n, frontier, visited, null);
    }

    public static SearchStatus depthLimitedDFS(Problem p, State s, int limit){
        LIFO<Node> frontier = new LIFO<>();
        Map<Node, Boolean> visited = new HashMap<>();
        Node n = new Node(s, null);

        return recursiveDFS(p, n, frontier, visited, limit);
    }

    private static SearchStatus recursiveDFS(Problem p, Node n, Queue<Node> frontier,
                                             Map<Node, Boolean> visited, Integer limit){
        if (n == null){
            return new SearchStatus("not found", null);
        }

        if (limit == null || limit > 0){

            visited.put(n, true);

            if (p.isGoal(n.getState())){
                return new SearchStatus("found", n.getSolution());
            }

            List<Action> actions = p.actions(n.getState());

            for (Action a : actions){
                Node newN = new Node(p.step(n.getState(), a), n);

                if (!contains(visited, newN) && !frontier.contains(newN)){
                    frontier.push(newN);
                }
            }

            return recursiveDFS(p, frontier.pop(), frontier, visited, (limit == null) ? null : limit-1);
        } else {
            return new SearchStatus("hit limit", null);
        }
    }

    public static SearchStatus uniformCost(CostProblem p, State s){
        PriorityQueue<Node> frontier = new PriorityQueue<>(new Function<Node>() {
            @Override
            public double evaluate(Node node) {
                return -1*node.getCost(); // The lower the cost the better it is
            }
        });

        Map<Node, Boolean> visited = new HashMap<>();
        Node curr = new Node(s, null, 0);

        while (true){
            if (p.isGoal(curr.getState())){
                return new SearchStatus("found", curr.getSolution());
            }

            visited.put(curr, true);
            List<Action> actions = p.actions(curr.getState());

            for (Action a : actions){
                Node n = new Node(p.step(curr.getState(), a), curr, curr.getCost() + p.cost(s,a));

                if (!contains(visited, n)){
                    if (!frontier.contains(n)){
                        frontier.push(n);
                    } else if (frontier.inHeap(n).getCost() > n.getCost()){
                        frontier.modify(n, n);
                    }
                }
            }

            curr = frontier.pop();

            if (curr == null){
                return new SearchStatus("not found", null);
            }
        }
    }

    public static SearchStatus aStar(CostProblem p, State s, Function<State> heuristic){
        PriorityQueue<Node> frontier = new PriorityQueue<>(new Function<Node>() {
            @Override
            public double evaluate(Node node) {
                return -1*node.getCost() + heuristic.evaluate(node.getState()); // The lower the cost the better it is
            }
        });

        Map<Node, Boolean> visited = new HashMap<>();
        Node curr = new Node(s, null, 0);

        while (true){
            if (p.isGoal(curr.getState())){
                return new SearchStatus("found", curr.getSolution());
            }

            visited.put(curr, true);
            List<Action> actions = p.actions(curr.getState());

            for (Action a : actions){
                Node n = new Node(p.step(curr.getState(), a), curr, curr.getCost() + p.cost(s,a));

                if (!contains(visited, n)){
                    if (!frontier.contains(n)){
                        frontier.push(n);
                    } else if (frontier.inHeap(n).getCost() > n.getCost()){
                        frontier.modify(n, n);
                    }
                }
            }

            curr = frontier.pop();

            if (curr == null){
                return new SearchStatus("not found", null);
            }
        }
    }

    public static void bidirectional(Problem p){

    }

    private static boolean contains(Map m, Object o){
        for (Object key : m.keySet()){
            if (o.equals(key)){
                return true;
            }
        }
        return false;
    }
}