import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private Board board;
    private MinPQ<Board> queue;
    // private final variables ...
    // Create an immutable data type Solver
    // find a solution to the initial board (using the A* algorithm)
    
    public Solver(Board initial) 
    {
        if (initial == null)
            throw new IllegalArgumentException(" Initial board cannot be null. ");
        board = initial;
                 
     
    }
    // is the initial board solvable? (see below)
    
    public boolean isSolvable() 
    {
        return !( /* board.initiles[length-1; length-1] == 0 && */ board.manhattan() == 1 && board.hamming() == 2);  
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() 
    {
        return -1; // for an unsolvable board
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
    
        return null; // for an unsolvable board
    }

        
    // test client (see below) 
    public static void main(String[] args)
    { 
 // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
      }
    }
}
