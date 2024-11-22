/**
키워드
0 또는 1
0은 칸이 비어 있음
1은 해당 칸이 벽으로 채워져 있음
도착점은 (N-1, N-1) 칸
상, 하, 좌, 우로 인접한 두 빈 칸을 연결하여 건설
인접한 두 빈 칸을 상하 또는 좌우로 연결한 경주로를 직선 도로 100원
직선 도로가 서로 직각으로 만나는 지점을 코너 500원
최소 비용
접근법
board = 25이하 -> 완탐 O(N^6) 까지 가능
항상 경주로 건설 가능 형태
출발, 도착 == 항상 0
그리디 : 최대한 직선만 활용해서 건설
bfs 탐색
- 가는방향 그대로 : 100원 추가
- 가는방향과 다르면(코너) : 500원 추가
모든 방향 탐색이 진행되야 함으로 DFS 모든 경로 탐색 및 최소값 갱신

**/

import java.util.*;
class Solution {
    static int N, res;
    static int[][] map;
    static boolean[][] v;
    public int solution(int[][] board) {
        
        N = board.length;
        map = board;
        
        res = Integer.MAX_VALUE;
        v = new boolean[map.length][map[0].length];
        
        v[0][0] = true;
        dfs(0, 0, -1, 0);
        
        return res;
    }
    
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    private static void dfs(int x, int y, int d, int sum) {
        if(x == N-1 && y == N-1) {
            res = Math.min(res, sum);
            return;
        }
        
        // d에 따라서 직선인지 코너인지
        
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!isRange(nx,ny) || map[nx][ny] == 1 || v[nx][ny]) continue;

            //진행 가능 방향
            v[nx][ny] = true;
            if(d == -1 || d == i) {
                dfs(nx, ny, i, sum + 100);
            }
            else {
                dfs(nx, ny, i, sum + 600);
            }
            
            v[nx][ny] = false;
        }
    }
    
    private static boolean isRange(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) return false;
        return true;
    }
}