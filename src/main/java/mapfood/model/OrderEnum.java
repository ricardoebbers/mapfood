package mapfood.model;

import java.io.Serializable;

public enum OrderEnum implements Serializable {

    NOVO("Novo"),
    RECEBIDO("Recebido"),
    EM_PREPARO("Em preparo"),
    PROCURANDO_ENTREGADOR("Procurando entregador"),
    AGUARDANDO_ENTREGADOR("Aguardando Entregador"),
    SAIU_ENTREGA("Saiu para entrega"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String valorStatus;

    OrderEnum(String valorStatus) {
        this.valorStatus = valorStatus;
    }

    public String valorStatus(){
        return valorStatus;
    }
}
