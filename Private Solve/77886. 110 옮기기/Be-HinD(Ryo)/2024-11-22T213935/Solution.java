import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        
        String[] res = new String[s.length];
        int p = 0;
        for(String idx : s) {
            res[p++] = process(idx);
        }
        return res;
    }
    
    // "110"추출을 제외한 나머지 로직에 대한 메서드
    private static String process(String idx) {
        String remainStr = extract110(idx);
        
        int cnt110 = (idx.length() - remainStr.length()) / 3; //추출된 "110"의 개수
        
        //삽입위치 탐색
        int zeroIdx = 0;
        for(int i=0; i<remainStr.length(); i++) {
            if(remainStr.charAt(i) == '0') {
                zeroIdx = i+1;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(int i=0; i<zeroIdx; i++) {
            res.append(remainStr.charAt(i));
        }
        for(int i=0; i<cnt110; i++) {
            res.append("110");
        }
        for(int i=zeroIdx; i<remainStr.length(); i++) {
            res.append(remainStr.charAt(i));
        }
        return res.toString();
    }
    
    // "110"추출 메서드
    private static String extract110(String idx) {
        char[] chars = idx.toCharArray();
        char[] arr = new char[chars.length];
        
        int p = 0;
        
        for(int i=0; i<chars.length; i++) {
            arr[p] = chars[i];
            if(p >= 2) {
                if(arr[p-2] == '1' && arr[p-1] == '1' && arr[p] == '0') {
                    p -= 3;
                }
            }
            p++;
        }
        
        
        // 효율적인 문자열 연산을 위한 StringBuilder 활용
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<p; i++) {
            sb.append(arr[i]);
        }
        
        return sb.toString();
    }
    
}