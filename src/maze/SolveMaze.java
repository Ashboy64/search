package maze;

import search.Search;
import search.SearchStatus;
import search.State;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SolveMaze {

    public static void main(String[] args){
        MazeProblem problem = new MazeProblem(200, 200, 1, "problem.ppm");
        solve(problem, "solution.ppm");
    }

    public static void solve(MazeProblem problem, String name){

        SearchStatus status = Search.DFS(problem, problem.getInitial());

        if (status.getStatus().equals("found")){
            List<State> states = status.getSoln();
            String filepath = "D:\\Coding\\experiments\\ai_modern_approach\\search\\res\\" + name;

            boolean[][] grid = problem.getGrid();

            try {
                FileWriter writer = new FileWriter(filepath);
                writer.write("P3\n" + grid[0].length + " " + grid.length + "\n255\n");

                for (int i = 0; i < grid.length; i++){
                    for (int j = 0; j < grid[0].length; j++){

                        State toCheck = new State(i + " " + j);

                        if (states.contains(toCheck)){
                            writer.write("0 100 255");
                        } else if (grid[i][j]){
                            writer.write("255 255 255");
                        } else {
                            writer.write("0 0 0");
                        }

                        if (j != grid[0].length-1){
                            writer.write("\t");
                        }
                    }

                    writer.write("\n");
                }

                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(status.getStatus());
        }
    }

}
