import java.util.*;

// 21:27
// 강철 부대가 위치한 지역은 중복없는 번호로 구분되고, 두 지역간에 길을 통과하는데 걸리는 시간은 1로 통일
// 임무의 시작때와 다르게 되돌아오는 경로가 없어서 복귀가 불가능한 부대원이 있을 수 있음, 복귀가 불가능한 경우 -1이된다.
// 양방향 간선이며 가중치가 존재함, 간선의 개수가 50만, 노드의 개수는 10만 -> 희소 그래프
// 역으로 생각해서 강철부대에서 각 부대원들이 위치한 지역까지의 최단거리를 구하는 문제일듯?
// 역방향 그래프를 그려야하나 싶었는데 어짜피 양방향이라 그럴필요가 없음, 만약 단방향 가중치 그래프였으면 역방향 그래프를 그려서 문제를 접근하려 했을 것
// 다익스트라를 사용해서 강철부대의 위치에서 sources 배열에 해당하는 위치까지의 거리를 구하면 될듯

class Solution {
    static class Node implements Comparable<Node>{
        int e;
        int w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
        
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static ArrayList<Node>[] g;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        int[] cost = new int[n+1];
        g = new ArrayList[n+1];
        for(int i = 1;i<n+1;i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0;i<roads.length;i++) {
            int s = roads[i][0];
            int e = roads[i][1];
            g[s].add(new Node(e, 1));
            g[e].add(new Node(s, 1));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(cost, 987654321);
        cost[destination] = 0;
        pq.add(new Node(destination, cost[destination]));
        int cnt = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if (cost[now.e] < now.w) continue;
            if (++cnt == n) break;
            for(Node node: g[now.e]) {
                if (cost[node.e] > now.w + node.w) {
                    cost[node.e] = now.w + node.w;
                    pq.add(new Node(node.e, cost[node.e]));
                }
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i = 0;i<sources.length;i++) {
            answer[i] = (cost[sources[i]] == 987654321 ? -1 : cost[sources[i]]);
        }
        return answer;
    }
}