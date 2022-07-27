
const previousImage = document.querySelector("#before-picture img");
const nowImage = document.querySelector("#now-picture img");
const nextImage = document.querySelector("#after-picture img");

const previousButton = document.getElementById("btn-previous-img");
const nextImageButton = document.getElementById("btn-next-img");

const imgCount = 10;
const imgIndexKey = "imgIndexArr";

window.onload = new function(){
    const arr = [1,2,3];
    localStorage.setItem(imgIndexKey, arr.toString());
}


function modifyImgIndex(isAdd){
    const arr = localStorage.getItem(imgIndexKey).split(",");
    for (let i=0 ; i<3 ; i++) {
        if (isAdd) {
            arr[i]++;
            if (arr[i] >= imgCount) arr[i] = 0;
        } else {
            arr[i]--;
            if (arr[i] <= 1) arr[i] = imgCount;
        }
    }

    console.log(arr);
    const submittedImg = document.getElementById("selected-img-no");
    submittedImg.value = arr[1] + 1;

    localStorage.setItem(imgIndexKey, arr.toString());
    return arr;
}

function getImgs(){
    const imgArray = [];
    for (let i=1 ; i<=imgCount ; i++) {
        let img = document.getElementById("img-"+i);
        imgArray.push(img);
    }
    return imgArray;
}

previousButton.addEventListener("click", () => new function(){
    const imgArray = getImgs();
    const imgIndexArr = modifyImgIndex(false);

    previousImage.src = imgArray[imgIndexArr[0]].src;
    nowImage.src = imgArray[imgIndexArr[1]].src;
    nextImage.src = imgArray[imgIndexArr[2]].src;

});

nextImageButton.addEventListener("click", () => new function(){
    const imgArray = getImgs();
    const imgIndexArr = modifyImgIndex(true);

    previousImage.src = imgArray[imgIndexArr[0]].src;
    nowImage.src = imgArray[imgIndexArr[1]].src;
    nextImage.src = imgArray[imgIndexArr[2]].src;
});
