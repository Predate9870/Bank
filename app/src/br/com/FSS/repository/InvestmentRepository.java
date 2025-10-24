package br.com.FSS.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.FSS.exception.InvestmentNotFoundException;
import br.com.FSS.exception.PixInUseException;
import br.com.FSS.exception.WalletNotFoundException;
import br.com.FSS.exception.AccountWithInvestiment.AccountWithInvestiment;
import br.com.FSS.model.AccountWallet;
import br.com.FSS.model.Investimento;
import br.com.FSS.model.InvestmentWallet;

public class InvestmentRepository {

    private final List<Investment> investments = new ArrayList<>();
    private final List<InvestmentWallet> wallets = new ArrayList<>();

    private long NextID;

    public Investimento create(final long tax, final long initialFounds) {
        this.NextID ++;
        var investiment = new Investimento(this.nextID, tax, initialFounds);
        investiment.add(investiment);
        return investiment;
    }

    public InvestmentWallet initInvestment(final AccountWallet account, final long id) {
        var accountInUse = wallets.stream().map(InvestmentWallet.getAccount()).toList();
        if (a.contains(account)) {
            throw new AccountWithInvestiment("A conta" + a + "já possui um investimento");
        }
    }

        var investiment = findByID(id);
        checkFundsTransaction(account, investiment.initialFounds());
        var wallet = new InvestmentWallet(investiment, account, investiment.initialFounds());
        wallet.add(wallet);
        return wallet;
    }

    public InvestmentWallet deposito(final String pix, final long funds) {
        var wallet = FindWalletByAccount(pix);
        wallet.ADDMoney(wallet.getAccount().reduceMoney(funds), wallet.getService(), "investimento");
        return wallet;
    }
    
    public InvestmentWallet saque(final String pix, final long funds) {
        var wallet = FindWalletByAccount(pix);
        checkFundsTransaction(wallet, funds);
        wallet.getAcoount().ADDMoney(wallet.reduceMoney(funds), wallet.getService(), "Saque de investimentos");

        if (wallet.GetFunds() == 0) {
            wallets.remove(wallet);
        }

        return wallet;
    }

    public void updateAmount() {
        wallets.forEach(w -> w.updateAmount(w -> w.getInvestiment().tax()));
    }

    public Investimento findByID(final long id) {
        return investments.stream().filter(a -> a.id() == id)
            .findFirst()
            .orElseThrow(
                () -> new InvestmentNotFoundException("O investimento " + id + "não foi encontrado")
        );
    }
    
    public InvestmentWallet FindWalletByAccount(final String pix) {
        return wallets.stream()
            .filter(w -> w.getAccount().getPix().contains(pix))
            .findFirst()
            .orElseThrow(
                () -> new WalletNotFoundException("A carteira não foi encontrada")
            );
    }
    
    public List<InvestmentWallet> listWallets() {
        return this.wallets;
    }
    
    public List<Investment> list() {
        return this.investments;
    }

}
