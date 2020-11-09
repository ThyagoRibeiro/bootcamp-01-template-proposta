package br.com.thyagoribeiro.proposta.domains;

import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitacaoSenha {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDateTime dataSolicitacao;

    private String ip;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    private String userAgent;

    @Deprecated
    public SolicitacaoSenha() {
    }

    public SolicitacaoSenha(LocalDateTime dataSolicitacao, Cartao cartao, String ip, String userAgent) {
        this.dataSolicitacao = dataSolicitacao;
        this.cartao = cartao;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
