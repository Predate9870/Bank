package br.com.FSS.model;

import java.util.ArrayList;

import lombok.EqualsHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsHashCode
@Getter
@ToString

public class Money {

    private final List<MoneyAudit> histórico = new ArrayList<>();

    public Money(final MoneyAudit histórico) {
        this.histórico.add(histórico);
    }

    public void addHistory(final MoneyAudit histórico) {
        this.histórico.add(histórico);
    }
}
