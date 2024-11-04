import java.util.*;

class Solution {
    
    class Core {
        int index;
        int endTime;
        
        Core(int i, int t) {
            index = i;
            endTime = t;
        }
    }
    
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        PriorityQueue<Core> pq = new PriorityQueue<>((a, b) -> {
            return a.endTime == b.endTime ? a.index - b.index : a.endTime - b.endTime;
        });
        
        for(int i = 0; i < cores.length; i++) 
            pq.offer(new Core(i, 0));
        
        Core core;
        while(n-- > 1) {
            core = pq.poll();
            core.endTime += cores[core.index];
            pq.offer(core);
        }
        
        return pq.peek().index + 1;
    }
}