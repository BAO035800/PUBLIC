function stalk(a){
    let found=0,truoc=[],giua=[],sau=[],mangmoi=[]
    let t=0,g=0,s=0,minngoac=0,maxngoac=0
    for(let i=0;i<a.length;i++){
        if(a[i]==='*'||a[i]==='/'||a[i]==='('||a[i]===')'){
            found=1
            break
        }
    }
    if(found===1){
        let ngoac=0;
        for(let i=0;i<a.length;i++){
            if(a[i]==='('){
                ngoac=1
            }
            else if(a[i]===')'&&ngoac===1){
                ngoac=0
                mangmoi.push(truoc)
            }
            else if(ngoac===1){
                truoc.push(a[i])
            }
            else if(ngoac===0){
                giua.push(a[i])
            }
        }
    }
    a=[truoc,giua]
    return a
}

function tinh(a,b){
    let result=0
    stalk(a)
    for(let i=0;i<a.length;i++){
        for(let j=0;j<b.length;j++){
            if(a[i]==b[j]){
                a[i]=b[j]
                break
            }
        }
    }
    let dau='+',found=0,gop=''
    for(let i=0;i<a.length;i++){
        for(let j=0;j<a.length;j++){
            if(a[j]==='+'||a[j]==='-'||a[j]==='*'||a[j]==='/'||a[j]==="("||a[j]===')'){
                found=1
                break
            }
        }
        if(found===1){
            if(typeof a[i]==='number'){
                if(dau==='+'){
                    result+=a[i];
                }
                else if(dau==='-'){
                    result-=a[i]
                } 
                else if(dau==='/'){
                    result/=a[i]
                }
                else if(dau==='*'){
                    result*=a[i]
                }
            }
            else if(a[i]==='+'||a[i]==='-'||a[i]==='/'||a[i]==='*'){
                dau=a[i]
            }
        }
        else if(found===0){
            if (typeof a[i] === 'number') {
                result = result * 10 + a[i];
            }
        }
    }
    return result
}