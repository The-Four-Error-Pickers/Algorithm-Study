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
                boolean[] v1 = new boolean[weak.length];
                boolean[] use1 = new boolean[dist.length];
                use1[j] = true;
                int cnt = 0;
                cnt += go(v1, dist[j], weak, i, 1, n);
                dfs(v1, use1, weak, dist, n, cnt, 1);
                boolean[] v2 = new boolean[weak.length];
                boolean[] use2 = new boolean[dist.length];
                cnt = 0;
                use2[j] = true;
                cnt += go(v2, dist[j], weak, i, -1, n);
                dfs(v2, use2, weak, dist, n, cnt, 1);
            }
        }
        
        return min == 987654321 ? -1: min;
    }
    static void dfs(boolean[] v, boolean[] use, int[] weak, int[] dist, int n, int wCnt, int uCnt) {
        if(min < uCnt) return;
        boolean find = false;
        for(int i = 0;i<weak.length;i++) {
            for(int j = 0;j<dist.length;j++) {
                if (v[i]) continue;
                if (use[j]) continue;
                find = true;
                use[j] = true;
                boolean[] newV1 = v.clone();
                int up = go(newV1, dist[j], weak, j, 1, n);
                dfs(newV1, use, weak, dist, n, wCnt + up, uCnt+1);
                boolean[] newV2 = v.clone();
                up = go(newV2, dist[j], weak, j, -1, n);
                dfs(newV2, use, weak, dist, n, wCnt + up, uCnt+1);
                use[j] = false;
            }
        }
        if(!find && wCnt == weak.length && min > uCnt) {
            min = uCnt;
        }
    }
    static int go(boolean[] v, int dis, int[] weak, int s, int dir, int n) {
        int cnt = 0;
        if (dir == 1) {
            for(int i = 0;i<weak.length;i++) {
                if (weak[i] >= s && weak[i] <= Math.min(s + dis, n) && !v[i]) {
                    v[i] = true;
                    cnt++;
                } else if (weak[i] >= 0 && s + dis - weak[weak.length-1] >= weak[i] && !v[i]) {
                    v[i] = true;
                    cnt++;
                }
            }
            return cnt;
        } else {
            // 반시계
            for(int i = 0;i<weak.length;i++) {
                if (weak[i] <= s && weak[i] >= Math.max(s - dis, 0) && !v[i]) {
                    v[i] = true;
                    cnt++;
                } else if (weak[i] >= s - dis + n && !v[i]) {
                    v[i] = true;
                    cnt++;
                }
            }
            return cnt;
        }
    }
}