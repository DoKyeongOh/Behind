const btnMoreComment = document.getElementById("btn-more-comment");

btnMoreComment.addEventListener("click", showMoreComment);

window.onload = (() => {
    console.log('onload');
    localStorage.setItem("commentCount", "0");
});

function showMoreComment(){
    const lcValue = localStorage.getItem("commentCount");
    console.log(lcValue);
    let commentCount = parseInt(lcValue);
    if (commentCount === 0) {
        commentCount = 5;
    } else {
        commentCount = commentCount + 5;
    }

    localStorage.setItem("commentCount", JSON.stringify(commentCount));

    const ul = document.getElementById("ul-comments");
    const lis = ul.getElementsByTagName("li");

    for (let i = lis.length - 1; i >= 0; i--) {
        if (i>=commentCount) {
            lis[i].style.display = "none";
        } else {
            lis[i].style.display = "";
        }
    }

    console.log("test");
}