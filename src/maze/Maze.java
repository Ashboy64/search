package maze;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Makes a maze with at least one path from start to end
 */
public class Maze {

    boolean[][] grid;
    Random r;

    public static void main(String[] args){
        Maze m = new Maze(100, 100, 7,"e.ppm");
    }

    public Maze(int rows, int cols, int numWaypoints, String name){
        grid = new boolean[rows][cols]; // (0,0) is start (rows-1, cols-1) is end
        r = new Random();

        generate();
        createPath(numWaypoints);

        save("D:\\Coding\\experiments\\ai_modern_approach\\search\\res\\" + name);
    }

    public void save(String filepath){
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.write("P3\n" + grid[0].length + " " + grid.length + "\n255\n");

            for (int i = 0; i < grid.length; i++){
                for (int j = 0; j < grid[0].length; j++){
                    if (grid[i][j]){
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
    }

    public void generate(){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = r.nextBoolean();
            }
        }
    }

    public void createPath(){
        int[] current = new int[]{0, 0};
        grid[0][0] = true;

        int down = grid.length - 1;
        int along = grid[0].length - 1;

        while (down > 0 || along > 0){
            if (r.nextBoolean() && along > 0){
                current[1]++;
                along--;
            } else if (down > 0){
                current[0]++;
                down--;
            } else {
                current[1]++;
                along--;
            }
            grid[current[0]][current[1]] = true;
        }
    }

    public void clear(int[] current, int[] end){
        current = new int[]{current[0], current[1]};
        grid[current[0]][current[1]] = true;

        int down = end[0] - current[0];
        int along = end[1] - current[1];

        while (down > 0 || along > 0){
            if (r.nextBoolean() && along > 0){
                current[1]++;
                along--;
            } else if (down > 0){
                current[0]++;
                down--;
            } else {
                current[1]++;
                along--;
            }
            grid[current[0]][current[1]] = true;
        }
    }

    public void createPath(int numWaypoints){

        int[][] waypoints = new int[numWaypoints+2][2];
        waypoints[0] = new int[]{0, 0};

        for (int i = 0; i < numWaypoints; i++) {
            waypoints[i+1] = new int[]{r.nextInt(grid.length), r.nextInt(grid[0].length)};
        }

        waypoints[numWaypoints+1] = new int[]{grid.length-1, grid[0].length-1};

        for (int i = 1; i < waypoints.length; i++){
            clear(waypoints[i-1], waypoints[i]);
        }


    }

    public boolean[][] getGrid(){
        return grid;
    }
}
