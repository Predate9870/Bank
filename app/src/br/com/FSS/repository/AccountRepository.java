package br.com.FSS.repository;

import br.com.FSS.exception.PixInUseException;
import br.com.FSS.model.AccountWallet;
import br.com.FSS.model.MoneyAudit;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

public class AccountRepository {

    private final List<AccountWallet> accounts;

    public AccountWallet create(final List<String> pix, final long initialFounds) {
        var pixInUse = accounts.stream().flatMap(a -> a.getPix().stream()).toList();
        for (String p: pix) {
            if (pixInUse.contains(p)) {
                throw new PixInUseException("O pix " + p + "já está em uso");
            }
        }

        var newAccount = new AccountWallet(initialFounds, pix);
        accounts.add(newAccount);
        return newAccount;
    }

    public void depósito(final String pix, final long FundsAmount) {
        var target = FindByPix(pix);
        target.ADDMONEY(FundsAmount, "depósito");
    }

    public long saque(final String pix, final long amount) {
        var source = FindByPix(pix);
        checkFundsTransaction(source, amount);
        source.reduceMoney(amount);
        return amount;
    }

    public void transferência(final String sourcePix, final String targetPix, final long amount) {
        var source = FindByPix(sourcePix);
        checkFundsTransaction(source, amount);
        var target = FindByPix(targetPix);
        var message = "pix enviado de " + sourcePix + " para "  + targetPix + "!";
        target.ADDMONEY(source.reduceMoney(amount), source.getService(), message);
    }

    public AccountWallet FindByPix(final String pix) {
        return accounts.stream()
        .filter(a -> a.getPix().contains(pix))
        .findFirst()
        .orElseThrow(() -> new AccountNotFoundException("A conta pix " + pix + "não existe ou foi encerrada"));
    }

    public List<AccountWallet> list() {
        return this.accounts;
    }

    public Map<OffsetDateTime, List<MoneyAudit>> getHistory(final String pix) {
        var wallet = FindByPix(pix);
        var audit = wallet.getFinancialTransaction();
        return audit.stream()
            .collect(Collectors.groupingBy(t -> t.createdAT).truncatedTo(SECONDS));
    }
}

