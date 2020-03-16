package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.respository.InfoRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class InfoService {

    @Autowired
    private InfoRespository infoRespository;

    public InfoFornecedor getInfoPorEstado(String estado) {
        return infoRespository.findByEstado(estado);
    }
}
