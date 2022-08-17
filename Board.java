import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Board {

    private int[][] initiles, bd1, bd2, bd3, bd4;
    private static int arrayLength;
    private static int iterLength;
    
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) 
    {
        initiles = tiles;
        bd1 = null; bd2 = null; bd3 = null; bd4 = null;
        arrayLength = tiles.length;
        iterLength = 2;
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
        /*
         * for (int i = 0; i < arrayLength; i++) 
            for(int j = 0; j < arrayLength; j++)
            if (initiles[i][j] != 0 && initiles[i][j] !=  (i * arrayLength + j + 1)) 
                sum += Math.abs((blocks1D[i] - 1) / n - i / n) + // distance of rows
                             Math.abs((blocks1D[i] - 1) % n - i % n);  // distance of columns
        */
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() 
    {                
        /* for(int i = 0; i < arrayLength ; i++) 
            for(int j = 0; j < arrayLength; j++)
                if(!(initiles[i][j] == (i * arrayLength + j + 1) || initiles[i][j] == 0)) {
                   // StdOut.println(i + " " + j + " --> " + (i * arrayLength + j + 1) + " == " + initiles[i][j]);
                    return false;
                }
        return true; // the last one expected to be 0; */
        return this.hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) 
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board b = (Board) y;
        if (this.dimension() != b.dimension()) return false;
        for (int i=0; i < this.initiles.length; i++)
            for (int j=0; j < this.initiles.length; j++)
                if (this.initiles[i][j] != b.initiles[i][j]) return false;        
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        int i = 0, j = 0;
        for(int k = 0; k < arrayLength; k++)
            for(int l = 0; l < arrayLength; l++)
                if(initiles[k][l] == 0)         // where is the empty thing ??
                {
                    i = (k == arrayLength - 1) ? -1: k; 
                    j = (l == arrayLength - 1) ? -1: l;
                    break;
                    }
        
        bd1 = new int[arrayLength][arrayLength];
        bd2 = new int[arrayLength][arrayLength];    // new empty neighbors
        bd3 = new int[arrayLength][arrayLength];
        bd4 = new int[arrayLength][arrayLength];
        
        for (int m = 0; m < arrayLength; m++)
            for (int n = 0; n < arrayLength; n++)
            {
                bd1[m][n] = initiles[m][n];       // copying original board to the neighbors                         
                bd2[m][n] = initiles[m][n];
                bd3[m][n] = initiles[m][n];
                bd4[m][n] = initiles[m][n];
            }
        
        switch (i) {
        case 0:
            switch (j) {
            // Nested case
            case 0:
                StdOut.println(i + " - " + j);
                
                bd1[0][0] = bd1[0][1]; bd1[0][1] = 0;  // upper left corner
                bd2[0][0] = bd2[1][0]; bd2[1][0] = 0;
                                                          
                break;
                
            case (-1):
                StdOut.println(i + " - " + j);  
                
                bd1[0][arrayLength - 1] = bd1[0][arrayLength - 2]; bd1[0][arrayLength - 2] = 0;  // upper right corner
                bd2[0][arrayLength - 1] = bd2[1][arrayLength - 1]; bd2[1][arrayLength - 1] = 0;
                
                break;
            
            default: // j > 0 && j < length-1 
                StdOut.println(i + " - " + j); 
                
                bd1[i][j] = bd1[i][j - 1]; bd1[i][j - 1] = 0;
                bd2[i][j] = bd2[i][j + 1]; bd2[i][j + 1] = 0; // upper edge
                bd3[i][j] = bd3[i + 1][j]; bd3[i + 1][j] = 0;
                iterLength = 3;
            }

            break; // case i == 0
            
        case (-1):
            switch (j) {
            // Nested case
            case 0:
                StdOut.println(i + " - " + j);
                
                bd1[arrayLength - 1][0] = bd1[arrayLength - 2][0]; bd1[arrayLength - 2][0] = 0;
                bd2[arrayLength - 1][0] = bd2[arrayLength - 1][1]; bd2[arrayLength - 1][1] = 0;  // bottom left corner
                
                break;
            
            case (-1):
                StdOut.println(i + " - " + j); 
                
                bd1[arrayLength - 1][arrayLength - 1] = bd1[arrayLength - 2][arrayLength - 1]; bd1[arrayLength - 2][arrayLength - 1] = 0;
                bd2[arrayLength - 1][arrayLength - 1] = bd2[arrayLength - 1][arrayLength - 2]; bd2[arrayLength - 1][arrayLength - 2] = 0;
                // bottom right corner
                break;
  
            default: // j > 0 && j < length-1 
                StdOut.println(i + " - " + j); 
                bd1[arrayLength - 1][j] = bd1[arrayLength - 1][j - 1]; bd1[arrayLength - 1][j - 1] = 0;
                bd2[arrayLength - 1][j] = bd2[arrayLength - 1][j + 1]; bd2[arrayLength - 1][j + 1] = 0; // bottom edge
                bd3[arrayLength - 1][j] = bd3[arrayLength - 2][j]; bd3[arrayLength - 2][j] = 0;
                iterLength = 3;
            }
            break; // i == -1

        default: // i != 0 && i != length-1 

            switch (j) {
            // Nested case
            case 0:
                StdOut.println(i + " - " + j); 
                
                bd1[i][0] = bd1[i - 1][0]; bd1[i - 1][0] = 0;
                bd2[i][0] = bd2[i + 1][0]; bd2[i + 1][0] = 0; // left edge
                bd3[i][0] = bd3[i][1]; bd3[i][1] = 0;
                iterLength = 3;
                break;
            
            case (-1):
                StdOut.println(i + " - " + j); 
            
                bd1[i][arrayLength - 1] = bd1[i - 1][arrayLength - 1]; bd1[i - 1][arrayLength - 1] = 0;
                bd2[i][arrayLength - 1] = bd2[i + 1][arrayLength - 1]; bd2[i + 1][arrayLength - 1] = 0; // right edge
                bd3[i][arrayLength - 1] = bd3[i][arrayLength - 2]; bd3[i][arrayLength - 2] = 0;
                iterLength = 3;
                break;
  
            default: // j > 0 && j < length-1 
                StdOut.println(i + " - " + j); 
                
                bd1[i][j] = bd1[i][j - 1]; bd1[i][j - 1] = 0;
                bd2[i][j] = bd2[i][j + 1]; bd2[i][j + 1] = 0; // in the middle
                bd3[i][j] = bd3[i + 1][j]; bd3[i + 1][j] = 0;
                bd4[i][j] = bd4[i - 1][j]; bd4[i - 1][j] = 0;
                iterLength = 4;
            }            
        }
        return new Iterable<Board>()
        {
            @Override
            public Iterator<Board> iterator()
            {
                
                
                return new Iterator<Board>()
                {
                    private int position;
                    private Board[] items = {new Board(bd1), new Board(bd2), new Board(bd3), new Board(bd4)}; /* = new Board[4]; */ 
                    // private int arrlength;
                    
                    @Override
                    public boolean hasNext()
                    {
                       // arrlength = !Arrays.equals(bd4, initiles) ? 4 : !Arrays.equals(bd3, initiles) ? 3 : 2;
                        
                        return position != items.length;
                    }

                    @Override
                    public Board next()
                    {                        
                        if (!hasNext()) throw new NoSuchElementException();                       
                        return items[position++];
                    }
                    
                    public void remove() 
                    {
                        
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
        return brd; 
    }

    // unit testing (not graded)
    public static void main(String[] args) 
    {
        int[][] board = {{5, 8, 0}, 
                         {6, 7, 1}, 
                         {2, 4, 3}};
        Board bd = new Board(board);
        StdOut.println(bd.dimension());
        StdOut.println("Game is over? " + bd.isGoal());
        // StdOut.println(bd.toString());
        
        
        int bdnew[][] = new int[board.length][board.length]; 
        for (int i=0; i<board.length; i++)
            for (int j=0; j<board.length; j++)
                bdnew[i][j] = board[i][j]; 
        Board bd2 = new Board(bdnew);
        
        Iterator<Board> itr = bd.neighbors().iterator();
        while (itr.hasNext()) {
            
            if (itr.next().equals(bd) || itr.next().equals(itr.next()))
            {
              //  StdOut.print(itr.next().toString());    
              // itr.next();
               itr.remove();
               
               /* itr.next();
               itr.remove(); */
             }
        } // nope, but it perfectly removes all without "if" condition

        
       for (Board boardx : bd.neighbors())
            if (!boardx.equals(bd)) 
            {
            StdOut.print(boardx);
            StdOut.println("manhattan " + boardx.manhattan() + ", hamming " + boardx.hamming());
            StdOut.println(" ----- ");
             }
       
        
        
       StdOut.println("Changed:" + bd.twin());
        StdOut.println("Equals: " + bd.equals(bd2));     
    }
}