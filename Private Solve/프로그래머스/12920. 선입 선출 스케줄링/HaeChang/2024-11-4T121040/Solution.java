import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        // 여러개의 코어가 있고, 코어별로 한 작업을 처리하는데 걸리는 시간이 다름
        // 초기 코어할당은 시간으로 계산하지 않음, 코어수가 이미 해야할일들 보다 더 많은경우에는 n번째 코어가 항상 마지막임
        if (cores.length >= n) return n;
        // 그러면 그게 아니라고 해서 미배정 작업들이 남게 되면 여기서부터가 문제임
        // 2개 이상의 코어가 남는경우 앞의 코어부터 작업을 처리한다 -> 는 결국 인덱스가 더 적은게 앞선다는 뜻
        // 시간관리해주는 priorityQueue 사용해볼까?
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
           if (a[0] == b[0]) {
               return a[1] - b[1];
           } 
           return a[0] - b[0];
        });
        for(int i = 0;i<cores.length;i++) {
            pq.add(new int[]{cores[i], i});
        }
        int job = n - cores.length;
        
        int total = 0;
        answer = n;
        while(job > 0 && !pq.isEmpty()) {
            int[] worker = pq.peek();    
            int time = worker[0];
            total += time;
            while(!pq.isEmpty() && pq.peek()[0] <= total && job > 0) {
                int[] peek = pq.poll();
                pq.add(new int[]{total + cores[peek[1]], peek[1]});
                job--;
                answer = peek[1] + 1;
            }
        }
        
        return answer;
    }
}