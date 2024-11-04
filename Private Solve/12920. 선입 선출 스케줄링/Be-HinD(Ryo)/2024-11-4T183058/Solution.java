import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        /**
        cores -> 오름차순 정렬
        
        **/
        
        Arrays.sort(cores);
        int t = 0;
        int[] schedule = cores.clone();
        n -= cores.length;
        
        while(true) {
            boolean flag = true;
            for(int i=0; i<schedule.length; i++) {
                schedule[i]--;
                if(schedule[i] == 0) {
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