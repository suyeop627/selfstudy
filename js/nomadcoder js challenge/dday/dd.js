
const dday = new Date('2022-12-25 00:00:00');
const dd = dday.getDate()
const dhour = dday.getHours()
const dmin = dday.getMinutes()
const dsec = dday.getSeconds()


const now = new Date();
const nd = now.getDate()
const nhours = now.getHours()
const nmin = now.getMinutes()
const nsec = now.getSeconds()

const time1 = now.getTime()
console.log(time1*60)



console.log(`${dd-nd} : ${dhour-nhours} : ${dmin-nmin} : ${dsec-nsec}`)





