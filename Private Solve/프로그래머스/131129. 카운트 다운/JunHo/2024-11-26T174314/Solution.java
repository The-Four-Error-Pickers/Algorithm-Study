import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];  // dp[i][0] : 점수 i를 만들때 최소 다트 수, dp[i][2] : i를 만들때 최대 싱글/불 횟수
        
        for(int i = 1; i <= target; i++) dp[i][0] = 100001; // 나올수 없는 최댓값으로 설정
        
        // 다트 점수를 계산
        for (int n = 1; n <= target; n++) {
            // 1. 불을 맞추는 경우 (점수 50)
            if (n >= 50) {
                int[] temp = dp[n - 50];
                update(dp, n, temp[0] + 1, temp[1] + 1); // 불은 싱글/불 횟수에 포함
            }

            // 2. 싱글, 더블, 트리플을 맞추는 경우
            int start = Math.min(20, n); // 다트 점수는 최대 20(탐색 최솟값을 위한 코드
            for (int i = start; i >= 1; i--) {
                for (int j = 1; j <= 3; j++) { // 싱글, 더블, 트리플
                    if (n >= i * j) { // 점수를 초과하지 않는 경우만 계산
                        int[] temp = dp[n - i * j];
                        int singleCount = (j == 1) ? 1 : 0; // 싱글이면 카운트 증가
                        update(dp, n, temp[0] + 1, temp[1] + singleCount);
                    }
                }
            }
        }
        
        return dp[target];
    }
    
    // dp 테이블을 업데이트하는 함수
    public void update(int[][] dp, int n, int dartCount, int singleBullCount) {
        // 1. 최소 다트 수가 더 적은 경우 갱신, 2. 다트가 같은 경우는 싱글, 불 카운트 비교
        if (dartCount < dp[n][0] || (dartCount == dp[n][0] && singleBullCount > dp[n][1])) {
            dp[n][0] = dartCount;
            dp[n][1] = singleBullCount;
        }
    }
}