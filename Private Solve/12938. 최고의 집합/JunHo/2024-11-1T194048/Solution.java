import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
    
        // 불가능한 경우
        if(n >= s) return new int[]{-1};
        
        // 풀이 : s를 n만큼 나누고 나머지 만큼 1씩 더해주면 최댓값이 됨
        int[] answer = new int[n];
        
        for(int i = 0; i < n; i++) answer[i] = s / n;
        
        int mod = s % n;
        int idx = 0;
        while(mod-- > 0) {
            answer[idx++]++;
        }
        
        Arrays.sort(answer);

        return answer;
    }
}