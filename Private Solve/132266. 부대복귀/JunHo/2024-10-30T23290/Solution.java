import java.util.*;

class Solution {
    
    class Node {
        int vertex;
        Node next;  // 연결리스트 유지를 위한 노드
        
        Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        } 
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        // BFS + PQ + 방문체크
        int[] answer = new int[sources.length];
        int[] distance = new int[n + 1];    
        boolean[] nVisited = new boolean[n + 1];  
        Arrays.fill(distance, -1);
        Node[] adjList = new Node[n + 1];   // 연결리스트 배열
        
        for(int i = 0; i < roads.length; i++) {
            int start = roads[i][0];
            int end = roads[i][1];
            
            adjList[start] = new Node(end, adjList[start]);
            adjList[end] = new Node(start, adjList[end]);
        }
        
        bfs(n, distance, nVisited, adjList, destination);
            
        for(int i = 0; i < sources.length; i++) {
            answer[i] = distance[sources[i]];
        }
        
        return answer;
    }
    
    // destination을 start로 해서 모든 road 탐색
    public void bfs(int n, int[] distance, boolean[] nVisited, Node[] adjList, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[]{start, 0});
        int count = 0;
        
        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            
            if(nVisited[current[0]]) continue;
            nVisited[current[0]] = true;
            distance[current[0]] = current[1];
            
            if(++count == n) break;
            
            for(Node temp = adjList[current[0]]; temp != null; temp = temp.next) {
                pq.offer(new int[] {temp.vertex, current[1] + 1});
            }
            
        }
    }
}