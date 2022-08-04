import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Board {

    private int[][] initiles;
    private final int arrayLength;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) 
    {
        initiles = tiles;
        arrayLength = tiles.length;
    }
                                           
    // string representation of this board
    public String toString() 
    {
     String s = arrayLength + "\r\n";
     for(int i = 0; i < arrayLength ; i++) 
     {
         for(int j = 0; j < arrayLength; j++) 
         {
             s+=" " + initiles[i][j];
         }
         s+="\r\n"; // do we need to print the last one ????
     }
     return s;
    }

    // board dimension n
    public int dimension() 
    {
        return arrayLength;
    }

    // number of tiles out of place
    public int hamming() 
    {
        int sum =(initiles[arrayLength-1][arrayLength-1] == 0)? 0 : 1;
        
        for(int i = 0; i < arrayLength ; i++) 
            for(int j = 0; j < arrayLength; j++)
                if(initiles[i][j] != i * arrayLength + j + 1) sum++;
        
        return sum - 1; // the last one expected to be 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() 
    {
        int sum = 0;
        int delta = 0;
        
        for(int i = 0; i < arrayLength ; i++) 
            for(int j = 0; j < arrayLength; j++)
            {
                delta = (initiles[i][j] > 0) ? initiles[i][j] - (i * arrayLength + j + 1) : arrayLength * arrayLength - (i * arrayLength + j + 1);
                if (delta > 0) sum += delta;
            }        
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() 
    {                
        for(int i = 0; i < arrayLength ; i++) 
            for(int j = 0; j < arrayLength; j++)
                if(!(initiles[i][j] == (i * arrayLength + j + 1) || initiles[i][j] == 0)) {
                   // StdOut.println(i + " " + j + " --> " + (i * arrayLength + j + 1)+ " == "+ initiles[i][j]);
                    return false;
                }
        return true; // the last one expected to be 0;
    }

    // does this board equal y?
    public boolean equals(Object y) 
    {
        return false; // watch out!!!
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        return new Iterable<Board>()
        {
            @Override
            public Iterator<Board> iterator()
            {
                return new Iterator<Board>()
                {
                    private int position;
                    private Board[] items = new Board[4]; // or simply = new Board[]{new Board(initiles), new Board(initiles)};

                    @Override
                    public boolean hasNext()
                    {
                        return position != items.length;
                    }

                    @Override
                    public Board next()
                    {
                        if (!hasNext()) throw new NoSuchElementException();
                        return items[position++];
                    }
                };
            }
        };
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() 
    {
        int[][] newArray = initiles.clone();
        Board brd = new Board(newArray);
        int swap = newArray[arrayLength-1][arrayLength-1];
        newArray[arrayLength-1][arrayLength-1] = newArray[0][0];
        newArray[0][0] = swap;
        // StdOut.println(newArray == initiles); // prints "false"
        return brd; // not sure why initial object is dependent and simultaneously changed????
    }

    // unit testing (not graded)
    public static void main(String[] args) 
    {
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board bd = new Board(board);
        StdOut.println(bd.dimension());
        StdOut.println("Hamming: " + bd.hamming());
        StdOut.println("Manhattan: " + bd.manhattan() + " ... not sure though...");
        StdOut.println("Game is over? " + bd.isGoal());
        StdOut.println(bd.toString());
        
        // public Board twin() ???!!!
        int bdnew[][] = new int[board.length][board.length]; 
        for (int i=0; i<board.length; i++)
            for (int j=0; j<board.length; j++)
                bdnew[i][j] = board[i][j]; 
        Board bd2 = new Board(bdnew);
        
        StdOut.println("Changed:" + bd.twin().toString());
        
        StdOut.println("Cached copy:" + bd2.toString());
        
        StdOut.println("Equals: " + bd.equals(bd2)); // but why this is false???
        
        
    }
}