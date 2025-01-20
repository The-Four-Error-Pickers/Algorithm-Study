import java.util.*;

class Solution {
    static int min;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        min = 987654321;
        // 외벽의 총 둘레는 n 미터, 취약한 지점이 있음
        // 점검 시간은 1시간 제한
        // 최소한의 인구를 투입하기로 함
        // 외벽을 따라 시계 혹은 반시계로만 이동함
        // dist : idx번째 친구가 이동할 수 있는 max distance
        // week : 점검 위치 변위
        for(int i = 0;i<weak.length;i++) {
            for(int j = 0;j<dist.length;j++) {
                boolean[] v1 = new boolean[n];
                boolean[] use1 = new boolean[dist.length];
                use1[j] = true;
                go(v1, dist[j], weak[i], 1, n);
                dfs(v1, use1, weak, dist, n);
                boolean[] v2 = new boolean[n];
                boolean[] use2 = new boolean[dist.length];
                use2[j] = true;
                go(v2, dist[j], weak[i], -1, n);
                dfs(v2, use2, weak, dist, n);
            }
        }
        
        return min == 987654321 ? -1: min;
    }
    static void dfs(boolean[] v, boolean[] use, int[] weak, int[] dist, int n) {        
        boolean find = false;
        for(int i = 0;i<weak.length;i++) {
            for(int j = 0;j<dist.length;j++) {
                if (v[weak[i]]) continue;
                if (use[j]) continue;
                find = true;
                use[j] = true;
                boolean[] newV1 = v.clone();
                go(newV1, dist[j], weak[i], 1, n);
                dfs(newV1, use, weak, dist, n);
                boolean[] newV2 = v.clone();
                go(newV2, dist[j], weak[i], -1, n);
                dfs(newV2, use, weak, dist, n);
                use[j] = false;
            }
        }
        if(!find) {
            int cnt = 0;
            for(int i = 0;i<use.length;i++) {
                if(use[i]) cnt++;
            }
            min = Math.min(cnt, min);
        }
    }
    static void go(boolean[] v, int dis, int s, int dir, int n) {
        if (dir == 1) {
            // 시계
            for(int i = s;i<s + dis;i++) {
                v[s % n] = true;
            }
        } else {
            // 반시계
            while(dis-- > -1) {
                v[(s + n) % n] = true;
                s--;
            }
        }
    }
}