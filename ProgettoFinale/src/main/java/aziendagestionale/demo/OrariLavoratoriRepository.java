package aziendagestionale.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrariLavoratoriRepository extends JpaRepository<OrariLavoratori, Long> {
 
    //ottengo una lista di oggetti OrariLavoratori in base all'id del dipendente
    @Query("select s from OrariLavoratori s where s.idDipendente = :id_dip")
    public List<OrariLavoratori> getOrarioSettimanaleByDipendenti(@Param("id_dip") Long id_dip);
    /* @Param la uso per associare il valore del parametro "id_dip" alla variabile "id_dip" nella query SQL. */

}