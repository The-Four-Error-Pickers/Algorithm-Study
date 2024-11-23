import java.util.*;
class Solution {
    static boolean[] v;
    public int solution(int[] cards) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        v = new boolean[cards.length];
        for(int i=0; i<cards.length; i++) {
            pq.offer(openBox(i, cards));
        }
        
        return pq.size() < 2 ? 0 : pq.poll() * pq.poll();
    }
    
    static int openBox(int idx, int[] arr) {
        int res = 0;
        
        while(!v[idx]) {
            res++;
            v[idx] = true;
            idx = arr[idx]-1;
        }
        
        return res;
    }
}