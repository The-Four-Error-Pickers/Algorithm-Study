import java.util.*;

class Solution {
    // 언제나 좌상에서 우하로 가야함
    // 경주로는 두 칸을 연결하는 도로이고, 그것이 일직선이면 직선도로, 꺾이면 코너라고 한다.
    // 직선도로는 100원, 코너는 500원
    // 0은 비어있고, 1은 벽
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n;
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        // 최소비용 경주로 건설
        // 다익스트라로 추정됨
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[3] - b[3]);
        int[][] cost = new int[n][n];
        for(int i = 0;i<n;i++) Arrays.fill(cost[i], 987654321);
        cost[0][0] = 0;
        for(int dir = 0;dir<4;dir++ ) {
            int ny = 0 + dy[dir];
            int nx = 0 + dx[dir];
            if (OOB(ny, nx) || board[ny][nx] == 1) continue;
            cost[ny][nx] = 100;
            pq.add(new int[]{ny, nx, dir, cost[ny][nx]});
        }
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            if (cost[now[0]][now[1]] < now[3]) continue;
            if (now[0] == n-1 && now[1] == n-1) break;
            for(int dir = 0;dir < 4;dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (OOB(ny, nx) || board[ny][nx] == 1) continue;
                if (isCorner(now[2], dir)) {
                    
                    if(cost[ny][nx] > now[3] + 500 + 100) {
                        cost[ny][nx] = now[3] + 500 + 100;
                        pq.add(new int[]{ny, nx, dir, cost[ny][nx]});
                    }
                } else {
                    if (cost[ny][nx] > now[3] + 100) {
                        cost[ny][nx] = now[3] + 100;
                        pq.add(new int[]{ny, nx, dir, cost[ny][nx]});
                    }   
                }
            }
        }
        return cost[n-1][n-1];
    }
    static boolean isCorner(int prevDir, int dir) {
        if ((prevDir == 2 || prevDir == 3) && (dir == 0 || dir == 1)) {
            return true;
        } else if ((dir == 2 || dir == 3) && (prevDir == 0 || prevDir == 1)) {
            return true;
        }
        return false;
    }
    static boolean OOB(int y, int x) {
        return y >= n || y < 0  || x >= n || x < 0;
    }
}