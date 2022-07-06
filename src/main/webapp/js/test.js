

const button = document.getElementById("testButton");
console.log(button.innerText);

button.addEventListener("click", doGetRequest);


function doGetRequest(){
    const xhr = new XMLHttpRequest();
    const method = "GET";
    const url = "http://localhost:8080/";
    
    xhr.open(method, url);
    
    xhr.onreadystatechange = function (event) {
        const target = event;

    if (target.readyState === XMLHttpRequest.DONE) {
        const status = target;

        if (status === 0 || (status >= 200 && status < 400)) {
            // 요청이 정상적으로 처리 된 경우
        } else {
            // 에러가 발생한 경우
        }
    }
    }
    
    xhr.send();
}