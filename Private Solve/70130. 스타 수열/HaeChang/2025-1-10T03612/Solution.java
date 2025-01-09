// x의 길이가 2 이상의 짝수
// x의 길이를 2n 이라고 할때, 다음과 같은 n개의 집합의 교집합의 원소의 개수가 1이상
// 길이가 가장 긴 스타수열의 길이를 return

// 스타수열에 있어서 공통되는 값이 무조건 하나이상 있어야 한다.
// 그렇다는것은 cnt에 있어서 최소 2개이상 있어야 하는것
// 그러한 후보 값을 기준으로
// 후보값이 포함된 집합을 찾는데, 자기 자신과 인접한 원소에 대해서 검사하면 된다.
// 왜냐하면 스타 수열 내 인접한 원소와 같으면 안되기 때문이다.
// 정확하게는 (n + 1) % 2 == 0 인경우 n 번째 원소와 같아선 안되기 때문이다.
// [5, 2, 3, 3] 에서 

import java.util.*;
class Solution {
    public int solution(int[] a) {
        int answer = -1;
        int size = 0;
        int[] cnt = new int[a.length];
        for(int num: a) cnt[num]++;
        for(int i = 0; i < cnt.length; i++) {
            int elem = i;
            int c = 0;
            for(int j = 1; j < a.length; j++) {
                if ((elem == a[j - 1] || elem == a[j]) && (a[j - 1] != a[j])) {
                    c += 2;
                }
                if (a[j - 1] == a[j]) {
                    j +=1;
                }
            }
            answer = Math.max(answer, c);
        }
        
        return answer;
    }
}