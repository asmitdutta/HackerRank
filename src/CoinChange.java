import java.util.Scanner;

/**
 * Created by asmitd on 31/12/16.
 */
public class CoinChange {

    public long getNumberOfWays(int[] C,int N,int M){
        long[][] T = new long[C.length][N+1];

        for(int i=0;i<C.length;i++){
            for(int j=0;j<=N;j++){
                if(j==0){
                    T[i][j] = 1;
                }else if(j>=C[i]){
                    if(i==0){
                        T[i][j] = T[i][j-C[i]];
                    }else{
                        T[i][j] = T[i-1][j] + T[i][j-C[i]];
                    }
                }else{
                    if(i==0){
                        T[i][j] = 0;
                    }else{
                        T[i][j] = T[i-1][j];
                    }
                }
            }
        }

        return T[C.length-1][N];
    }

    public static void main(String[] args){
        CoinChange coinChange = new CoinChange();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int N = Integer.parseInt(line.split(" ")[0]);
        int M = Integer.parseInt(line.split(" ")[1]);
        String[] arr = scanner.nextLine().split(" ");
        int[] C = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            C[i] = Integer.parseInt(arr[i]);
        }
        System.out.println(coinChange.getNumberOfWays(C,N,M));
    }

}
