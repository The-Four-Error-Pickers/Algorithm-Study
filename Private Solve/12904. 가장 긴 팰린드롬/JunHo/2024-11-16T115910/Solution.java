class Solution {
    public int solution(String s) {
        int answer = 1;

        loop : for(int length = s.length(); length > 0; length--) {    // 1. 길이 정하기
            loop2 : for(int idx = 0; idx + length <= s.length(); idx++) {  // 2. 길이로 부터 시작할 수 있는 모든 인덱스 탐색
                for(int i = 0; i < length / 2; i++) {    // 3. 양 끝이 똑같은지 확인
                    if(s.charAt(idx + i) != s.charAt(idx + length - i - 1)) {   // 양 끝이 다르면 다음 인덱스 탐색
                        continue loop2;    
                    }
                }
                
                answer = length;
                break loop;
            }
        }

        return answer;
    }
}