import java.util.Scanner;
public class RodCuttingProblem {
    public static int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    public static int rodCutting(int price[], int n) { 
        int val[] = new int[n + 1]; 
        val[0] = 0; 
        int i, j; 
  
        // Build the table val[] in bottom up manner and return 
        // the last entry from the table 
        for (i = 1; i <= n; i++) { 
            int max_val = Integer.MIN_VALUE; 
            for (j = 0; j < i; j++) 
                max_val = max(max_val, price[j] + val[i - j - 1]); 
            val[i] = max_val; 
        } 
  
        return val[n]; 
    } 
  
    public static void main(String args[]) { 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of the rod: "); 
        int n = scanner.nextInt();
        int price[] = new int[n];
        System.out.println("Enter the prices for each length: ");
        for(int i=0;i<n;i++) {
            price[i] = scanner.nextInt();
        }
        System.out.println("Maximum Profit: " + rodCutting(price, n)); 
    } 
}