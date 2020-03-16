package br.com.alura.microservice.loja.dto;

import java.util.ArrayList;
import java.util.List;

public class CompraDTO {
    private List<ItemDaCompraDTO> itens = new ArrayList<>();
    private EnderecoDTO endereco;

    public List<ItemDaCompraDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDaCompraDTO> itens) {
        this.itens = itens;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
