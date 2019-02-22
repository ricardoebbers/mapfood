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

    public OrderEnum getNextStatus(){
        switch (this){
            case NOVO:
                return RECEBIDO;
            case RECEBIDO:
                return EM_PREPARO;
            case EM_PREPARO:
                return SAIU_ENTREGA;
            case PROCURANDO_ENTREGADOR:
                return AGUARDANDO_ENTREGADOR;
            case AGUARDANDO_ENTREGADOR:
                return SAIU_ENTREGA;
            case SAIU_ENTREGA:
                return ENTREGUE;
            case ENTREGUE:
                break;
            case CANCELADO:
                break;
        }
        return this;
    }
}
