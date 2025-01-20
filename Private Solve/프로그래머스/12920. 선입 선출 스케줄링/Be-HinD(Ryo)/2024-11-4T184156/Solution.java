import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        /**
        cores -> 오름차순 정렬
        
        **/

        int minTime = Integer.MAX_VALUE;
        for(int i=0; i<cores.length; i++) {
            minTime = Math.min(minTime, cores[i]);
        }
        int t = 0;
        int[] schedule = cores.clone();
        n -= cores.length;
        
        while(true) {
            boolean flag = true;
            for(int i=0; i<schedule.length; i++) {
                schedule[i] -= minTime;
                if(schedule[i] < 1) {
                    n--;
                    if(n == 0) {
                        answer = i+1;
                        flag = false;
                        break;
                    }
                    schedule[i] = cores[i];
                }
            }
            
            if(!flag) break;
        }
        return answer;
    }
}