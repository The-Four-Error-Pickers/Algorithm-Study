function solution(enroll, referral, seller, amount) {
    const referralMap = {}
    const recommendMap = {}
    
    const N = enroll.length
    const money = Array(N).fill(0)

    for (let i=0; i<N; i++) {
        referralMap[enroll[i]] = i
        recommendMap[enroll[i]] = referral[i]
    }

    for (let i=0; i<seller.length; i++) {
        
        let curSeller = seller[i]
        let curMoney = amount[i] * 100

        while (curSeller !== '-' && curMoney > 0) {
            
            const idx = referralMap[curSeller]
            const referralMoney = Math.floor(curMoney * 0.1)
            const ownMoney = curMoney - referralMoney

            // 내 돈
            money[idx] += ownMoney

            curMoney = referralMoney
            curSeller = recommendMap[curSeller]

            // 1원 미만
            if (referralMoney < 1) {
                break
            }
        }
    }

    return money
}
