//const API_KEY = weatherAPI.apikey;
const API_KEY = "ee6a3dab36c3bd679253c120ff8d5de6";


function onGeoOK(position) {
  const lat = position.coords.latitude;
  const lon = position.coords.longitude;
  const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric`;
  fetch(url);

  fetch(url)
    .then(response => response.json())
    .then(data => {
      const city = document.querySelector("#weather span:first-child")
      const weather = document.querySelector("#weather span:last-child")
      city.innerText = `${data.name}`;
      weather.innerText = `${data.main.temp}℃, ${data.weather[0].main} `;
    });
}

function onGeoError() {
  alert("Can't find you. No weather for you. ")
}

navigator.geolocation.getCurrentPosition(onGeoOK, onGeoError)
