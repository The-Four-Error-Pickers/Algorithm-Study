import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
    
        int low = 0;
        int high = Integer.MAX_VALUE;
        
        while(low <= high) {
            final int mid = low + (high-low)/2;
            
            int sum = 0;
            for(int i=0; i<cores.length; i++) {
                sum += mid / cores[i];
                if(sum >= n) break;
            }
            
            if(sum >= n) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        // high = n이전의 작업을 처리한 시간
        
        int sum = 0;
        for(int i=0; i<cores.length; i++) {
            sum += high / cores[i];
        }
        
        //이후 마지막 작업을 처리하는 코어 탐색
        for(int i = 0; i < cores.length; i++) {
            if(sum == n) {
                return i+1;
            }
            if((high+1) % cores[i] == 0) {
                sum++;
            }
        }
        
        
        return answer;
    }
}