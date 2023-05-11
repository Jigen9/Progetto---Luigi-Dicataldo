package aziendagestionale.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class OrariLavoratoriService {
 
    @Autowired
    private OrariLavoratoriRepository repo;

    public List<OrariLavoratori> listAll() {
        return repo.findAll();
    }

    public void save(OrariLavoratori idOrario) {
        repo.save(idOrario);
    }

    public OrariLavoratori get(long idOrario) {
        return repo.findById(idOrario).get();
    }

    public void delete(long idOrario) {
        repo.deleteById(idOrario);
    }
    
    /*qui seleziono tutti gli orari lavorativi dell'id dipendente specificato.*/
    public List<OrariLavoratori> getOrarioSettimanaleByDipendenti(Long idDipendente) {
        return repo.getOrarioSettimanaleByDipendenti(idDipendente);
    }
}