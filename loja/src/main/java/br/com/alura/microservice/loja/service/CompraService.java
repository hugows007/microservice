package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.dto.*;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.model.CompraState;
import br.com.alura.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

    @Autowired
    private CompraRepository compraRepository;

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    public Compra realizaCompra(CompraDTO compra) {
        final String estado = compra.getEndereco().getEstado();

        Compra compraSalva = new Compra();
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraSalva.setState(CompraState.RECEBIDO);
        compraRepository.save(compraSalva);
        compra.setCompraId(compraSalva.getId());

        LOG.info("buscando informações do fornecedor de {}", estado);
        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);

        LOG.info("Realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());

        compraSalva.setState(CompraState.PEDIDO_REALIZADO);
        compraRepository.save(compraSalva);

        InfoEntregaDTO entregaDto = new InfoEntregaDTO();
        entregaDto.setPedidoId(pedido.getId());
        entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDto.setEnderecoOrigem(info.getEndereco());
        entregaDto.setEnderecoDestino(compra.getEndereco().toString());
        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucher.getNumero());

        compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraRepository.save(compraSalva);
        return compraSalva;
    }

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    public Compra realizaCompraFallback(CompraDTO compra) {
        if (compra.getCompraId() != null) {
            return compraRepository.findById(compra.getCompraId()).get();
        }
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compra.getEndereco().toString());
        return compraFallback;
    }
}
