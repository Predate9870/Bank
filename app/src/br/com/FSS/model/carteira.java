package br.com.FSS.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class carteira {

    @Getter
    private final Bankservice serviceType;

    protected final List<Money> money;

    public Wallet(final Bankservice sevicetype) {
        this.service = sevicetype;
        this.money = new ArrayList<>();
    }

    protected List<Money> generateMoney(final long amount, final String description) {
        var history = new MoneyAudity(UUID.randomUUID(), service, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(amount).toList();
    }

    public long GetFunds() {
        return money.size();
    }

    public void ADDMoney(final List<Money> money, final Bankservice service, final String description) {
        var history = new MoneyAudity(UUID.randomUUID(), service, description, OffsetDateTime.now());
        money.forEach(m -> m.addHistory(history));
        this.money.addAll(money);
    }


    public List<Money> reduceMoney(final long amount) {
        List<Money> toRemove = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            toRemove.add(this.money.removeFirst());
        }
        return toRemove;
    }

    public List<MoneyAudit> getFinancialTransaction() {
        return money.stream().flatMap(m -> m.getHistórico().stream()).toList();
    }

    @Override
    public String toString() {
        return "Wallet{" +
               "Serviço: " + service +
               ", Dinheiro: R$" + (money.size() / 100) +
               "}"
    }
}
