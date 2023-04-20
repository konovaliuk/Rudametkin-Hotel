window.addEventListener("load", (event) => {
    initDefaultDates();
    let params = getFormDataFromGetParams();
    if(params != null)
       setFormDataFromParams(params);

    updateFormInfoElement();
    setFormInputsCondition();
})

function initDefaultDates() {
    let todayDay = new Date();
    let todayStr = getDateString(todayDay);

    document.getElementById('input-arrival-date').valueAsDate = todayDay;
    document.getElementById('input-arrival-date').min = todayStr;

    document.getElementById('input-departure-date').valueAsDate = todayDay;
    document.getElementById('input-departure-date').min = todayStr;
}

function increaseCounterValue(counterSPAN) {
    let input = counterSPAN.nextElementSibling.nextElementSibling;
    input.value = (Number(input.value) + 1).toString();
    counterSPAN.innerHTML = input.value;
    if(counterSPAN.previousElementSibling.disabled)
        counterSPAN.previousElementSibling.disabled = false;
    updateFormInfoElement();
}

function decreaseCounterValue(counterSPAN) {
    let input = counterSPAN.nextElementSibling.nextElementSibling;
    input.value = (Number(input.value) - 1).toString();
    counterSPAN.innerHTML = input.value;
    if(input.value === "1")
        counterSPAN.previousElementSibling.disabled = true;
    updateFormInfoElement();
}

function updateMinDepartureDate() {
    let arrivalDate = document.getElementById('input-arrival-date').valueAsDate;
    let departureDate = document.getElementById('input-departure-date').valueAsDate;
    if(departureDate < arrivalDate)
        document.getElementById('input-departure-date').valueAsDate = arrivalDate;
    updateFormInfoElement();
}

function updateFormInfoElement() {
    let personsAmount = document.getElementById("persons").innerHTML;
    let bedsAmount = document.getElementById("beds").innerHTML;
    let roomType = document.getElementById("input-room-type").value;
    let arrivalDateString = formatInputData(document.getElementById("input-arrival-date").value);
    let departureDateString = formatInputData(document.getElementById("input-departure-date").value);
    let resultString =  `${personsAmount} person(s), ${bedsAmount} bed(s), ${roomType}, ${arrivalDateString}-${departureDateString}`;
    document.getElementById("form-result-info").value = resultString;
}

function formatInputData(stringData) {
    let yyyy = stringData.substring(0, 4);
    let mm = stringData.substring(5, 7);
    let dd = stringData.substring(8, 10);
    return `${dd}.${mm}.${yyyy}`;
}


function setFormInputsCondition() {
    let persAmountView = document.querySelector("#persons");
    persAmountView.previousElementSibling.disabled = Number(persAmountView.innerHTML) === 1;

    let bedsAmountView = document.querySelector("#beds");
    bedsAmountView.previousElementSibling.disabled = Number(bedsAmountView.innerHTML) === 1;

    updateMinDepartureDate();
}

function getFormDataFromGetParams() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    const keys = ["persons", "beds", "arrival-date", "departure-date", "room-type"];
    const params = new Map();

    for(let key of keys)
        params.set(key, urlParams.get(key))

    return params;
}

function setFormDataFromParams(params) {
   params.forEach((value, key) => {
       if(value == null)
           return;

       let input = document.querySelector(`#input-${key}`);
       console.log(key, value,`#input-${key}`);
       input.value = value;

       if(["persons", "beds"].includes(key)) {
           let span = document.querySelector(`#${key}`);
           span.innerHTML = value;
       }
   })
}

function getDateString(date) {
    let dd = date.getDate();
    let mm = date.getMonth() + 1;
    let yyyy = date.getFullYear();

    if (dd < 10)
        dd = '0' + dd;
    if (mm < 10)
        mm = '0' + mm;

    return yyyy + '-' + mm + '-' + dd;
}