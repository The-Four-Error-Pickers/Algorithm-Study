import java.util.*;

class Solution {

    class Node {
        int vertex; // 연결된 정점 번호 저장
        Node next;  // 연결리스트 유지를 위한 변수

        Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        Node[] adjList = new Node[n + 1];   // 연결정보를 저장하기 위한 배열
        int[] degree = new int[n + 1];  // 각 번호마다 차수 저장
        for (int i = 0; i < lighthouse.length; i++) {
            int from = lighthouse[i][0];
            int to = lighthouse[i][1];

            // 차수 증가 및 연결정보 저장
            degree[from]++;
            degree[to]++;
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        // 리프 노드랑 연결된 노드를 그때마다 갱신
        Set<Integer> set = new HashSet<>();

        while (true) {
            // 리프 노드를 찾기
            for (int i = 1; i <= n; i++) {
                if (degree[i] == 1) {    // 차수가 1이면 리프노드
                    for (Node temp = adjList[i]; temp != null; temp = temp.next) {
                        if (degree[temp.vertex] > 0) set.add(temp.vertex);   // 차수가 0 이상인 연결된 노드 추가
                    }
                }
            }

            // 더 이상 불을 킬게 없는 경우
            if (set.isEmpty()) break;

            // 불을 켜는 작업
            for (int vertex : set) {
                if (degree[vertex] <= 0) continue; // 이미 처리된 노드는 무시
                degree[vertex] = 0; // 불을 켬
                answer++;

                // 연결된 노드 차수 감소
                for (Node temp = adjList[vertex]; temp != null; temp = temp.next) {
                    degree[temp.vertex]--;
                }
            }
            
            // set을 비우고 다시 갱신하기
            set.clear();
        }

        return answer;
    }
}
