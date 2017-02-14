import java.util.Scanner;

/**
 * Created by asmitd on 13/02/17.
 */
public class JesseCookies {

    private void minHeapify(int[] a,int i,int length){
        int minIndex=i;
        if(2*i+1 < length && a[i]>a[2*i+1]){
            minIndex = 2*i+1;
        }
        if(2*i+2 < length && a[minIndex]>a[2*i+2]){
            minIndex = 2*i+2;
        }
        if(minIndex!=i){
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
            minHeapify(a,minIndex,length);
        }
    }

    private void buildMinHeap(int[] a,int length){
        for(int i=length/2-1;i>=0;i--){
            minHeapify(a,i,length);
        }
    }

    private int maximizeSweetness(int[] a,int k,int i,int length){
        int lsc = a[0];
        while(lsc<k && length!=1){
            int slscIndex = a[1]<a[2]?1:2;
            a[0] = 1*lsc+2*a[slscIndex];
            a[slscIndex] = Integer.MAX_VALUE;
            int temp = a[length-1];
            a[length-1] = a[slscIndex];
            a[slscIndex] = temp;
            buildMinHeap(a,length-1);
            i++;
            length--;
            lsc = a[0];
        }
        if(lsc<k && length==1){
            return -1;
        }
        return i;
        /*int lsc = a[0];
        if(lsc<k && length!=1){
            int slscIndex = a[1]<a[2]?1:2;
            a[0] = 1*lsc+2*a[slscIndex];
            a[slscIndex] = Integer.MAX_VALUE;
            int temp = a[length-1];
            a[length-1] = a[slscIndex];
            a[slscIndex] = temp;
            buildMinHeap(a,length-1);
            i = maximizeSweetness(a,k,i+1,length-1);
        }else if(length==1){
            return -1;
        }
        return i;*/
    }

    public static void main(String[] args){
        JesseCookies j = new JesseCookies();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int N = Integer.parseInt(line.split(" ")[0]);
        int K = Integer.parseInt(line.split(" ")[1]);
        line = scanner.nextLine();
        String[] arr = line.split(" ");
        int[] a = new int[N];
        for(int i=0;i<arr.length;i++){
            a[i] = Integer.parseInt(arr[i]);
        }
        j.buildMinHeap(a,a.length);
        System.out.println(j.maximizeSweetness(a,K,0,a.length));
    }
}
