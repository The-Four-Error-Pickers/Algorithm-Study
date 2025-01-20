import java.util.*;

class Solution {
    
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        if(cores.length >= n) 
            return n;

        int l = 0, r = 50_000_000, mid;
        int jobs;
        int save = 0, time = 0;
        while(l <= r) {
            mid = l + (r - l) / 2;
            jobs = 0;
            
            for(int i = 0; i < cores.length; i++) {
                jobs += (mid / cores[i]) + 1;
            }
            
            if(jobs > n) {
                r = mid - 1;
            } else {
                l = mid + 1;
                save = jobs;
                time = mid;
            }
        }

        int min = Integer.MAX_VALUE, value;
        for(int i = cores.length - 1; i >= 0; i--) {
            value = time % cores[i];
            if(value < min) {
                min = value;
                answer = i + 1;
             
            }
        }

        return answer;
    }

}