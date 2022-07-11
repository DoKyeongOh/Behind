
const btnCheckSameId = document.getElementById("btn-same-check");

// 아이디 중복확인
btnCheckSameId.addEventListener("click", (event) => new function(event){
    const inputId = document.getElementById("input-account-id").value;
    if (inputId === "") {
        alert("아이디를 입력해주세요!");
        return;
    } 
    
    const xhr = getXmlHttpRequest('post', '/idUsage', {id:inputId});

    xhr.onreadystatechange = function(event) {
        const {target} = event;
        if (xhr.readyState === xhr.DONE) {
            if (xhr.status === 200 || xhr.status === 201) {
                const isUsable = JSON.parse(target.response).isUsable; 
                const labelIsUsableId = document.getElementById("labelIsUsableId");
                if (isUsable) {
                    labelIsUsableId.innerText = "사용할 수 있는 아이디입니다.";
                } else {
                    labelIsUsableId.innerText = "사용할 수 없는 아이디입니다.";
                }
            } 
        }
    };
});

// 비밀번호, 비밀번호 확인 체크
const inputPw = document.getElementById("input-account-pw");
const inputPwCheck = document.getElementById("input-account-pw-check");

inputPw.addEventListener("input", (event) => checkPw(event));
inputPwCheck.addEventListener("input", (event) => checkPw(event));

function checkPw(event){
    const input = inputPw.value;
    const inputCheck = inputPwCheck.value;
    const labelIsSame = document.getElementById("labelIsSamePw");
    if (input !== inputCheck) {
        labelIsSame.innerText="비밀번호와 비밀번호 확인 값이 다릅니다.";
    } else {
        labelIsSame.innerText="비밀번호와 비밀번호 확인 값이 일치합니다.";
    }
}

// 비동기 통신으로 중복 아이디 있는지 체크
// 파라미터 보내는건 post방식에서만 됨.
function getXmlHttpRequest(method, url, postData){
    const xhr = new XMLHttpRequest();

    xhr.open(method.toUpperCase(), url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(JSON.stringify(postData));
    return xhr;
}

