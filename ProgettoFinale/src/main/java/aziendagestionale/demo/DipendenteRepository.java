package aziendagestionale.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/*Interfaccia di repository di Spring Data JPA per la gestione delle entità Dipendente */
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    //query personalizzata con @Query
    @Query("SELECT p FROM Dipendente p WHERE CONCAT(p.idDipendente, p.nome, p.cognome, p.ruolo, p.sede) LIKE %?1%")
    public List<Dipendente> search(String keyword);
    //segue la corrispondenza parziale della stringa di ricerca. 
    //Il metodo restituisce un elenco di dipendenti che corrispondono alla query che ho fatto su.
 
}