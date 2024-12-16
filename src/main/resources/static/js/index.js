function hideForm1() {
    var hide1 = document.querySelector('.form1')//.style.display = 'none';
   var hide2 = document.querySelector('.form2');

    hide1.classList.add("hide");
    hide2.classList.remove("hide");
  }
  
  function hideForm2() {
    var hide1 = document.querySelector('.form1');
    var hide2 = document.querySelector('.form2');

    hide1.classList.remove("hide");
    hide2.classList.add("hide");
  }

function hidefriend() {
   // var hide = document.querySelector('.hidefriend');
   // hide.classList.toggle("hidefriend-left");
    var hiddenMessage = document.querySelector(".hidefriend");
    hiddenMessage.classList.remove("hide");
    var hiddenMessage1 = document.querySelector(".hidecongrat");
    hiddenMessage1.classList.add("hide");
}
function hideCongrat() {
    //var hide = document.querySelector('.hidecongrat');

//hide1.classList.remove("animated-form");
    //hide.classList.toggle("hidefriend-right");
    var hiddenMessage = document.querySelector(".hidefriend");
    hiddenMessage.classList.add("hide");
    var hiddenMessage1 = document.querySelector(".hidecongrat");
    hiddenMessage1.classList.remove("hide");
}

/*
friend
congratilation*/