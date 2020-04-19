package maze;

import search.Action;
import search.Problem;
import search.State;

import java.util.ArrayList;
import java.util.List;

public class MazeProblem extends Problem {

    private boolean[][] grid;

    public MazeProblem(int rows, int cols, int numWaypoints, String name){
        grid = (new Maze(rows, cols, numWaypoints, name)).getGrid();
    }

    public State getInitial(){
        return new State("0 0");
    }

    @Override
    public List<Action> actions(State s) {
        String[] strCoords = s.getId().split(" ");
        int[] intCoords = new int[]{Integer.parseInt(strCoords[0]), Integer.parseInt(strCoords[1])};

        List<Action> available = new ArrayList<>();

        for (int i : new int[]{-1, 0, 1}){
            for (int j : new int[]{-1, 0, 1}){

                if (((i != 0 || j != 0) && i*j == 0) && (intCoords[0] + i >= 0 && intCoords[0] + i < grid.length) &&
                        (intCoords[1] + j >= 0 && intCoords[1] + j < grid[0].length)){

                    if (grid[intCoords[0] + i][intCoords[1] + j]){
                        available.add(new Action(i + " " + j));
                    }
                }
            }
        }

        return available;
    }

    @Override
    public State step(State s, Action a) {
        String[] strCoords = s.getId().split(" ");
        int[] intCoords = new int[]{Integer.parseInt(strCoords[0]), Integer.parseInt(strCoords[1])};

        strCoords = a.getId().split(" ");
        int[] intCoordsA = new int[]{Integer.parseInt(strCoords[0]), Integer.parseInt(strCoords[1])};

        intCoords[0] += intCoordsA[0];
        intCoords[1] += intCoordsA[1];

        return new State(intCoords[0] + " " + intCoords[1]);
    }

    @Override
    public boolean isGoal(State s) {
        return s.getId().equals((grid.length-1) + " " + (grid[0].length-1));
    }

    public boolean[][] getGrid(){
        return grid;
    }
}
