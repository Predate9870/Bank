package br.com.FSS.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;


public class InvestmentWallet extends carteira {

    private final Investmento investiment;
    private final AccountWallet account;

    public InvestmentWallet(final Investimento investiment, final AccountWallet account, final long amount) {
        super(INVESTIMENT);
        this.investiment = investiment;
        this.account = account;
        this.amount = account;

        ADDMoney(account.reduceMoney(amount), getService(), "investimento");
    }

    public void UpdateMoney(final long percent) {
        var amount = GetFunds() * percent / 100;
        var history = new MoneyAudit(UUID.randomUUID(), getService(), "rendimentos", OffsetDateTime.now);
        var money = Stream.generate(() -> new Money(history)).limit(amount).toList();
        this.money.addAll(money);
    }

    @Override String toString() {
        return super.toString() + "Carteira de investimento{" +
                                  "investimento: " + investiment +
                                  ", conta: " + account +
                                  "}";
    }
    
}
