package br.com.thyagoribeiro.proposta.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ErroPadronizado {

    private Collection<String> mensagens;

    public ErroPadronizado(String mensagem) {
        this.mensagens = Arrays.asList(mensagem);
    }

    public ErroPadronizado(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }

}
