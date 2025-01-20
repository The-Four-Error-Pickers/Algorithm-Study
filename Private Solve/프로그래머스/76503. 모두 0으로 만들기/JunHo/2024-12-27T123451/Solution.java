import java.util.*;

class Solution {
    
    class Node {
        int vertex;
        Node next;
        
        Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
    
    static long[] value;
    static int[] inDegree;
    static boolean[] visited;
    static Node[] adjList;
    
    public long solution(int[] a, int[][] edges) {
        value = new long[a.length];  // DFS를 진행할때 각 노드마다 연산을 진행한 횟수를 담는 배열
        visited = new boolean[a.length];    // DFS 진행시 방문체크를 위한 배열
        adjList = new Node[a.length];   // 트리 구성을 위한 연결리스트
        inDegree = new int[a.length];
        
        // 불가능한 경우 계산
        int tempSum = 0;
        for(int i = 0; i < a.length; i++) {
            value[i] = a[i];
            tempSum += a[i];
        }
        if(tempSum != 0) return -1;
        
        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
            inDegree[from]++;
            inDegree[to]++;
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < inDegree.length; i++) {
            if(inDegree[i] == 1) q.add(i);  // 리프노드 저장
        }
        
        long answer = 0;
        while(!q.isEmpty()) {
            int current = q.poll();
            visited[current] = true;
            
            for(Node temp = adjList[current]; temp != null; temp = temp.next) {
                int nextIdx = temp.vertex;  // 부모의 인덱스
                if(!visited[nextIdx]) { // 방문하지 않았다면 처리
                    inDegree[nextIdx]--;
                    value[nextIdx] += value[current];   // 부모로 가중치를 옮김
                    answer += Math.abs(value[current]);
                    value[current] = 0;
                    if(inDegree[nextIdx] == 1) q.offer(nextIdx);
                }
            }
        }        
        
        return answer;
    }
}