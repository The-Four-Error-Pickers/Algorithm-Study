import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int len = sources.length;
        int[] answer = new int[len];
        
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : roads) {
            graph[edge[1]].add(edge[0]);
            graph[edge[0]].add(edge[1]);
        }
            
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dist = new int[n+1];
        boolean[] vis = new boolean[n+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        pq.offer(new int[] {destination, 0});
        
        int[] cur;
        int cnt = 0;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            
            if(vis[cur[0]])
                continue;
            vis[cur[0]] = true;
            if(++cnt == n)
                break;
            
            for(int nxt : graph[cur[0]]) {
                if(dist[nxt] <= cur[1] + 1)
                    continue;
                dist[nxt] = cur[1] + 1;
                pq.offer(new int[] {nxt, dist[nxt]});
            }
        }        
        
        for(int i = 0; i < len; i++) {
            if(dist[sources[i]] == Integer.MAX_VALUE)
                answer[i] = -1;
            else
                answer[i] = dist[sources[i]];
        }
        return answer;
    }
}