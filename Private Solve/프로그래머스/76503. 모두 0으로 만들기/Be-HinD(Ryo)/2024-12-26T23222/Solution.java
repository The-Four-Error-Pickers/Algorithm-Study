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
    static class Tree {
        int v;
        int w;
        Tree parent;
        List<Tree> children;
        public Tree(int value, int w) {
            this.v = value;
            this.w = w;
            this.parent = null;
            this.children = new ArrayList<>();
        }

        public void addChild(Tree child) {
            this.children.add(child);
        }
    }
    static List<List<Integer>> list;
    static int[] value;
    static long res;
    public long solution(int[] a, int[][] edges) {
        
        //제로섬 전처리
        int sum = 0;
        for(int idx : a) {
            sum += idx;
        }
        if(sum != 0) return -1;
        
        list = new ArrayList<>();
        for(int i=0; i<a.length; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<edges.length; i++) {
            int[] edge = edges[i];
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        value = a;
        
        Tree root = new Tree(0, a[0]);
        makeTree(root, null);
        value[0] = makeZero(root);

        if(value[0] != 0) return -1;
        // System.out.println(Arrays.toString(value));
        return res;
    }
    
    static void makeTree(Tree currentNode, Tree parent) {
        currentNode.parent = parent;

        for (int idx : list.get(currentNode.v)) {
            if (parent == null || idx != parent.v) {
                Tree childNode = new Tree(idx, value[idx]);
                currentNode.addChild(childNode);
                makeTree(childNode, currentNode);
            }
        }
    }
    static int makeZero(Tree current) {
        if(!current.children.isEmpty()) {
            //리프노드가 아닐경우
            int cnt = 0;
            for(Tree t : current.children) {
                cnt += makeZero(t);
            }
            value[current.v] += cnt;
            res += Math.abs(value[current.v]);
            return value[current.v];
        }
        else {
            //리프노드일 경우
            int ret = value[current.v];
            res += Math.abs(ret);
            value[current.v] = 0;
            return ret;
        }
    }
}