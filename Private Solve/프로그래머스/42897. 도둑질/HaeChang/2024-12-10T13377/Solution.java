import java.util.*;

class Solution {
    static int[] dp;
    static int n;
    public int solution(int[] money) {
        int answer = 0;
        n = money.length;
        dp = new int[n];
        dp[0] = money[0];
        dp[1] = Math.max(dp[0], money[1]);
        for(int i = 2;i < n ;i++) {
            dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
        }
        
        return dp[n - 1];
    }  
}