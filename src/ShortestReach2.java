import java.util.Scanner;

public class ShortestReach2 {

    private int[][] adjacencyMatrix;
    private int[] depth;
    private int[] parent;
    private boolean[] isCompleted;

    private int findMinDepthIndex(int u,int N){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int v=1;v<=N;v++){
            if(!isCompleted[v] && min > depth[v]){
                min = depth[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void dijkstra(int S,int N){
        for(int i=1;i<=N;i++){
            int u = findMinDepthIndex(i,N);
            if(u != -1) {
                isCompleted[u] = true;
                for(int v=1;v<=N;v++){
                    if(adjacencyMatrix[u][v] != 0 && depth[v] > depth[u] + adjacencyMatrix[u][v]){
                        depth[v] = depth[u] + adjacencyMatrix[u][v];
                        parent[v] = u;
                    }
                }
            }
        }
    }

    private void singleSourceShortestPath(int S,int N){
        depth = new int[N+1];
        parent = new int[N+1];
        isCompleted = new boolean[N+1];

        for(int i=1;i<=N;i++){
            depth[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            isCompleted[i] = false;
        }
        depth[S] = 0;
        parent[S] = -1;

        dijkstra(S,N);

        for(int i=1;i<=N;i++){
            if(i!=S){
                if(depth[i] == Integer.MAX_VALUE) System.out.print(-1 + " ");
                else System.out.print(depth[i] + " ");
            }
        }
    }

    public static void main(String[] args){
        ShortestReach2 shortestReach = new ShortestReach2();
        Scanner scanner = new Scanner(System.in);
        String line;
        int T = Integer.parseInt(scanner.nextLine());
        for(int i=0;i<T;i++){
            line = scanner.nextLine();
            int N = Integer.parseInt(line.split(" ")[0]);
            shortestReach.adjacencyMatrix = new int[N+1][N+1];
            int M = Integer.parseInt(line.split(" ")[1]);
            for(int j=0;j<M;j++){
                line = scanner.nextLine();
                int x = Integer.parseInt(line.split(" ")[0]);
                int y = Integer.parseInt(line.split(" ")[1]);
                int r = Integer.parseInt(line.split(" ")[2]);
                if(r < shortestReach.adjacencyMatrix[x][y] && shortestReach.adjacencyMatrix[x][y] !=0){
                    shortestReach.adjacencyMatrix[x][y] = r;
                    shortestReach.adjacencyMatrix[y][x] = r;
                }else if(shortestReach.adjacencyMatrix[x][y] == 0){
                    shortestReach.adjacencyMatrix[x][y] = r;
                    shortestReach.adjacencyMatrix[y][x] = r;
                }
            }
            int S = Integer.parseInt(scanner.nextLine());
            shortestReach.singleSourceShortestPath(S,N);
            System.out.println();
        }
    }
}

