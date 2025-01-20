/**
키워드
가중치가 부여된 트리
모든 점들의 가중치를 0으로

접근법
정점 <= 300,000 //완전탐색으로는 불가능
음수 가중치 가능
간선 = 정점-1개
전처리 : a의 모든 가중치가 0인지 확인 후 판별
리프(혹은 루트)에서부터 0으로 만들기 <<
**/

import java.util.*;
class Solution {
    static List<List<Integer>> list;
    static long[] value;
    static long res;
    static int[] v;
    public long solution(int[] a, int[][] edges) {
        
        //제로섬 전처리
        value = new long[a.length];
        
        int sum = 0;
        for(int i=0; i<a.length; i++) {
            value[i] = a[i];
            sum += a[i];
        }
        if(sum != 0) return -1;
        
        list = new ArrayList<>();
        for(int i=0; i<a.length; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<edges.length; i++) {
            int[] edge = edges[i];
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        v = new int[a.length];
        
        makeZero(0);

        if(value[0] != 0) return -1;
        // System.out.println(Arrays.toString(value));
        return res;
    }
    
    static long makeZero(int node) {
        v[node] = 1;
        for(int i=0; i<list.get(node).size(); i++){
            int next = list.get(node).get(i);
            if(v[next]==1) continue;
            value[node] += makeZero(next);
            
        }
        long num = value[node];
        res += Math.abs(num);
        return num;
    }
}