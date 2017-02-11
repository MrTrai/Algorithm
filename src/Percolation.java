import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //Blocked = 0
    //Open = 1
    private int[][] percolationArray;
    private boolean isPercolate = false;
    private WeightedQuickUnionUF unionUF;
    private int openedSite = 0;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument n <= 0");
        }
        percolationArray = new int[n][n];
        //Initialize to blocked sites
        unionUF = new WeightedQuickUnionUF(n * n);
    }                // create n-by-n grid, with all sites blocked

    //Argument row/col is base 1
    public void open(int row, int col) {
        //Convert to 0 base
        int baseZeroRow = row - 1;
        int baseZeroCol = col - 1;

        //set open site
        if (!isOpen(row, col)) {
            percolationArray[baseZeroRow][baseZeroCol] = 1;
            openedSite++;
        }

        //convert 2D index to 1D index array
        int currentSite = ( baseZeroRow * percolationArray.length ) + baseZeroCol;
        int surroundingSites;

        //Connect to surrounding neighbor after open site
        for (int i = -1; i < 2; i++) {
            if (baseZeroRow + i < 0 || baseZeroRow + i >= percolationArray.length) {
                //if out of bound
                continue;
            }
            if (isOpen(row + i, col)) {
                surroundingSites = ( ( baseZeroRow + i ) * percolationArray.length ) + baseZeroCol;
                unionUF.union(surroundingSites, currentSite);
            }
        }

        for (int i = -1; i < 2; i++) {
            if (baseZeroCol + i < 0 || baseZeroCol + i >= percolationArray.length) {
                //if out of bound
                continue;
            }
            if (isOpen(row, col + i)) {
                surroundingSites = ( ( baseZeroRow ) * percolationArray.length ) + ( baseZeroCol + i );
                unionUF.union(surroundingSites, currentSite);
            }
        }
    }    // open site (row, col) if it is not open already

    //Argument row/col is base 1
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (percolationArray[row][col] == 1) {
            return true;
        }
        return false;
    }  // is site (row, col) open?

    //Argument row/col is base 1
    public boolean isFull(int row, int col) {
        row--;
        col--;
        if (unionUF.find( (row * percolationArray.length) + col ) < percolationArray.length
                && isOpen(row + 1, col + 1)) {
            return true;
        }
        return false;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return openedSite;
    }       // number of open sites

    public boolean percolates() {
        return isPercolate;
    }              // does the system percolate?

    public static void main(String[] args) {

    }   // test client (optional)

}
