package com.gomes8.To_Do_List.model.enums;

public enum Prioridade {
    BAIXA(0),
    MEDIA(1),
    ALTA(2);

    private final int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
