import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        // 여러개의 코어가 있고, 코어별로 한 작업을 처리하는데 걸리는 시간이 다름
        // 초기 코어할당은 시간으로 계산하지 않음, 코어수가 이미 해야할일들 보다 더 많은경우에는 n번째 코어가 항상 마지막임
        if (cores.length >= n) return n;
        // 그러면 그게 아니라고 해서 미배정 작업들이 남게 되면 여기서부터가 문제임
        // 2개 이상의 코어가 남는경우 앞의 코어부터 작업을 처리한다 -> 는 결국 인덱스가 더 적은게 앞선다는 뜻
        int remain = n - cores.length;
        // 1
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        
        for(int i = 0;i<cores.length;i++) {
            pq.add(new int[]{cores[i], i});
        }
        int time = 1;
        while(remain > 0) {
            int[] now = pq.poll();
            
            if (time == now[0]) {
                remain--;
            } else if (time < now[0]) {
                time = now[0];
                remain--;
            }
            
            if (remain == 0) {
                answer = now[1] + 1;
                break;
            } else {
                pq.add(new int[] {time + now[0], now[1]});
            }
        }
        
        return answer;
    }
}