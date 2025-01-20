import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        int min = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o2[1] - o1[1]);
        for(int i=0; i<scores.length; i++) {
            int[] cur = scores[i];
            pq.offer(new int[]{i, cur[0]+cur[1], cur[0], cur[1]});
        }
        
        int rank = 0;
        int prev = 0;
        int[] max = new int[2];
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            rank++;
            
            if(cur[2]>=max[0] && cur[3]>=max[1]) {
                max[0] = cur[2];
                max[1] = cur[3];
            }
            else if(cur[2]<max[0] && cur[3]<max[1]) {
                if(cur[0] == 0) {
                    rank = -1;
                    break;
                }
                rank--;
            }
            
            if(cur[0] == 0) {
                break;
            }
        }
        return rank;
    }
}