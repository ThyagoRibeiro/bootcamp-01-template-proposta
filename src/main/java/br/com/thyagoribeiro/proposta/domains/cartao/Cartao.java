package br.com.thyagoribeiro.proposta.domains.cartao;

import br.com.thyagoribeiro.proposta.domains.Biometria;
import br.com.thyagoribeiro.proposta.domains.SolicitacaoSenha;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String numeroCartao;

    private LocalDateTime dataEmissao;

    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Bloqueio> bloqueioList;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Aviso> avisoList;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Carteira> carteiraList;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Parcela> parcelaList;

    private BigDecimal limite;

    @OneToOne(cascade=CascadeType.ALL)
    private Renegociacao renegociacao;

    @OneToOne(cascade=CascadeType.ALL)
    private Vencimento vencimento;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Biometria> biometriaList;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<SolicitacaoSenha> solicitacaoSenhaList;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numeroCartao, LocalDateTime dataEmissao, String titular, List<Bloqueio> bloqueioList, List<Aviso> avisoList, List<Carteira> carteiraList, List<Parcela> parcelaList, BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento) {
        this.numeroCartao = numeroCartao;
        this.dataEmissao = dataEmissao;
        this.titular = titular;
        this.bloqueioList = bloqueioList;
        this.bloqueioList.forEach(bloqueio -> bloqueio.setCartao(this));
        this.avisoList = avisoList;
        this.avisoList.forEach(aviso -> aviso.setCartao(this));
        this.carteiraList = carteiraList;
        this.carteiraList.forEach(carteira -> carteira.setCartao(this));
        this.parcelaList = parcelaList;
        this.parcelaList.forEach(parcela -> parcela.setCartao(this));
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Bloqueio> getBloqueioList() {
        return bloqueioList;
    }

    public void setBloqueioList(List<Bloqueio> bloqueioList) {
        this.bloqueioList = bloqueioList;
    }

    public List<Aviso> getAvisoList() {
        return avisoList;
    }

    public void setAvisoList(List<Aviso> avisoList) {
        this.avisoList = avisoList;
    }

    public List<Carteira> getCarteiraList() {
        return carteiraList;
    }

    public void setCarteiraList(List<Carteira> carteiraList) {
        this.carteiraList = carteiraList;
    }

    public List<Parcela> getParcelaList() {
        return parcelaList;
    }

    public void setParcelaList(List<Parcela> parcelaList) {
        this.parcelaList = parcelaList;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public void setRenegociacao(Renegociacao renegociacao) {
        this.renegociacao = renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }

    public List<Biometria> getBiometriaList() {
        return biometriaList;
    }

    public void setBiometriaList(List<Biometria> biometriaList) {
        this.biometriaList = biometriaList;
    }

    public List<SolicitacaoSenha> getSolicitacaoSenhaList() {
        return solicitacaoSenhaList;
    }

    public void setSolicitacaoSenhaList(List<SolicitacaoSenha> solicitacaoSenhaList) {
        this.solicitacaoSenhaList = solicitacaoSenhaList;
    }
}
