class Solution {
    
    static int[] dx = {1, 0, 0, -1};  // d, l, r, u
    static int[] dy = {0, -1, 1, 0};
    static String[] s = {"d", "l", "r", "u"};
    static String answer = "z";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        int distance = getDistance(x - 1, y - 1, r - 1, c - 1);
        if((distance > k) || ((k - distance) & 1) == 1) return "impossible";
        
        dfs(0, "", n, m, x - 1, y - 1, r - 1, c - 1, k);
        return answer;
    }
    
    // distance : 현재까지 거리, route : 현재까지의 경로
    public void dfs(int distance, String route, int n, int m, int tempX, int tempY, int endX, int endY, int k) {        
        if((distance + getDistance(tempX, tempY, endX, endY)) > k) return;
        if(answer.compareTo(route) < 0) return;
        if((distance == k) && (tempX == endX) && (tempY == endY)) {
            answer = route;
            return;
        }
        
        for(int d = 0; d < 4; d++) {
            int currentX = tempX + dx[d], currentY = tempY + dy[d];
            if((currentX < 0) || (currentX > n - 1) || (currentY < 0) || (currentY > m - 1)) continue;
            dfs(distance + 1, route + s[d], n, m, currentX, currentY, endX, endY, k);
        }
    }
    
    public int getDistance(int tempX, int tempY, int endX, int endY) {
        return Math.abs(endX - tempX) + Math.abs(endY - tempY);
    }
}