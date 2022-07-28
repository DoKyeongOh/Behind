const btnExpandReply = document.getElementById("btn-expand-reply");

btnExpandReply.addEventListener("click", () => new function () {
    console.log(btnExpandReply.innerText);

    if (btnExpandReply.innerText === "펼치기"){
        btnExpandReply.innerText = "접기";
    } else {
        btnExpandReply.innerText = "펼치기";
    }
})

