let img = document.getElementsByTagName("img")
const img_wrap = document.getElementById("img_wrap")
const countPrint = document.getElementById("count")
let timeout;
let count; ;

function start() {
  count=0;
  countPrint.innerHTML=`시도횟수 : ${count}`
  img_wrap.innerHTML = ""
  clearTimeout(timeout)

  //배치
  const arrImg = ["../image/1.png", "../image/1.png", "../image/2.png", "../image/2.png", "../image/3.png",
    "../image/3.png", "../image/4.png", "../image/4.png", "../image/5.png", "../image/5.png",
    "../image/6.png", "../image/6.png", "../image/7.png", "../image/7.png", "../image/8.png", "../image/8.png"]


  function shuffle() {

    for (let i = 1; i <= 16; i++) {
      const randomIndex = Math.floor(Math.random() * arrImg.length)
      const wrap = document.getElementById("img_wrap")

      const chosenImage = arrImg[randomIndex];

      const imgToPrint = document.createElement("img")
      imgToPrint.src = `img/${chosenImage}`
      imgToPrint.setAttribute("onclick", "checkCard(this)");
      imgToPrint.className = "open"
      imgToPrint.style.disabled = true

      wrap.appendChild(imgToPrint)
      arrImg.splice(randomIndex, 1)

    }
  }
  shuffle()


  timeout = setTimeout(function () {
    img = document.getElementsByTagName("img")

    for (i = 0; i < img.length; i++) {
      img[i].className = "hidden"
    }
  }, 3000)


}


let checkboolean=[];

//클릭->카드오픈
let firstCard;
let secondCard;

function checkCard(element) {
  count++
  if (firstCard == undefined) {
    firstCard = element
    firstCard.classList.toggle("open")


  }
  else if (firstCard != undefined && secondCard == undefined) {
    secondCard = element
    secondCard.classList.toggle("open")

    //같은카드 두번 눌렀을 때
    if (firstCard == secondCard) {
      alert("다른 카드를 선택해주세요.")
      firstCard.classList.remove("open")
      firstCard = undefined
      secondCard = undefined

      //두 장이 서로 다를때
    } else {
      if (firstCard.src != secondCard.src) {
        setTimeout(function () {
          firstCard.className = "hidden"
          secondCard.className = "hidden"
          firstCard = undefined
          secondCard = undefined
        }, 500)

      }
      //두장이 같을 때
      else if (firstCard.src == secondCard.src) {

        secondCard.classList = "open"
        firstCard = undefined
        secondCard = undefined
      //클리어 확인
        for (j = 0; j < 16; j++) {
          let a = img[j].classList.contains("open")

          if(a==true){
            checkboolean.push(a)
            if(checkboolean.length == 16){
             
              setTimeout(function () {
                let clear = confirm(`${count}번 만에 성공했어요! 다시 한번 해보시겠어요?`)
           
                if(clear == true){
                  start()
                }else{
                  alert("플레이해주셔서 감사합니다.")
                }
              }, 500)
              
          }
        }
          else if(a==false){
            a=""
            checkboolean=[];
            return;

                }
                
              }
          }


        }

      }
      countPrint.innerHTML =`시도횟수 : ${count}`

    }



//재시작
function restart() {
  img_wrap.innerHTML = ""
  count = 0;
  start()
 
}

//시도횟수


