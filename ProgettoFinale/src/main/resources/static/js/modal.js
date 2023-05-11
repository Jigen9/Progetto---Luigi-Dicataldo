// Bottone che apre la modale
var btn = document.querySelectorAll(".panel");

// span che chiude la modale
var span = document.querySelectorAll(".close");

var modal;
let elementToAppend;


btn.forEach(function(e){

  e.onclick = function() {
	modal =  document.getElementById(e.getAttribute('value')); //al click rendo visibile la modale
	  
  modal.style.display = "block";
      
  }
});

// la modale si chiude al click sulla X 
span.forEach(function(e) {
	e.onclick = function() {
   		modal.style.display = "none";
	}
});


// la modale si chiude quando si clicca al di fuori di essa 
window.onclick = function(event) {
  if (event.target == modal) {
    if(elementToAppend != null)
        modal.removeChild(elementToAppend);
     modal.style.display = "none";
  }
}


btn.onclick = function() {
  modal.style.display = "block";

} //display a block



