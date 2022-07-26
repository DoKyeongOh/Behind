const previousImage = document.querySelector("#before-picture span");
const nowImage = document.querySelector("#now-picture span");
const nextImage = document.querySelector("#after-picture span");

previousImage.addEventListener("click", () => new function() {
    changeImage(true);
});
nextImage.addEventListener("click", () => new function() {
    changeImage(false);
});

function changeImage(isNext) {
    console.log("함수 실행됨.");

    let addNum = -1;
    if (isNext) addNum = 1;

    const preImgNo = getImgNo(previousImage.src)+addNum;
    const nowImgNo = getImgNo(nowImage.src)+addNum;
    const nextImgNo = getImgNo(nextImage.src)+addNum;

    console.log(previousImage.src);

    previousImage.src = getImgPath(preImgNo);
    nowImage.src = getImgPath(nowImgNo);
    nextImage.src = getImgPath(nextImgNo);
}

function getImgNo(imgString){
// ../../pictures/origin/1.jpeg
    return imgString.substring(
        imgString.length-5,
        imgString.length-4
    )
}

function getImgPath(imgNo){
    return "../../pictures/origin/" + imgNo + ".jpeg";
}