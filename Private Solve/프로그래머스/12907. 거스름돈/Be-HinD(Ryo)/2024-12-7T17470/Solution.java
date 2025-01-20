import java.util.*;
class Solution {
    static int res;
    public int solution(int n, int[] money) {
        
        /**
        키워드
        방법의 경우의 수 (최소 개수 x)
        접근법
        보통 dp 풀이
        **/
        Arrays.sort(money);
        
        long[][] dp = new long[n+1][money.length+1];
        
        for(int i=1; i<=money.length; i++) {
            int coin = money[i-1];
            for(int j=1; j<=n; j++) {
                
                dp[j][i] = dp[j][i-1];
                
                if(j % coin == 0) dp[j][i]++; //현재 코인으로 j를 만들 수 있으면
                
                int price = j;
                while(true) {
                    if(price - coin > 0) {
                        price -= coin;
                        if(dp[price][i-1] > 0) dp[j][i]++;
                    }
                    else break;
                }
                
            }
        }
        
        return (int) dp[n][money.length] % 1000000007;
    }
}