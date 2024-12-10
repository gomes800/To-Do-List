package com.gomes8.To_Do_List.model.enums;

public enum Status {
    PENDENTE(0),
    CONCLUIDA(1);

    private final int valor;

    Status(int valor) {this.valor = valor;}

    public int getValor() {
        return valor;
    }
}
