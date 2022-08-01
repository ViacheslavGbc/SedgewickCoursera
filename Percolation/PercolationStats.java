import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
private static final double CONFIDENCE_95 = 1.96;
private double[] a;
private int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) 
            throw new IllegalArgumentException("Grid size or trials is out of range in the constructor "+ Integer.toString(n)+ Integer.toString(trials));
         else
         { 
             
            Percolation grid;
            a = new double[trials];
            t = trials;
                           
            for (int j = 0; j < trials; j++) 
            {
                int max = 0, i, row, col;
                         
                 i = 0;
                 
                 row = StdRandom.uniform(n + 1); 
                 col = StdRandom.uniform(n + 1);
                  
                 if (row == 0) row++;               
                 if (col == 0) col++;
                  
                 grid = new Percolation(n);
                 
                 while (!(grid.percolates()) && i < n*n) 
                 {
                     while (grid.isOpen(row, col) && max < n*n)
                     {
                         row = StdRandom.uniform(n + 1);
                         col = StdRandom.uniform(n + 1);
                         if (row == 0) row++;               
                         if (col == 0) col++;
                         max++;
                     }
                     grid.open(row, col);
                     i++; 
                 }
                 a[j] = 0.00; 
                 a[j] = (double) grid.numberOfOpenSites()/(n*n);
                 // StdOut.printf(a[j]/100+ " ");
            } 
         }
        
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(a);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(a); 
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95*stddev()/ Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95*stddev()/ Math.sqrt(t)); // return!!!
    }

   // test client (see below)
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int t = Integer.parseInt(args[1]);
       
      PercolationStats ps = new PercolationStats(n, t);
      StdOut.println("mean                    = " + ps.mean());
      StdOut.println("stddev                  = " + ps.stddev());
      StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", "+ ps.confidenceHi() +"]");
   }
}
