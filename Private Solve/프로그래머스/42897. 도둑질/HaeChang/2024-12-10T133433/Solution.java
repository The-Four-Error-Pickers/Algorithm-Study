import java.util.*;

class Solution {
    static int[] dp;
    static int n;
    public int solution(int[] money) {
        int answer = 0;
        n = money.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        dfs(0, money);
        dfs(1, money);
        
        return Math.max(dp[0], dp[1]);
    }
    static int dfs(int depth, int[] m) {
        if (dp[depth] != -1) {
            return dp[depth];
        }
        
        dp[depth] = m[depth];
        int max = 0;
        for(int i = depth + 2; i < n ;i++) {
            max = Math.max(dfs(i, m), max);
        }
        dp[depth] += max;
        return dp[depth];
    }    
}