package br.com.alura.microservice.fornecedor.respository;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRespository extends CrudRepository<InfoFornecedor, Long> {
    InfoFornecedor findByEstado(String estado);
}
