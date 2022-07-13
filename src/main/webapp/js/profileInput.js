const btnAge = document.getElementById("btn-age-dropdown");
let ageItem;
for (i = 2022 ; i>1950 ; i--){
    ageItem = document.createElement("option");
    ageItem.innerText=i;
    btnAge.appendChild(ageItem);
}

const btnCity = document.getElementById("btn-city-dropdown");
function addCity(cityName){
    const cityItem = document.createElement("option");;
    cityItem.innerText = cityName; 
    btnCity.appendChild(cityItem);
}

function addCitys(){
    addCity("서울특별시");
    addCity("부산광역시");
    addCity("대구광역시");
    addCity("인천광역시");
    addCity("광주광역시");
    addCity("대전광역시");
    addCity("울산광역시");
    addCity("세종특별자치시");
    addCity("경기도");
    addCity("강원도");
    addCity("충청북도");
    addCity("충청남도");
    addCity("전라북도");
    addCity("전라남도");
    addCity("경상북도");
    addCity("경상남도");
    addCity("제주특별자치도");
}

addCitys();

const labelMan = document.getElementById("label-man");
labelMan.addEventListener("click", () => new function(){
    const radioMan = document.getElementById("radio-man");
    radioMan.checked = true;
});

const labelWoman = document.getElementById("label-woman");
labelWoman.addEventListener("click", () => new function(){
    const radioMan = document.getElementById("radio-woman");
    radioMan.checked = true;
});






















