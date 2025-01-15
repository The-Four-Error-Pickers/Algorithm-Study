class Solution {
    public int solution(int[] a) {
        
        int[] count = new int[a.length + 1];
        for(int i = 0; i < a.length; i++) count[a[i]]++;
        
        int answer = 0;
        for(int i = 0; i < count.length; i++) {
            if(count[i] < 2) continue; // 1개나 0개는 탐색할 필요 X
            if(answer >= count[i] * 2) continue;    // 현재 정답보다 만들 수 있는 스타수열의 길이가 작은 경우 다음꺼 탐색
            
            int temp = 0;   // 현재 i 값으로 만들 수 있는 길이
            for(int j = 0; j < a.length - 1; j++) {
                if(((a[j] == i) || (a[j + 1] == i)) && (a[j] != a[j + 1])) {
                    temp += 2;
                    j++;
                }
            }
            answer = Math.max(answer, temp);
        }
        
        return answer;
    }
}