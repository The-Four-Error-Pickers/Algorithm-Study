import java.util.*;
class Solution {
    static int[] arr;
    public int[] solution(int target) {
        int[] answer = new int[2];
        
        return Dijk(target);
        
    }
    
    private static int[] Dijk(int t) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1[1] == o2[1]) {
                return o2[2] - o1[2];
            }
            return o1[1] - o2[1];
        });
        pq.offer(new int[]{t,0,0});
        int[] v = new int[100055];
        Arrays.fill(v, Integer.MAX_VALUE);
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(cur[0] == 0) {
                return new int[]{cur[1], cur[2]};
            }
            if(v[cur[0]] < cur[1]) continue;
            
            for(int i=1; i<=20; i++) {
                if(cur[0] - i >= 0) {
                    int next = cur[0] - i;
                    if(v[next] < cur[1]+1) continue;
                    pq.offer(new int[]{cur[0]-i, cur[1]+1, cur[2] + 1});
                    v[next] = cur[1]+1;
                }
                if(cur[0] - (i*2) >= 0) {
                    int next = cur[0] - (i*2);
                    if(v[next] < cur[1]+1) continue;
                    pq.offer(new int[]{cur[0]-(i*2), cur[1]+1 , cur[2]});
                    v[next] = cur[1]+1;
                }
                if(cur[0] - (i*3) >= 0) {
                    int next = cur[0] - (i*3);
                    if(v[next] < cur[1]+1) continue;
                    pq.offer(new int[]{cur[0]-(i*3), cur[1]+1, cur[2]});
                    v[next] = cur[1]+1;
                }
            }
            
            if(cur[0] - 50 >= 0) {
                int next = cur[0] - 50;
                if(v[next] < cur[1]+1) continue;
                pq.offer(new int[]{cur[0]-50, cur[1]+1, cur[2] + 1});
                v[next] = cur[1]+1;
                
            }
        }
        
        return new int[]{-1,-1};
    }
}