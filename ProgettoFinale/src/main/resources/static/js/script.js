function goToDipendenti() { // Viene richiamata quando si preme il pulsante Aggiungi prodotto
     window.location.replace("new_dip");
}

function goToHome() {    // Viene richiamata quando si preme il pulsante Home
     window.location.replace("/");
}

function goToOrari(){
     window.location.replace("orario");
}


//questo script mi stampa un Hr ogni 5 giorni visualizzati nella modale in modo da suddividere le settimane
//con tabella 1 settimana 1, tabella2 settimana 2 e così via. Sono 4 in tutto.
let contatoreSettimane = 2;
let panel = document.querySelectorAll("#PannelloContenitore");
for(let i = 0; i < panel.length; i++){
  if((i+1) % 5 === 0){
    panel[i].insertAdjacentHTML("afterend", "<hr/>");
    contatoreSettimane++;
  }
}




 
   
   
//con questo script identifico ogni "mostra oggetti" con un id unico
//l'id è incrociato tra id dell'orario di quel dipendente e il dipendente
document.querySelectorAll(".mostra-oggetti").forEach(function (e) {
          e.onclick = function () {
               
              var id = e.getAttribute("id").split("-")[2];

              if(document.getElementById("orari-" + id).style.display == "block")
                document.getElementById("orari-" + id).style.display = "none";
              else
                document.getElementById("orari-" + id).style.display = "block";
        
          };
        });

document.querySelectorAll(".mostra-orario").forEach(function (e) {
          e.onclick = function () {
               
              var id = e.getAttribute("id").split("-")[2];
              
              document.getElementById("orario-btn-" + id).href ="/orario/" + id;
          };
        });

