package search;

import java.util.List;

public abstract class Problem {
    public abstract List<Action> actions(State s);
    public abstract State step(State s, Action a);
    public abstract boolean isGoal(State s);
}