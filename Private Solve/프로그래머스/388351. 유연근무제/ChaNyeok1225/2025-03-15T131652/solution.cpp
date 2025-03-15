#include <iostream>

#include <string>
#include <vector>

using namespace std;

int solution(vector<int> schedules, vector<vector<int>> timelogs, int startday) {
    int answer = 0;
    int chkDay[] = {0,1,1,1,1,1,0,0,1,1,1,1,1,0,0};
    int len = schedules.size();
    
    for(int i = 0; i < len; i++) {
        int target = schedules[i] + 10;
        if(target % 100 >= 60) {
            target += 40;
        }
        
        bool flag = true;
        for(int j = 0; j < 7; j++) {
            if(chkDay[startday + j] && timelogs[i][j] > target) {
                flag = false;
                break;
            }
        }
        
        if(flag) 
            answer++;
        
    }
    
    return answer;
}