package aziendagestionale.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//classe che si occupa di fornire i servizi relativi ai dipendenti dell'azienda
//io ho salva, getid, cancella e così via
@Service
@Transactional
public class DipendenteService {
 
    //iniezione automatica di dipendenza
    @Autowired
    private DipendenteRepository repo;
    //in questo medoto inserisco una stringa di parole chiave come parametro, che viene utilizzata per filtrare i risultati.
    public List<Dipendente> listAll(String keyword) {
    	if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Dipendente dipendente) {
        repo.save(dipendente);
    }

    public Dipendente get(long idDipendente) {
        return repo.findById(idDipendente).get();
    }

    public void delete(long idDipendente) {
        repo.deleteById(idDipendente);
    }


}