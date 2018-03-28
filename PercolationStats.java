import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    
    double trial_arr[];
    int size;
    
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        
        this.trial_arr = new double[trials];
        this.size = n;
        
        for (int i = 0; i < trials; i++){
            Percolation grid = new Percolation(n);
            while (grid.percolates() == false){
                int x = StdRandom.uniform(1,size+1);
                int y = StdRandom.uniform(1,size+1);
                if (grid.isFull(x,y) == true){
                    grid.open(x,y);
                }
            }
            trial_arr[i] = ((double) grid.open / (size * size));
        }
    }
    
    public double mean(){
        return StdStats.mean(trial_arr);
    }
    
    public double stddev(){
        return StdStats.stddev(trial_arr);
    }
    
    public static void main(String[] args){
        PercolationStats tri = new PercolationStats(10,10);
   }
}