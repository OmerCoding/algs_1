import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
    int open = 0;
    int size;
    int grid[][];
    WeightedQuickUnionUF x;
   
    public Percolation(int n){
        int WQUF_size = n * n + 2;
        this.grid = new int[n][n];
        this.size = n;
        this.x = new WeightedQuickUnionUF(WQUF_size);
    }
    
    public void open(int row, int col){
        int num = xyTo1D(row,col);
        if((row < 1 || col < 1) || (row > size || col > size)){
            throw new IllegalArgumentException();
        }
        if(grid[row-1][col-1] == 0){
            if(num % size == 0){
                if(num - size <= 0){
                    x.union(0,xyTo1D(row,col));
                    if(isOpen(row+1,col)){
                        x.union(xyTo1D(row+1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row,col-1)){
                        x.union(xyTo1D(row,col-1),xyTo1D(row,col));
                    }
                } else if(num + size > (size * size)){
                    x.union((size * size + 1),xyTo1D(row,col));
                    if(isOpen(row-1,col)){
                        x.union(xyTo1D(row-1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row,col-1)){
                        x.union(xyTo1D(row,col-1),xyTo1D(row,col));
                    }
                } else {
                    if(isOpen(row,col-1)){
                        x.union(xyTo1D(row,col-1),xyTo1D(row,col));
                    }
                    if(isOpen(row+1,col)){
                        x.union(xyTo1D(row+1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row-1,col)){
                        x.union(xyTo1D(row-1,col),xyTo1D(row,col));
                    }
                }
            }
            else if(num % size == 1){
                if(num - size <= 0){
                    x.union(0,xyTo1D(row,col));
                    if(isOpen(row+1,col)){
                        x.union(xyTo1D(row+1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row,col+1)){
                        x.union(xyTo1D(row,col+1),xyTo1D(row,col));
                    }
                } else if(num + size > (size * size)){
                    x.union((size * size + 1),xyTo1D(row,col));
                    if(isOpen(row-1,col)){
                        x.union(xyTo1D(row-1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row,col+1)){
                        x.union(xyTo1D(row,col+1),xyTo1D(row,col));
                    }
                } else {
                    if(isOpen(row,col+1)){
                        x.union(xyTo1D(row,col+1),xyTo1D(row,col));
                    }
                    if(isOpen(row+1,col)){
                        x.union(xyTo1D(row+1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row-1,col)){
                        x.union(xyTo1D(row-1,col),xyTo1D(row,col));
                    }
                }
            } else {
                if(num - size <= 0){
                    x.union(0,xyTo1D(row,col));
                    if(isOpen(row,col-1)){
                        x.union(xyTo1D(row,col-1),xyTo1D(row,col));
                    }
                    if(isOpen(row,col+1)){
                        x.union(xyTo1D(row,col+1),xyTo1D(row,col));
                    }
                    if(isOpen(row+1,col)){
                        x.union(xyTo1D(row+1,col),xyTo1D(row,col));
                    }
                } else if(num + size > (size * size)){
                    x.union((size * size + 1),xyTo1D(row,col));
                    if(isOpen(row,col-1)){
                        x.union(xyTo1D(row,col-1),xyTo1D(row,col));
                    }
                    if(isOpen(row,col+1)){
                        x.union(xyTo1D(row,col+1),xyTo1D(row,col));
                    }
                    if(isOpen(row-1,col)){
                        x.union(xyTo1D(row-1,col),xyTo1D(row,col));
                    }
                } else {
                    if(isOpen(row,col-1)){
                        x.union(xyTo1D(row,col-1),xyTo1D(row,col));
                    }
                    if(isOpen(row,col+1)){
                        x.union(xyTo1D(row,col+1),xyTo1D(row,col));
                    }
                    if(isOpen(row-1,col)){
                        x.union(xyTo1D(row-1,col),xyTo1D(row,col));
                    }
                    if(isOpen(row+1,col)){
                        x.union(xyTo1D(row+1,col),xyTo1D(row,col));
                    }
                }
            }
            grid[row-1][col-1] = 1;
            open++;
        }
    }
    
    public boolean isOpen(int row, int col){
        if((row < 1 || col < 1) || (row > size || col > size)){
            throw new IllegalArgumentException();
        }
        if(grid[row-1][col-1]==1){
            return true;
        } else {
            return false;
        }
    }
        
    public boolean isFull(int row, int col){
        if((row < 1 || col < 1) || (row > size || col > size)){
            throw new IllegalArgumentException();
        }
        if(grid[row-1][col-1]==0){
            return true;
        } else {
            return false;
        }
    }
    
    public int numberOfOpenSites(){
        return open;
    }
    
    public boolean percolates(){
        if(x.connected(0, size * size + 1)){
            return true;
        } else {
            return false;
        }
    }
    
    private int xyTo1D(int x, int y){
        return size * (x - 1) + y;
    }
   
    public static void main(String[] args){
        Percolation test_perc = new Percolation(3);
        //System.out.println(test_perc.xyTo1D(2,1));
        //System.out.println(test_perc.grid[1][1]);
        //System.out.println(test_perc.isOpen(1,1));
        //test_perc.open(1,1);
        test_perc.open(1,1);
        System.out.println(test_perc.percolates());
        test_perc.open(2,1);
        test_perc.open(3,1);
        System.out.println(test_perc.percolates());
        //test_perc.open(1,2);
        //System.out.println(test_perc.grid[1][1]);
        //System.out.println(test_perc.isOpen(1,1));
        //System.out.println(test_perc.x.find(2));
   }
}

