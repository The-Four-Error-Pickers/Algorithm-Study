import java.util.*;

class Solution {
    
    static int wlen, dlen, answer;
    static int[] eweak, edist, fix;
    static boolean[] vis;
    
    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        wlen = weak.length;
        dlen = dist.length;
        
        eweak = new int[wlen * 2];
        vis = new boolean[dlen];
        fix = new int[wlen];
        Arrays.fill(fix, -1);
        edist = dist;
        
        for(int i = 0; i < wlen; i++) {
            eweak[i] = weak[i];
            eweak[i + wlen] = weak[i] + n;
        }
        
        dfs(0, 0, 0);
        
        // 불가능하면 -1로 만드는거 놓침
        if(answer == Integer.MAX_VALUE)
            answer = -1;
        return answer;
    }
    
    void dfs(int widx, int cnt, int inspect) {
        if(answer <= cnt)
            return;
        
        if(widx >= wlen) {
            if(inspect >= wlen) 
                answer = answer < cnt ? answer : cnt;
            return;
        }
        
        for(int i = 0; i < dlen; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            
            int inspectCnt = 0;
            for(int j = widx; j < widx + wlen; j++) {
                if(fix[j % wlen] != -1 || eweak[widx] + edist[i] < eweak[j])
                    break;
                inspectCnt++;
                fix[j % wlen] = i;
            }
            
            dfs(widx + inspectCnt, cnt + 1, inspect + inspectCnt);
            vis[i] = false;
            for(int j = widx; j < widx + wlen; j++) {
                if(fix[j % wlen] != i)
                    break;
                fix[j % wlen] = -1;
            }
            
        }
        
        dfs(widx + 1, cnt, inspect);
        
    }
}