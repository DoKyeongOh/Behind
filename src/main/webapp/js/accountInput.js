
const btnCheckSameId = document.getElementById("btn-same-check");

// 아이디 중복확인
btnCheckSameId.addEventListener("click", (event) => new function(event){
    const inputId = document.getElementById("input-account-id").value;
    if (inputId === "") {
        alert("아이디를 입력해주세요!");
    }
    asyncRequest();
});

// 비밀번호, 비밀번호 확인 체크
const inputPw = document.getElementById("input-account-pw");
const inputPwCheck = document.getElementById("input-account-pw-check");

inputPw.addEventListener("input", (event) => checkPw(event));
inputPwCheck.addEventListener("input", (event) => checkPw(event));

function checkPw(event){
    const input = inputPw.value;
    const inputCheck = inputPwCheck.value;

    const labelIsSame = document.getElementById("labelIsSame");
    if (input !== inputCheck) {
        const labelIsSame = document.getElementById("labelIsSame");
        labelIsSame.innerText="비밀번호와 비밀번호 확인 값이 다릅니다.";
    } else {
        labelIsSame.innerText="비밀번호와 비밀번호 확인 값이 일치합니다.";
    }
}

function asyncRequest(){
    const xhr = new XMLHttpRequest();
    const method = "POST";
    const url = "http://localhost:8080/idSameTest";

    xhr.open(method, url);

    xhr.onreadystatechange = function (event) {
        const {target} = event;

        if (target.readyState === XMLHttpRequest.DONE) {
            const {status} = event;

            if (status === 0 || (status >= 200 && status < 40)){
                console.log(status);
            } else {
                console.log(status);
                console.log("잘안됨");
            }
        }
    };

    xhr.send(null);
}