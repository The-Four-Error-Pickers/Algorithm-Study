import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        int[] degree = new int[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : lighthouse) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        int max, select;
        while(true) {
            
            max = 0;
            select = 0;
            
            for(int i = 1; i < n + 1; i++) {
                if(max < degree[i]) {
                    max = degree[i];
                    select = i;
                }
            }
            
            if(select == 0)
                break;
            
            degree[select] = 0;
            
            for(int conn : graph[select])
                degree[conn]--;
            answer++;
        }
        
        return answer;
    }
}