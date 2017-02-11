import com.sun.org.apache.regexp.internal.RE;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
   private double[] ratio;
   public PercolationStats(int n, int trials) {
      ratio = new double[trials];
      for (int i = 0; i < trials; i++) {
         Percolation percolationTest = new Percolation(n);
         while (!percolationTest.percolates()) {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;
            if (!percolationTest.isOpen(row, col)) {
               percolationTest.open(row, col);
            }
         }
//         System.out.println("Number of open sites: " + percolationTest.numberOfOpenSites());
         ratio[i] =  ( (double) percolationTest.numberOfOpenSites() )  /  ( (double) (n*n) ) ;
//         System.out.println("Ratio is: " + ratio[i]);
      }
//      for (int i = 0; i < ratio.length; i++) {
//         System.out.println("ratio at " + i + " is: " + ratio[i]);
//      }
      //calculate mean
      //Std Dev
      //Confidence Interval
   }    // perform trials independent experiments on an n-by-n grid

   public double mean() {
      return StdStats.mean(ratio);
   }                          // sample mean of percolation threshold

   public double stddev() {
      return StdStats.stddev(ratio);
   }                        // sample standard deviation of percolation threshold

   public double confidenceLo() {
      return mean() - ( ( 1.96 * Math.sqrt(stddev()) ) / Math.sqrt(ratio.length) );
   }                  // low  endpoint of 95% confidence interval

   public double confidenceHi() {
      return mean() + ( ( 1.96 * Math.sqrt(stddev()) ) / Math.sqrt(ratio.length) );
   }                  // high endpoint of 95% confidence interval

   public static void main(String[] args) {
      Stopwatch stopwatch = new Stopwatch();
      PercolationStats percStat = new PercolationStats(160, 100);
      System.out.println("Time is: " + stopwatch.elapsedTime());
      System.out.println("Mean is: " + percStat.mean());
      System.out.println("Standard Deviation is: " + percStat.stddev());
      System.out.println("Confidence Low is: " + percStat.confidenceLo());
      System.out.println("Confidence High is: " + percStat.confidenceHi());
   }        // test client (described below)

}
