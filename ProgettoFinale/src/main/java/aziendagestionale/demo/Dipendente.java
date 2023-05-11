package aziendagestionale.demo;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
/*con questa annotazione @Entity indico che Dipendente è un'entità presente nel DB 
(su mySql c'è la tabella "dipendente")*/
@Entity
public class Dipendente {
    private Long idDipendente; //contiene idDipendente
    private String nome;    //nome del dipendente
    private String cognome; //cognome del dipendente
    private String ruolo; //ruolo del dipendente
    private int livello; //livello del dipendente
    private float stipendio; //stipendio del dipendente
    private String sede; //sede di lavoro del dipendente
    private int anniServizio; //anni di servizio del dipendente
  
    //costruttore vuoto
    protected Dipendente() {
    }
 
    /*@Id indica che il metodo restituisce l'ID del dipendente
    è una chiave primaria nella mia tabella "Dipendente" del database SQL*/
    @Id
    /*@GeneratedValue indica che il valore dell'ID è generato automaticamente dal database*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdDipendente() {
        return idDipendente;
    }

    //vari getter e setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public float getStipendio() {
        return stipendio;
    }

    public void setStipendio(float stipendio) {
        this.stipendio = stipendio;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getAnniServizio() {
        return anniServizio;
    }

    public void setAnniServizio(int anniServizio) {
        this.anniServizio = anniServizio;
    }

    public void setIdDipendente(Long idDipendente) {
        this.idDipendente = idDipendente;
    }

    /*In questa funzione calcolo lo stipendio in base al livello
     * l'ho utilizzato per visualizzare lo stipendio SENZA i bonus.
     * Verrà visualizzato all'interno della modale
     */
    public float calcolastipendio(){

        int livelloTemp = livello; //imposto una variabile temporanea che contiene il livello
        float stipendioBase = 1500; //stipendio default

        switch (livelloTemp) {
            
            case 1:{
                stipendio = stipendioBase;
                break;}
            case 2:{ //se sei lv2 prendo il 20% in più dello stipendio base
                stipendio = (stipendioBase + (0.20f*stipendioBase));
                break;}
            case 3:{ //35% in più
                stipendio = (stipendioBase + (0.35f*stipendioBase));
                break;}
            default:{
                stipendio = stipendioBase;
                livello = 1;
                anniServizio=1; //imposto i default
                break;}
        }
        if(anniServizio==0){ //evito dipendenti di lv0
            anniServizio=1;
        }
        return stipendio;
    }


    /*per questione puramente dimostrativa genero i mesi dei singoli dipendenti in modo casuale
     * 
    */
    public int generaMesi() {
        Random rand = new Random();
        int mese = rand.nextInt(12) + 1;
        return mese;
    }
    
    /* stesso nome dell'altro stipendio,ma questo lo uso per il calcolo dello stipendio con bonus
     * viene richiamato il metodo adatto in base ai parametri passati
    */
    public float calcolastipendio(int notturne, int straordinari) {
        float stip=calcolastipendio();
        stip+=(notturne * 5)+(straordinari * 5);
        return stip;
    }


}