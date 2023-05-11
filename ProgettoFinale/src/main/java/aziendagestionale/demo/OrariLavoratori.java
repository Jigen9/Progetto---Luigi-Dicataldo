package aziendagestionale.demo;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

 /*con questa annotazione @Entity indico che OrariLavoratori è un'entità presente nel DB 
(su mySql c'è la tabella "OrariLavoratori")*/
@Entity
public class OrariLavoratori {
    private Long idOrario; //contiene idOrario
    private Long idDipendente; //contiene idDipendente
    private String giornoSettimana; //contiene il giorno della settimana
    private int orario1; //orario 1 che sarà il tempo 9-13
    private int orario2; //orario 2 che sarà il tempo 14-18
    private int orario3; //orario 3 che sarà il tempo 18-22
 
    //costruttore vuoto  
    public OrariLavoratori() {

    }

    /*@Id indica che il metodo restituisce l'ID dell'Orario
    è una chiave primaria nella mia tabella "OrariLavoratori" del database SQL*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdOrario() {
        return idOrario;
    }

    //vari getter setter
    public String getGiornoSettimana() {
        return giornoSettimana;
    }public Long getIdDipendente() {
        return idDipendente;
    }public int getOrario1() {
        return orario1;
    }public int getOrario2() {
        return orario2;
    }public int getOrario3() {
        return orario3;
    }
    public void setIdDipendente(Long idDipendente) {
        this.idDipendente = idDipendente;
    }public void setIdOrario(Long idOrario) {
        this.idOrario = idOrario;
    }    public void setGiornoSettimana(String giornoSettimana) {
        this.giornoSettimana = giornoSettimana;
    }public void setOrario1(int orario1) {
        this.orario1 = orario1;
    }public void setOrario2(int orario2) {
        this.orario2 = orario2;
    }public void setOrario3(int orario3) {
        this.orario3 = orario3;
    }

    /*questo è il metodo che mi calcola gli orari
     * in modo casuale che andranno a settare gli orari
     * settimanali di ogni nuovo dipendente senza andare
     * ad inserirli manualmente. Ho fatto questa funzione per
     * visionare il funzionamento, ma per coerenza della traccia dovrebbe
     * esserci una progettazione manuale della settimana. Dovrei quindi 
     * togliere il metodo e mettere ogni settimana il valore 0 o 4
     */
    public int calcolaOrari(){

        Random random = new Random();
        int sentinella = 1;
        int orario =0;
        do{
         orario= random.nextInt(0, 5);

        if(orario == 0 || orario == 4){
            sentinella = 0;
        }
        }while(sentinella ==1);

        return orario;
}}