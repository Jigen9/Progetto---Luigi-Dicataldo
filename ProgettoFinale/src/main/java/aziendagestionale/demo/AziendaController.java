package aziendagestionale.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/*@Controller indica che questa classe è un controller 
che gestisce le richieste HTTP e le risposte HTTP. 
è responsabile di ricevere le richieste HTTP dal client, 
processarle e fornire una risposta appropriata. */
@Controller
public class AziendaController {
 
/*@Autowired inietta le dipendenze delle classi DipendenteService
OrariLavoratoriService all'interno della classe DipendenteController. */
    @Autowired
    private DipendenteService service;
    @Autowired
    private OrariLavoratoriService service2;


    /*Questo metodo gestisce la richiesta HTTP per la home page del sito web 
    e recupera l'elenco dei dipendenti e degli orari lavoratori da visualizzare nella vista. */
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Dipendente> listaDipendenti = service.listAll(keyword);
        List<OrariLavoratori> orariLavoratori = service2.listAll();
        //qui le liste vengono aggiunte all'oggetto "model" utilizzando i metodi addAttribute() 
        model.addAttribute("orariLavoratori", orariLavoratori);
        model.addAttribute("listaDipendenti", listaDipendenti);  

        return "index";
    }

    /*In questo metodo gestisco la richiesta HTTP per la pagina di creazione di un nuovo dipendente 
    Poi crea un nuovo oggetto Dipendente, inizialmente con uno stipendio calcolato in base al livello. */
    @RequestMapping("/new_dip")
    public String showNewDipendentePage(Model model) {
        Dipendente dipendente = new Dipendente();
        dipendente.calcolastipendio(); //alla creazione ha lo stipendio senza bonus
        model.addAttribute("dipendente", dipendente); //aggiungo all'oggetto model
         
        return "new_dip"; //restituisce la vista new_dip
    }

    //con @RequestMapping indico che questo metodo gestisce le richieste HTTP che vengono inviate all'URL "/save"
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    /*con @ModelAttribute indico che i dati del modulo inviato dall'utente devono essere utilizzati per creare un oggetto Dipendente.*/
   public String saveDipendente(@ModelAttribute("dipendente") Dipendente dipendente) {
    dipendente.calcolastipendio();
    service.save(dipendente);
    List<OrariLavoratori> orariLavoratori = service2.getOrarioSettimanaleByDipendenti(dipendente.getIdDipendente());
    /*controllo se l'orario esiste già, se non esiste ne crea uno nuovo altrimenti*/
    if(orariLavoratori.isEmpty()){

        String [] giorniSettimana = {"Lunedì","Martedì","Mercoledì","Giovedì","Venerdì"};
        for(int j=0; j<4; j++ ){ //4 perchè gestisco 4 settimane 
            for (int i=0; i<5; i++) {
                OrariLavoratori orario = new OrariLavoratori();
                orario.setGiornoSettimana(giorniSettimana[i]); //setto il giorno della settimana prima a lun poi mar e così via
                orario.setIdDipendente(dipendente.getIdDipendente());
                orario.setOrario1(orario.calcolaOrari()); //per scopo dimostrativo e per testare il funzionamento ho generato 
                orario.setOrario2(orario.calcolaOrari()); //in modo casuale gli orari (0 NO LAVORO 4 SI LAVORO) di tutta la sett.
                orario.setOrario3(orario.calcolaOrari()); //in pratica questo metodo si dovrebbe togliere perchè gli orari vanno programmati(inseriti)
                                                          //settimanalmente.
                service2.save(orario);  //salvo i dati
            }
        }
    }
   
    return "redirect:/";
   }


   @RequestMapping("/edit/{id}")
   public ModelAndView showEditDipendentiPage(@PathVariable(name = "id") int id) {
       ModelAndView mav = new ModelAndView("edit_dipendenti"); //creo un oggetto ModelAndView che indica quale pagina HTML deve essere visualizzata ("edit_dipendenti.html")
       Dipendente dipendente = service.get(id); //recupero l'id del dipendente
       dipendente.calcolastipendio(); //calcolo stipendio base
       mav.addObject("dipendente", dipendente); //passo l'oggetto alla view
    
       return mav; //restituisce l'oggetto model and view
   }
   
   //gestitso la cancellazione dei dipendenti
   @RequestMapping("/delete/{id}")
   public String deleteDipendenti(@PathVariable(name = "id") int id) { //cancello in base all'id presente nell'url
       service.delete(id);

       return "redirect:/";       
   }



   //modifico l'orario tramite idOrario e idDipendente per associare l'uno a l'altro
   @RequestMapping("/orario/{idOrario}")
   public ModelAndView viewModificaPage(@PathVariable(name = "idOrario") int idOrario) {
       OrariLavoratori orario = service2.get(idOrario);
       ModelAndView mav = new ModelAndView("orario");
       mav.addObject("orario", orario);
       Dipendente dipendente = service.get(orario.getIdDipendente()); /*richiamo il metodo "get" del service2 per avere l'oggetto OrariLavoratori corrispondente all'ID */
       mav.addObject("dipendente", dipendente);
       return mav;
   }

   //metodo per salvare l'orario quando richiamato
   @RequestMapping(value = "/salvaOrario", method = RequestMethod.POST)
   public String salvaOrario(@ModelAttribute("orario") OrariLavoratori orario) {
       service2.save(orario);
       return "redirect:/";
   }

   //calcolo gli straordinari di un utente specifico e tutti i bonus tramite l'id del dipendente
   @RequestMapping("/straordinari/{id}")
    public ModelAndView straordinari(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("straordinari");
        Dipendente dipendente = service.get(id);
        /*prendo gli orari settimanali associati al dipendente X tramite il servizio "service2" e li salvo in una lista chiamata "listaOrari" */
        List<OrariLavoratori> listaOrari = service2.getOrarioSettimanaleByDipendenti(dipendente.getIdDipendente());
        mav.addObject ("listaOrari", listaOrari);
        mav.addObject("dipendenti", dipendente);
        int somma=0;
        int notturne=0;
        int straordinari=0;

        //calcolo orario notturno
        for (OrariLavoratori orarioSettimana : listaOrari) {
            int orario1= orarioSettimana.getOrario1();
            int orario2= orarioSettimana.getOrario2();
            int orario3= orarioSettimana.getOrario3();
            somma+= orario1 + orario2 + orario3;
            if(orario3==4) {
                notturne+=2;
            }
        }
        if(somma>140) { //totale ore
            straordinari=somma-140; //calcolo straordinari se l'ammonte ore supera 140
        }
        int mesiLavorati=dipendente.generaMesi();
        float ferie= (float) ((2.5*mesiLavorati));
        float scatti=0;
        if(dipendente.getAnniServizio()>5) {
            int anni=(int)Math.floor(dipendente.getAnniServizio()/5);
            scatti=(float) (anni*0.5);
        }
        ferie+=scatti;
        //dopo aver effettuato i calcoli passo tutto al mav
        dipendente.setStipendio(dipendente.calcolastipendio(notturne, straordinari));
        mav.addObject("somma",somma);
        mav.addObject("notturne",notturne);
        mav.addObject("straordinari", straordinari);
        mav.addObject("mesiLavorati",mesiLavorati);
        mav.addObject("ferie",ferie);
        return mav;
    }
}