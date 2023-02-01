const limit = document.querySelector("#numRange");
const pick = document.querySelector("#usersPick");
const playBtn = document.querySelector("p button");
const chosenNumber = document.getElementById("chosenNumber")
const result = document.getElementById("result")



function play(event){
  event.preventDefault();
  const randomNumber = Math.ceil(Math.random() * limit.value);

  if (Number(limit.value) <= 0) {
    limit.value = ""
    alert("Please enter positive number above zero");

  
  } 
  else if(Number(pick.value=="")){
    alert("Plese choose your number")
  }
  else if(Number(limit.value)<Number(pick.value)){
  
    alert(`Plese enter number between 0 and ${limit.value}`)


  }
  
  else {
  
    console.log(randomNumber)
    if(Number(pick.value) == Number(randomNumber)){
      chosenNumber.innerHTML = `You chose: ${pick.value}, the machine chose: ${randomNumber}.` 
      result.innerHTML="You won"
      console.log("pick : "+ pick.value)
    }
    else {
      console.log("pick : "+ pick.value)
      chosenNumber.innerHTML = `You chose: ${pick.value}, the machine chose: ${randomNumber}.` 
      result.innerHTML="You lost"
    }
  }
}

playBtn.addEventListener("click", play);
