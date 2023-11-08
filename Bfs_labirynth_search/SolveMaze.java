package Bfs_labirynth_search;
import java.util.*;
import java.io.*;
// This task is to find the shortest path in a maze from start to finish.
// The maze is represented by a text file, where:
// W - wall
// C - corridor
// S - start
// F - finish
// The program finds the shortest path and prints it to the console.
// It also saves the maze with paths and the shortest path to a text file.
// The program uses a simple implementation of the BFS algorithm.
public class SolveMaze {
    private int[][] maze;
    private int[][] maze_org;
    int fin_n;
    int endX,endY;
    public SolveMaze() {
        fin_n = 1;
        endX = 0;
        endY = 0;
    }
    private void loadMaze(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        maze = new int[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\t");
            maze[i] = new int[parts.length];
            for (int j = 0; j < parts.length; j++) {
                switch (parts[j]) {
                    case "W":
                        maze[i][j] = -1;
                        break;
                    case "C":
                        maze[i][j] = 0;
                        break;
                    case "S":
                        maze[i][j] = 1;
                        break;
                    case "F":
                        maze[i][j] = -2;
                        break;
                }
            }
        }
        maze_org = maze.clone();
        sc.close();
    }
    private void find_last(){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == -2) {
                    endX = i;
                    endY = j;
                    break;
                }
            }
        }
    }
    private void numberAdjacent() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == fin_n) {
                    if (i > 0 && maze_org[i - 1][j] == 0){maze[i - 1][j] = fin_n + 1;}
                    if (j > 0 && maze_org[i][j - 1] == 0){maze[i][j - 1] = fin_n + 1;}
                    if (i < maze.length - 1 && maze_org[i + 1][j] == 0){maze[i + 1][j] = fin_n + 1;}
                    if (j < maze[i].length - 1 && maze_org[i][j + 1] == 0){maze[i][j + 1] = fin_n + 1;}
                }
            }
        }
    }
    public void makePaths() {
        while (true) {
            numberAdjacent();
            fin_n++;
            if(maze[endX][endY-1] == fin_n || maze[endX-1][endY] == fin_n || maze[endX+1][endY] == fin_n){break;}
        }
    }
    public int[][] backtrack() {
        int[][] path = new int[maze.length][maze[0].length];
        for (int[] row : path) {Arrays.fill(row, 0);}
        int x = endX, y = endY;
        for (int i = fin_n+1; i > 0; i--) {
            if (x > 0 && maze[x - 1][y] == i - 1) {x--;} 
            else if (y > 0 && maze[x][y - 1] == i - 1) {y--;} 
            else if (x < maze.length - 1 && maze[x + 1][y] == i - 1) {x++;} 
            else if (y < maze[0].length - 1 && maze[x][y + 1] == i - 1) {y++;}
            path[x][y] = i-1;
        }
        return path;
    }
    public void print(int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        SolveMaze maze = new SolveMaze();
        try {
            maze.loadMaze("maze_txt.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        maze.find_last();
        maze.makePaths();
        System.out.println("Maze with paths:");
        maze.print(maze.maze);
        int[][] path = maze.backtrack();
        System.out.println("Shortest path:");
        maze.print(path);
        try {
            File bfs_paths = new File("bfs_paths.txt");
            File shortest_path = new File("shortest_path.txt");
            PrintWriter pw = new PrintWriter(bfs_paths);
            PrintWriter pw2 = new PrintWriter(shortest_path);
            for (int i = 0; i < maze.maze.length; i++) {
                for (int j = 0; j < maze.maze[i].length; j++) {
                    pw.print(maze.maze[i][j] + "\t");
                    pw2.print(path[i][j] + "\t");
                }
                pw.println();
                pw2.println();
            }
            pw.close();
            pw2.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not able to be created");
        }
    }
}