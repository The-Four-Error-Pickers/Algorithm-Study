function solution(a) {
    let answer = 0;
    
    const counts = new Array(a.length).fill(0);
    a.forEach((v) => counts[v]++ );
        
    console.log(counts);
    
    for(let i=0; i<counts.length; i++){
        if(answer >= counts[i]) continue;
        
        let count = 0;
        
        for(let j=0; j<a.length-1; j++){
            if(a[j] === a[j+1]) continue;
            if(a[j] !== i && a[j+1] !== i) continue;
            
            count++;
            j++; // 다다음 위치로 이동
        }
        answer = Math.max(answer, count);
    }
    
    return answer*2;
}