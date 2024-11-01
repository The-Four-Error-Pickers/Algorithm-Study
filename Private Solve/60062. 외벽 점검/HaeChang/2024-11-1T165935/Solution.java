import java.util.*;

class Solution {
    static int min;
    static boolean find;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        min = 987654321;
        // 외벽의 총 둘레는 n 미터, 취약한 지점이 있음
        // 점검 시간은 1시간 제한
        // 최소한의 인구를 투입하기로 함
        // 외벽을 따라 시계 혹은 반시계로만 이동함
        // dist : idx번째 친구가 이동할 수 있는 max distance
        // week : 점검 위치 변위
        Arrays.sort(dist);
        for(int i = 0;i<weak.length;i++) {
            
            // 어짜피 한쪽방향으로만 밀면 된다. 그리고 weak는 증가할 수 밖에없다. 물론 n을 넘어서면 모듈러가 되지만
            // 즉, dist를 어떻게 더하느냐에 따라 달라지는데, dist는 왠만하면 큰걸 먼저 사용하는것이 좋다. 
            // 결국 목표하는 지점까지 도달하지 못하는 경우를 배제해야 한다.
            int end = weak[(i + weak.length - 1) % (weak.length)];
            if (end < weak[i]) {
                // 끝지점이 n을 넘어서는 경우
                end = weak[(i + weak.length - 1) % (weak.length)] + n;
            }
            find = false;
            
            dfs(weak[i], end, weak, dist, 0, n, dist.length - 1);
        }
        
        return min == 987654321 ? -1: min;
    }
    static void dfs(int s, int e, int[] w, int[] d, int uCnt, int n, int start) {
        if(find) return;
        if(start < 0) return;
        if(s >= e) {
            min = Math.min(uCnt, min);
            find = true;
            return;
        }
        
        for(int j = 0;j < w.length;j++) {
            int wVal = s + d[start] > n ? w[j] + n : w[j];
            if (wVal > s + d[start]) {
                dfs(wVal, e, w, d, uCnt + 1, n, start - 1);   
                break;
            }
        }
    }
}