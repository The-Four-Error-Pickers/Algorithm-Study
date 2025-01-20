import java.util.*;
class Solution {
    static int[] arr;
    public int[] solution(int target) {
        int[] answer = new int[2];
        /**
        키워드
        남은 점수보다 큰 점수로 득점하면 버스트가 되며 실격
        같은 라운드에 0점을 만들면 두 선수 중 "싱글" 또는 "불"을 더 많이 던진 선수가
        그마저도 같다면 선공인 선수가 승리
        접근법
        거스름돈 그리디 문제와 동일
        맞출 수 있는 점수의 내림차순으로 정렬
        **/
        
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=20; i++) {
            set.add(i);
            set.add(i*2);
            set.add(i*3);
        }
        set.add(50);
        
        arr = new int[set.size()];
        int id = 0;
        for(int idx : set) {
            arr[id++] = idx;
        }
        
        Arrays.sort(arr);
        
        //그리디로 하게되면 불을 최대한 많이 써야한다.
        while(target > 0) {
            if(target >= 50) {
                target -= 50;
                answer[0]++;
                answer[1]++;
            }
            else {
                int idx = lower(target);
                target -= arr[idx];
                answer[0]++;
                if(arr[idx] == 50) answer[1]++;
                else if(arr[idx] >= 1 && arr[idx] <= 20) answer[1]++;
            }
        }
        
        return answer;
    }
    
    private static int lower(int key) {
        int low = 0;
        int high = arr.length;
        
        while(low < high) {
            final int mid = low + (high - low)/2;
            
            if(arr[mid] >= key) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        
        return high;
    }
}