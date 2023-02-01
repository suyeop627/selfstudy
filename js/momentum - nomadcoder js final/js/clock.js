const clock = document.querySelector("h2#clock");

function getClock() {
  const date = new Date();
  const hours = String(date.getHours()).padStart(2,"0");
  const minutes = String(date.getMinutes()).padStart(2,"0");
  const seconds = String(date.getSeconds()).padStart(2,"0");
  clock.innerText = `${hours}:${minutes}:${seconds}`;
}

  getClock();
  setInterval(getClock, 1000);

  const timeLeft = document.getElementById("timeLeft")

  let tommorow;
  tommorow = new Date();
  tommorow.setHours(0);
  tommorow.setMinutes(0);
  tommorow.setSeconds(0);
  tommorow.setDate(tommorow.getDate() + 1);

  function getTimeLeft(){
 
    const dd = tommorow.getTime()

    const now = new Date()
    const nn = now.getTime()
  
    const numForDivide = 1000*60*60*24
    const dif = (dd-nn)/numForDivide
    
    const day = String(Math.floor(dif)).padStart(2, "0")
    const hour = String(Math.floor((dif-day)*24)).padStart(2, "0")
    const min = String(Math.floor(((dif-day)*24-hour)*60)).padStart(2, "0")
    const sec = String(Math.floor((((dif-day)*24-hour)*60-min)*60)).padStart(2, "0")

  
  
  timeLeft.innerHTML =`${String(hour).padStart(2,'0')}h ${String(min).padStart(2,'0')}m ${String(sec).padStart(2,'0')}s left today` ;
  }
  
  getTimeLeft();
  setInterval(getTimeLeft, 1000);
