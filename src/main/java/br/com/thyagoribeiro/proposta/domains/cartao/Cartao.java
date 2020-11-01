package br.com.thyagoribeiro.proposta.domains.cartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {

    @Id
    private String id;

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

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime dataEmissao, String titular, List<Bloqueio> bloqueioList, List<Aviso> avisoList, List<Carteira> carteiraList, List<Parcela> parcelaList, BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento) {
        this.id = id;
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
}
