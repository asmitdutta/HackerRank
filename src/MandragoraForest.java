import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by asmitd on 05/11/16.
 */
public class MandragoraForest {

    private long getSum(long[] arr){
        long sum = 0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
        }
        return sum;
    }

    private long findMaxP(long[] arr){
        long pMax = 0, p;
        long sum = getSum(arr);
        long eatenSum = 0;
        for(int k=0;k<=arr.length;k++){
            if(k==0){
                p = sum;
            } else if(k == arr.length){
                p = 0;
            } else{
                eatenSum += arr[k-1];
                p = (k+1)*(sum - eatenSum);
            }
            if(p > pMax){
                pMax = p;
            }else{
                break;
            }
        }
        return pMax;
    }

    public static void main(String[] args){
        MandragoraForest mandragoraForest = new MandragoraForest();
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine().trim());
        for(int t=0;t<T;t++){
            int N = Integer.parseInt(scanner.nextLine().trim());
            long[] arr = new long[N];
            String[] hArr = scanner.nextLine().trim().split(" ");
            for(int n=0;n<N;n++){
                arr[n] = Integer.parseInt(hArr[n]);
            }
            Arrays.sort(arr);
            System.out.println(mandragoraForest.findMaxP(arr));
        }
    }
}
