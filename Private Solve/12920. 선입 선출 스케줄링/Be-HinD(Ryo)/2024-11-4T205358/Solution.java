import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
    
        int low = 0;
        int high = Integer.MAX_VALUE;
        int task = 0;
        int time = 0;
        while(low <= high) {
            final int mid = low + (high-low)/2;
            
            int sum = cores.length;
            for(int i=0; i<cores.length; i++) {
                sum += mid / cores[i];
                if(sum >= n) break;
            }
            
            if(sum >= n) {
                high = mid - 1;
                time = mid;
                task = sum;
            }
            else {
                low = mid + 1;
            }
        }
        
        task -= n; //남은 작업
        
        for(int i=cores.length-1; i>=0; i--) {
            if(time % cores[i] == 0) {
                if(task == 0) {
                    return i+1;
                }
                task--;
            }
        }
        
        
        return answer;
    }
}