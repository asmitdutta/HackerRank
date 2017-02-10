import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {

    private BigInteger findNthTerm(Integer t1,Integer t2,int n){
        BigInteger[] table = new BigInteger[n];
        table[0] = new BigInteger(t1.toString());
        table[1] = new BigInteger(t2.toString());
        for(int i=2;i<n;i++){
            table[i] = table[i-1].pow(2).add(table[i-2]);
        }
        return table[n-1];
    }

    public static void main(String[] args){
        FibonacciModified fibonacciModified = new FibonacciModified();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int t1 = Integer.parseInt(line.split(" ")[0].trim());
        int t2 = Integer.parseInt(line.split(" ")[1].trim());
        int n = Integer.parseInt(line.split(" ")[2].trim());
        System.out.println(fibonacciModified.findNthTerm(t1,t2,n));
    }
}
