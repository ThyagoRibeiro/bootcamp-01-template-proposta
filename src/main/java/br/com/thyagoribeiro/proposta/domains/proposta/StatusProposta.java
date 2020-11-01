package br.com.thyagoribeiro.proposta.domains.proposta;

import java.util.HashMap;
import java.util.Map;

public enum StatusProposta {

    NAO_ELEGIVEL("COM_RESTRICAO"), ELEGIVEL("SEM_RESTRICAO");

    public final String restricao;

    private static final Map<String, StatusProposta> BY_RESTRICAO = new HashMap<>();

    static {
        for (StatusProposta statusProposta : values()) {
            BY_RESTRICAO.put(statusProposta.restricao, statusProposta);
        }
    }

    StatusProposta(String restricao) {
        this.restricao = restricao;
    }

    public static StatusProposta getByRestricao(String restricao) {
        return BY_RESTRICAO.get(restricao);
    }
}
