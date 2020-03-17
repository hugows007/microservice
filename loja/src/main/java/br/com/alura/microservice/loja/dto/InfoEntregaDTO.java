package br.com.alura.microservice.loja.dto;

import java.time.LocalDate;

public class InfoEntregaDTO {
    private Long id;

    private Long pedidoId;

    private LocalDate dataParaBusca;

    private LocalDate previsaoParaEntrega;

    private String enderecoOrigem;

    private String enderecoDestino;
    private LocalDate dataParaEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public LocalDate getDataParaBusca() {
        return dataParaBusca;
    }

    public void setDataParaBusca(LocalDate dataParaBusca) {
        this.dataParaBusca = dataParaBusca;
    }

    public LocalDate getPrevisaoParaEntrega() {
        return previsaoParaEntrega;
    }

    public void setPrevisaoParaEntrega(LocalDate previsaoParaEntrega) {
        this.previsaoParaEntrega = previsaoParaEntrega;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public void setEnderecoOrigem(String enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public void setDataParaEntrega(LocalDate dataParaEntrega) {
        this.dataParaEntrega = dataParaEntrega;
    }

    public LocalDate getDataParaEntrega() {
        return dataParaEntrega;
    }
}
