
const clockTitle = document.querySelector(".js-clock")




let tommorow;
tommorow = new Date();
tommorow.setHours(0);
tommorow.setMinutes(0);
tommorow.setSeconds(0);
tommorow.setDate(tommorow.getDate() + 1);

function getDday() {


  const dd = tommorow.getTime()

  const now = new Date()
  const nn = now.getTime()

  const numForDivide = 1000*60*60*24
  const dif = (dd-nn)/numForDivide
  
  const day = String(Math.floor(dif)).padStart(2, "0")
  const hour = String(Math.floor((dif-day)*24)).padStart(2, "0")
  const min = String(Math.floor(((dif-day)*24-hour)*60)).padStart(2, "0")
  const sec = String(Math.floor((((dif-day)*24-hour)*60-min)*60)).padStart(2, "0")
  console.log(hour, min, sec)



}


getDday();
setInterval(getDday, 1000);