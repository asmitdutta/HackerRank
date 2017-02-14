import java.util.Scanner;

/**
 * Created by asmit on 4/2/17.
 */
class Node{
    int data;
    Node next;
    Node(int data, Node next){
        this.data = data;
        this.next = next;
    }
    public String toString(){
        return "Node[data:"+data+",next:"+next+"]";
    }
}

public class BatchReverseLinkedList {

    public Node reverse(Node i, Node j, Node k){
        if(j == k){
            j.next=i;
        }else{
            reverse(i.next,j.next,k).next = i;
        }
        return i;
    }

    public void batchReverse(Node head){
        Node i = head;
        Node j = head;
        Node t = head;
        Node n = head;
        int k=1,c=1;
        while(j!=null){
            j = j.next;
            k++;
            if(k==3 && j!=null){
                t=j.next;
                n=t;
                c=1;
                while(c<3 && n!=null){
                    n=n.next;
                    c++;
                }
                if(n==null){
                    n=t;
                }
                reverse(i,i.next,j).next = n;
                i=j=t;
                k=1;
            }
        }
    }

    public static void main(String[] args){
        BatchReverseLinkedList b = new BatchReverseLinkedList();
        //Node tail = new Node(5, new Node(6, new Node(7, new Node(8,null))));new Node(7, new Node(8,null))
        Node start = new Node(3, new Node(4, new Node(5, new Node(6, new Node(7, new Node(8,null))))));
        Node head = new Node(1,new Node(2,start));
        b.batchReverse(head);
        System.out.println(start);
    }

}
