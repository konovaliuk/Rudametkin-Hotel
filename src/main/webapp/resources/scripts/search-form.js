let todayDay = new Date();
let dd = todayDay.getDate();
let mm = todayDay.getMonth() + 1;
let yyyy = todayDay.getFullYear();

if (dd < 10) {
    dd = '0' + dd;
}

if (mm < 10) {
    mm = '0' + mm;
}

let todayStr = yyyy + '-' + mm + '-' + dd;
document.getElementById('arrival-date').valueAsDate = todayDay;
document.getElementById('arrival-date').min = todayStr;

document.getElementById('departure-date').valueAsDate = todayDay;
document.getElementById('departure-date').min = todayStr;

updateFormStringAndInputs();
function increaseCounterValue(counterSPAN) {
    let input = counterSPAN.nextElementSibling.nextElementSibling;
    input.value = (Number(input.value) + 1).toString();
    counterSPAN.innerHTML = input.value;
    if(counterSPAN.previousElementSibling.disabled)
        counterSPAN.previousElementSibling.disabled = false;
    updateFormStringAndInputs();
}

function decreaseCounterValue(counterSPAN) {
    let input = counterSPAN.nextElementSibling.nextElementSibling;
    input.value = (Number(input.value) - 1).toString();
    counterSPAN.innerHTML = input.value;
    if(input.value === "1")
        counterSPAN.previousElementSibling.disabled = true;
    updateFormStringAndInputs();
}

function updateMinDepartureDate() {
    let arrivalDate = document.getElementById('arrival-date').valueAsDate;
    let departureDate = document.getElementById('departure-date').valueAsDate;
    if(departureDate < arrivalDate)
        document.getElementById('departure-date').valueAsDate = arrivalDate;
    updateFormStringAndInputs();
}

function updateFormStringAndInputs() {
    let personsAmount = document.getElementById("persons-amount").innerHTML.trim();
    let bedsAmount = document.getElementById("beds-amount").innerHTML.trim();
    let roomType = document.getElementById("room-type-select").value;
    let arrivalDateString = formatInputData(document.getElementById("arrival-date").value);
    let departureDateString = formatInputData(document.getElementById("departure-date").value);
    let resultString =  `${personsAmount} person(s), ${bedsAmount} bed(s), ${roomType}, ${arrivalDateString}-${departureDateString}`;
    document.getElementById("form-result-info").value = resultString;
}

function formatInputData(stringData) {
    let yyyy = stringData.substring(0, 4);
    let mm = stringData.substring(5, 7);
    let dd = stringData.substring(8, 10);
    return `${dd}.${mm}.${yyyy}`;
}