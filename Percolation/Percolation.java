// import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] id;
    private int numberopened;
    private int maxlength;
    private WeightedQuickUnionUF uf;
     // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n is out of range in the constructor "+ Integer.toString(n));
         else
         { 
             numberopened = 0;
             maxlength = n-1; // to check range within the class
             uf = new WeightedQuickUnionUF(n*n+2); // incl. top & bottom virtual points
             id = new boolean [n][n]; 
             for (int i = 0; i < n; i++)
                 for (int j = 0; j < n; j++)
                     id[i][j] = false;
         }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > (maxlength+1) || col > (maxlength+1))
            throw new IllegalArgumentException("Row or col is out of range in open() "+ Integer.toString(row) + Integer.toString(col));
         else
         { 
             if (!isOpen(row, col)) 
             {
                 row = indexer(row);
                 col = indexer(col);
                 
                 id[row][col] = true; 
                 numberopened++; 
                 int n = maxlength + 1;  // max value
                 // StdOut.println("--> r: "+row+" c: "+col+" n: "+ n);
                 if (row == 0) 
                     uf.union(col+1, 0); // connecting 1st row to the top
                 
                 if (row == maxlength) 
                     uf.union(row*n+col+1, n*n+1); // connecting the last row to the bottom
                 
                 if (row > 0 && isOpen(antiindexer(row-1), antiindexer(col)))  
                     uf.union(row*n+col+1, (row-1)*n+col+1); // connect to the open top
                
                 if (row < n-1 && isOpen(antiindexer(row+1), antiindexer(col))) 
                     uf.union(row*n+col+1, (row+1)*n+col+1); // connect to the open bottom
                 
                 if (col > 0 && isOpen(antiindexer(row), antiindexer(col-1))) 
                     uf.union(row*n+col+1, row*n+col+1-1); // connect to the open left 
                
                 if (col < n-1 && isOpen(antiindexer(row), antiindexer(col+1))) 
                     uf.union(row*n+col+1, row*n+col+1+1); // connect to the open right
                 
                 // connecting to the adjacent open sites. Formula (row*n+col+1) transfers matrix to array. 
             }
         }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > (maxlength+1) || col > (maxlength+1))
            throw new IllegalArgumentException("Row or col is out of range in isOpen(), row: " + Integer.toString(row)+ " col: " + Integer.toString(col)); 
         else
                       return id[indexer(row)][indexer(col)]; 
    }

    // is the site (row, col) full? // connected to the path????
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > (maxlength+1) || col > (maxlength+1))
            throw new IllegalArgumentException("Row or col is out of range in isFull() " + Integer.toString(row) + Integer.toString(col)); 
         else
         {
             row = indexer(row);
             col = indexer(col);
             
             return (uf.find(0) == uf.find(row * (maxlength + 1) + col + 1)); 
                     // || uf.find(row * (maxlength + 1) + col + 1) == uf.find((maxlength + 1) * (maxlength + 1) +1));
             } // return !!!!
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberopened; // return!!!
    }

    // does the system percolate?
    public boolean percolates() {
        int n = maxlength + 1;
        return uf.find(0) == uf.find(n*n+1); // virt.top connected to virt.bottom
    }
    
    private int indexer(int intindex) {
             intindex--;        
        return intindex;
    }
    
    private int antiindexer(int outindex) {
        outindex++;        
   return outindex;
   }

    // test client (optional)
    public static void main(String[] args) {
     
       /* int n = 200;
        Percolation grid = new Percolation(n);
        int max = 0;
        
        int row = StdRandom.uniform(n+1);
        int col = StdRandom.uniform(n+1);
        if (row == 0) row++;               
        if (col == 0) col++;
        
        int i = 0;
        while (!(grid.percolates()) && i < n*n) 
        {
            while (grid.isOpen(row, col) && max < n*n)
            {
                row = StdRandom.uniform(n+1);
                col = StdRandom.uniform(n+1);
                if (row == 0) row++;               
                if (col == 0) col++;
                max++;
            }
            grid.open(row, col); 
            // StdOut.println(row+" "+col);
            i++;
        } */
        /* grid.open(3, 4); 
        grid.open(0, 2);
        grid.open(0, 4);
        grid.open(0, 1);
        grid.open(1, 3);
        grid.open(2, 3);
        grid.open(0, 0);
        grid.open(3, 3);
        grid.open(4, 1);
        grid.open(4, 4);
        grid.open(1, 2); */ 
        /* for ( i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++)
                StdOut.print(grid.id[i][j]+" ");                
         StdOut.println();
         } */ 
        // StdOut.println("percolates: " + grid.percolates());
        // StdOut.println("number opened: " + grid.numberOfOpenSites());
        
        /* for ( i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                if (grid.uf.find(i*n+j+1) < 10)
                    StdOut.print(" ");
               StdOut.print(grid.uf.find(i*n+j+1)+" ");
            }
         StdOut.println();
         } */ 
        
     }

}
