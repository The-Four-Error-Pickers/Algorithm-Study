import java.util.*;
// 1, -1, 1, -1, 1, -1 이거나
// -1, 1, -1, 1 인거네
// 어떤 특정한 연속 부분수열에 펄스 부분수열을 적용시키는것

// 어쨋든 중요한것은 자기 앞까지 -1이었냐 +1이었냐를 따져봐야 하는것
// 부분 수열의 합


// 현재수를 +1을 곱한걸 선택할지 -1을 곱한걸 선택할지 각 수마다 존재한다.
// 



class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        long[][] dp = new long[sequence.length][2];
        dp[0][0] = sequence[0];     // 1을 사용한 경우
        dp[0][1] = sequence[0] * -1; // 앞에 -1을 사용한 경우
        
        answer = Integer.MIN_VALUE;
        
        for(int i = 1;i<sequence.length;i++) {
            dp[i][0] = Math.max(dp[i][0], sequence[i]);
            // 선택안한 것
            dp[i][1] = Math.max(dp[i][1], sequence[i] * -1);
            // 현재수 -1로 새롭게 시작하는것
            dp[i][0] = Math.max(dp[i - 1][1] + sequence[i], dp[i][0]); 
            // 자기 앞까지 -1를 사용한 숫자에서 현재수를 더한것과 현재값
            dp[i][1] = Math.max(dp[i - 1][0] + (sequence[i] * -1), dp[i][1]);
            // 자기 앞까지 1을 사용한 숫자에서 현재수의 -1을 곱한것과 현재값
            answer = Math.max(Math.max(dp[i][0], dp[i][1]), answer);
        }
                
        return Math.max(dp[0][0], Math.max(dp[0][1], answer));
    }
}