const quotes = [
  {
    quote:  "The purpose of our lives is to be happy.",
    author:  "Dalai Lama"
  },
  {
    quote:  "Life is what happens when you're busy making other plans." ,
    author:  "John Lennon"
  },  
  {
    quote: "Get busy living or get busy dying." ,
    author: " Stephen King"
  },  
  {
    quote:  " The way to get started is to quit talking and begin doing.",
    author: "Walt Disney"
  },  
  {
    quote:  "But I know, somehow, that only when it is dark enough can you see the stars.",
    author: "Martin Luther King, Jr"
  },  
  {
    quote: "Habit is second nature.",
    author: "Michel de Montaigne"
  },  
  {
    quote: "The unexamined life is not worth living.",
    author: "Socrates"
  },  
  {
    quote:  "You never really learn much from hearing yourself speak." ,
    author: "George Clooney"
  },  
  {
    quote:  "Life would be tragic if it weren’t funny.",
    author: "Stephen Hawking"
  },
  {
    quote: "Life’s tragedy is that we get old too soon and wise too late.",
    author: "Benjamin Franklin"
  },
];

const quote = document.querySelector("#quote span:first-child");
const author = document.querySelector("#quote span:last-child");
const todaysQuote = quotes[Math.floor(Math.random() * quotes.length)];

quote.innerText = `"${todaysQuote.quote}"`;
author.innerText = ` - ${todaysQuote.author}`;
