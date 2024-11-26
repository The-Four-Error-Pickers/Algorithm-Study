import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] profit = new int[enroll.length];
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> personIdx = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
            personIdx.put(enroll[i], i);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String currentSeller = seller[i];
            int currentPrice = amount[i] * 100;
            
            while (!currentSeller.equals("-")) {
                int parentPrice = currentPrice / 10;   // 부모에게 줄 금액
                int sellerProfit = currentPrice - parentPrice;
                
                profit[personIdx.get(currentSeller)] += sellerProfit;
                currentSeller = parent.get(currentSeller);
                
                if (parentPrice == 0) break; 
                currentPrice = parentPrice;
            }
        }
        
        return profit;
    }
}