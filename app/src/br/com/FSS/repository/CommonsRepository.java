package br.com.FSS.repository;

import lombok.NoArgsConstructor;

import static lombok.AcessLevel.PRIVATE;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import br.com.FSS.exception.NotMoneyEnoughException.NotMoneyEnoughException;
import br.com.FSS.model.AccountWallet;

@NoArgsConstructor(acess = PRIVATE)
public class CommonsRepository {
    public static void checkFundsTransaction(final carteira source, final long amount) {
        if (source.GetFunds() < amount) {
            throw new NotMoneyEnoughException("Sua conta não tem dinheiro suficiente para realizar essa transação");
        }
    }

    public static List<Money> generateMoney(final UUID transactionID, final long funds, final String description) {
        var history = new MoneyAudit(transactionID, ACCOUNT, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(funds).toList();
    }
}
