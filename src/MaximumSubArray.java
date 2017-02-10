import java.util.Scanner;

/**
 * Created by asmitd on 05/09/16.
 */
public class MaximumSubArray {

    int maxCSum(int a[])
    {
        int max_so_far = a[0];
        int curr_max = a[0];

        for (int i = 1; i < a.length; i++)
        {
            curr_max = Math.max(a[i], curr_max+a[i]);
            max_so_far = Math.max(max_so_far, curr_max);
        }
        return max_so_far;
    }

    private int maxNCSum(int[] arr){
        int i=0;
        int maxsum = 0;
        int maxele = Integer.MIN_VALUE;
        boolean isAnyNumberGreaterThanZero = false;
        while(i<arr.length){
            if(arr[i] < 0){
                maxele = Math.max(maxele,arr[i]);
            }else{
                isAnyNumberGreaterThanZero = true;
                maxsum = Math.max(maxsum + arr[i],maxsum);
                maxele = Math.max(maxele,arr[i]);
            }
            i++;
        }
        if(isAnyNumberGreaterThanZero){
            return maxsum;
        }else{
            return maxele;
        }
    }

    public static void main(String[] args){
        MaximumSubArray maximumSubArray = new MaximumSubArray();
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine().trim());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(scanner.nextLine().trim());
            int[] arr = new int[N];
            String[] inputArr = scanner.nextLine().trim().split(" ");
            for(int j=0;j<N;j++){
                arr[j] = Integer.parseInt(inputArr[j]);
            }
            int maxContiguousSum = maximumSubArray.maxCSum(arr);
            int maxNonContiguousSum = maximumSubArray.maxNCSum(arr);
            System.out.println(maxContiguousSum +" "+maxNonContiguousSum);
        }
    }
}
