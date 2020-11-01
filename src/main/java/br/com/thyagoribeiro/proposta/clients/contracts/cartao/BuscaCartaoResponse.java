package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.*;
import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BuscaCartaoResponse {

    private String id;

    @JsonProperty("emitidoEm")
    private LocalDateTime dataEmissao;

    private String titular;

    @JsonProperty("bloqueios")
    private List<BloqueioResponse> bloqueioResponseList;

    @JsonProperty("avisos")
    private List<AvisoResponse> avisoResponseList;

    @JsonProperty("carteiras")
    private List<CarteiraResponse> carteiraResponseList;

    @JsonProperty("parcelas")
    private List<ParcelaResponse> parcelaResponseList;

    private BigDecimal limite;

    @JsonProperty("renegociacao")
    private RenegociacaoResponse renegociacaoResponse;

    @JsonProperty("vencimento")
    private VencimentoResponse vencimentoResponse;

    private String idProposta;

    public BuscaCartaoResponse(String id, LocalDateTime dataEmissao, String titular, List<BloqueioResponse> bloqueioCartaoResponseList, List<AvisoResponse> avisoCartaoResponseList, List<CarteiraResponse> carteiraCartaoResponseList, List<ParcelaResponse> parcelaCartaoResponseList, BigDecimal limite, RenegociacaoResponse renegociacaoCartaoResponse, VencimentoResponse vencimentoCartaoResponse, String idProposta) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.titular = titular;
        this.bloqueioResponseList = bloqueioCartaoResponseList;
        this.avisoResponseList = avisoCartaoResponseList;
        this.carteiraResponseList = carteiraCartaoResponseList;
        this.parcelaResponseList = parcelaCartaoResponseList;
        this.limite = limite;
        this.renegociacaoResponse = renegociacaoCartaoResponse;
        this.vencimentoResponse = vencimentoCartaoResponse;
        this.idProposta = idProposta;
    }

    public Cartao toModel(Proposta proposta) {

        List<Bloqueio> bloqueioList = new ArrayList<>();
        List<Aviso> avisoList = new ArrayList<>();
        List<Carteira> carteiraList = new ArrayList<>();
        List<Parcela> parcelaList = new ArrayList<>();

        bloqueioResponseList.forEach(bloqueioResponse -> bloqueioList.add(bloqueioResponse.toModel()));
        avisoResponseList.forEach(avisoResponse -> avisoList.add(avisoResponse.toModel()));
        carteiraResponseList.forEach(carteiraResponse -> carteiraList.add(carteiraResponse.toModel()));
        parcelaResponseList.forEach(parcelaResponse -> parcelaList.add(parcelaResponse.toModel()));

        Renegociacao renegociacao = renegociacaoResponse == null ? null : renegociacaoResponse.toModel();
        Vencimento vencimento = vencimentoResponse == null ? null : vencimentoResponse.toModel();

        return new Cartao(id, dataEmissao, titular, bloqueioList, avisoList, carteiraList, parcelaList, limite, renegociacao, vencimento);
    }
}
