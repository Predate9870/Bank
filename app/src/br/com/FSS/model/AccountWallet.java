package br.com.FSS.model;

@Getter
public class AccountWallet extends carteira {

    private final List<String> pix;

    public AccountWallet(final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
    }

    public AccountWallet(final long amount, final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
        ADDMoney(amount, "valor de criação da conta");
    }

    public void ADDMONEY(final long amount, final String description){
        var money = generateMoney(amount, description);
        this.money = addAll(money);
    }

    @Override
    public String toString() {
        return super.toString() + "Carteira da conta {"  +
               "Pix: " + pix +
               "}";
    }
}
