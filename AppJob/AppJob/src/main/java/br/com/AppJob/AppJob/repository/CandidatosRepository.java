package br.com.AppJob.AppJob.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.com.AppJob.AppJob.models.Candidatos;
import br.com.AppJob.AppJob.models.Vaga;

public interface CandidatosRepository extends CrudRepository<Candidatos, String> {
	
	Iterable<Candidatos>findByVaga(Vaga vaga);
	
	Candidatos findByRg(String rg);
	Candidatos findById(long id);
	
	List<Candidatos>findByNomeCandidatos(String nomeCandidatos);

}
