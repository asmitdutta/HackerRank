import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by asmitd on 10/02/17.
 */

public class FibonacciNumbersTree {

    static class Node{
        BigInteger data;
        Node left;
        Node right;
        Node(BigInteger data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString() {
            return "Node[" + "data=" + data + ", left=" + left + ", right=" + right + "]";
        }
    }

    private int[] createFibSeq(int n){
        int[] fib = new int[n];
        fib[1]=1;
        fib[2]=1;
        for(int i=3;i<n;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib;
    }

    private Node lca(Node node,Node x,Node y,Node root){
        if(node==null){
            return null;
        }
        else if(node==x || node==y){
            return node;
        }
        Node xfound = lca(node.left,x,y,root);
        Node yfound = lca(node.right,x,y,root);
        if(xfound!=null && yfound!=null){
            return node;
        }
        else if(xfound!=null){
            return xfound;
        }
        else {
            return yfound;
        }
    }

    private Node sum(Node node,Node to){
        if(node==null){
            return null;
        }
        else if(node==to){
            return node;
        }
        Node l = sum(node.left,to);
        Node r = sum(node.right,to);
        if(l==null && r==null){
            return null;
        }
        if(l!=null){
            return new Node(node.data.add(l.data),null,null);
        }
        else {
            return new Node(node.data.add(r.data), null,null);
        }
    }

    private BigInteger findSum(Node[] arr, Node x, Node y){
        Node lca = lca(arr[0], x, y, arr[0]);
        return sum(lca, x).data.add(sum(lca, y).data).subtract(lca.data);
    }

    private void update(Node node,Node x,int k,boolean isinSubTree,int[] fib){
        if(node!=null){
            if(node==x || isinSubTree){
                node.data = node.data.add(BigInteger.valueOf(fib[k]));
                update(node.left,x,k+1,true,fib);
                update(node.right,x,k+1,true,fib);
            }else{
                update(node.left,x,k,false,fib);
                update(node.right,x,k,false,fib);
            }
        }
    }

    public static void main(String[] args){
        FibonacciNumbersTree f = new FibonacciNumbersTree();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int N = Integer.parseInt(line.split(" ")[0]);
        int M = Integer.parseInt(line.split(" ")[1]);
        Node[] arr = new Node[N];
        for(int i=0;i<N;i++){
            arr[i]=new Node(BigInteger.valueOf(0),null,null);
        }
        for(int j=0;j<N-1;j++){
            int node = scanner.nextInt();
            if(arr[node-1].left==null){
                arr[node-1].left = arr[j+1];
            }else if(arr[node-1].right==null){
                arr[node-1].right = arr[j+1];
            }
        }
        String query2 = scanner.nextLine();
        int[] fib = f.createFibSeq(10000000);
        for(int i=0;i<M;i++){
            String query = scanner.nextLine();
            if(query.split(" ")[0].equals("Q")){
                int X = Integer.parseInt(query.split(" ")[1]);
                int Y = Integer.parseInt(query.split(" ")[2]);
                System.out.println(f.findSum(arr,arr[X-1],arr[Y-1]).mod(BigInteger.valueOf((long)Math.pow(10,9)+7)));
            }else{
                int X = Integer.parseInt(query.split(" ")[1]);
                int k = Integer.parseInt(query.split(" ")[2]);
                f.update(arr[0], arr[X-1], k, false, fib);
            }
        }
    }
}
