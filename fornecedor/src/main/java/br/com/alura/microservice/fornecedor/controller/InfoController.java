package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    @RequestMapping("/{estado}")
    public ResponseEntity<InfoFornecedor> getInfoPorEstado(@PathVariable String estado) {
        LOG.info("Recebido pedido de informações do fornecedor de {}", estado);
        return ResponseEntity.ok().body(infoService.getInfoPorEstado(estado));
    }

}
