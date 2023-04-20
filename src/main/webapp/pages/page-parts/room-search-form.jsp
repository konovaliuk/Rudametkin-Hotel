<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div id="search-rooms-container">
  <div id="search-rooms-title">APARTMENTS SEARCH</div>
  <form action="${pageContext.request.contextPath}/search" id="fields-container" method="get">
      <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/person-logo.png" alt="PersonPic" class="form-icon search-form-item">
        <button type="button" class="field-counter-button search-form-item" onclick="decreaseCounterValue(this.nextElementSibling);" disabled>-</button>
        <span id="persons" class="counter search-form-item">1</span>
        <button type="button" class="field-counter-button right-indent search-form-item" onclick="increaseCounterValue(this.previousElementSibling);">+</button>
        <input id="input-persons" type="hidden" name="persons" value="1" />
      </span>
    <span>
          <img src="${pageContext.request.contextPath}/resources/pictures/bed-logo.png" alt="BedPic" class="form-icon search-form-item">
          <button type="button" class="field-counter-button search-form-item" onclick="decreaseCounterValue(this.nextElementSibling);" disabled>-</button>
          <span id="beds" class="counter search-form-item">1</span>
          <button type="button" class="field-counter-button right-indent search-form-item" onclick="increaseCounterValue(this.previousElementSibling);">+</button>
          <input id="input-beds" type="number" name="beds" value="1" hidden />
      </span>
    <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/calendar-logo.png" alt="BedPic" class="form-icon search-form-item">
        <input name="arrival-date" class="form-data-input search-form-item" id="input-arrival-date" type="date" onchange="updateMinDepartureDate();">
        <span class="search-form-item" style="margin: 0 3px; font-size: 20px;">-</span>
        <input name="departure-date" class="form-data-input search-form-item right-indent" id="input-departure-date" type="date" onchange="updateMinDepartureDate();">
      </span>
    <span>
        <img src="${pageContext.request.contextPath}/resources/pictures/coins-logo.png" alt="CoinsPic" class="form-icon search-form-item">
        <select name="room-type" id="input-room-type" class="search-form-item right-indent" onchange="updateFormStringAndInputs();">
          <option value="Any" selected>Any</option>
          <option value="Economy">Economy</option>
          <option value="Standard">Standard</option>
          <option value="Deluxe">Deluxe</option>
          <option value="Suite">Suite</option>
          <option value="Presidential">Presidential</option>
        </select>
      </span>
    <input type="text" id="form-result-info" class="search-form-item right-indent" disabled />
    <button type="submit" class="submit-search search-form-item">
      <div class="submit-search-image"></div>
    </button>
  </form>
</div>