import java.util.Scanner;

/**
 * Created by asmitd on 12/09/16.
 */
public class NikitaGame {

    private int findSum(int[] arr,int low,int high){
        int sum=0;
        while(low<=high){
            sum += arr[low];
            low++;
        }
        return sum;
    }

    private int findPoint(int[] arr,int low,int high,int start,int end){
        if(start == end) return -1;
        int mid = (start + end)/2;
        int leftSum = findSum(arr,low,mid);
        int rightSum = findSum(arr,mid+1,high);
        if(leftSum == rightSum){
            return mid;
        }else if(leftSum>rightSum){
            return findPoint(arr,low,high,start,mid);
        }else {
            return findPoint(arr,low,high,mid+1,end);
        }
    }

    private int getMaxScore(int[] arr,int low,int high){
        if(low >= high) return 0;
        else{
            int k = findPoint(arr,low,high,low,high);
            if(k==-1) return 0;
            return Math.max(getMaxScore(arr,low,k),getMaxScore(arr,k+1,high)) + 1;
        }
    }

    public static void main(String[] args){
        NikitaGame nikitaGame = new NikitaGame();
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine().trim());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(scanner.nextLine().trim());
            int[] arr = new int[N];
            String[] A = scanner.nextLine().split(" ");
            for(int j=0;j<N;j++){
                arr[j] = Integer.parseInt(A[j].trim());
            }
            boolean isAllZeroes = true;
            for(int k=0;k<arr.length;k++){
                if(arr[k]!=0){
                    isAllZeroes = false;
                    break;
                }
            }
            if(isAllZeroes){
                System.out.println(arr.length-1);
            }else{
                System.out.println(nikitaGame.getMaxScore(arr,0,arr.length-1));
            }
        }
    }
}
