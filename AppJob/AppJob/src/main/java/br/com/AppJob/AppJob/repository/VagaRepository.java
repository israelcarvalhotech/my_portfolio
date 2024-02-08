package br.com.AppJob.AppJob.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.AppJob.AppJob.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, String> {
	Vaga findByCodigo(long codigo);
	List<Vaga> findByNome(String nome);
	
}
