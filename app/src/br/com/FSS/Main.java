package br.com.FSS;

import java.util.Scanner;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

import br.com.FSS.exception.NotMoneyEnoughException.NotMoneyEnoughException;
import br.com.FSS.model.AccountWallet;
import br.com.FSS.repository.AccountRepository;
import br.com.FSS.repository.InvestmentRepository;

public class Main {
    Scanner input = new Scanner(System.in);

    private final static AccountRepository ARepository = new AccountException();
    private final static InvestmentRepository IRepository = new InvestmentRepository();
    public static void main(String[] args) {
        System.out.println("Olá! Seja bem vindo ao Capybara Bank!");
        while (true) {
            System.out.println("Selecione a operação desejada");
            System.out.println("1) Criar uma conta");
            System.out.println("2) Criar um investimento");
            System.out.println("3) Fazer um investimento");
            System.out.println("4) Depósitos");
            System.out.println("5) Saques");
            System.out.println("6) Transferência entre contas");
            System.out.println("7) Investir");
            System.out.println("8) Sacar um investimento");
            System.out.println("9) Listar contas");
            System.out.println("10) Listar investimentos");
            System.out.println("11) Listar carteiras de investimento");
            System.out.println("12) Atualizar investimentos");
            System.out.println("13) Verificar seu histórico de conta");
            System.out.println("14) Encerrar sessão");
            var choose = input.nexInt();
            switch (choose) {
                case 1: createAccount();
                case 2: createInvestiment();
                case 3: CriarCarteiraDeInvestimento();
                case 4: depósito();
                case 5: saque();
                case 6: transferência();
                case 7: Investir();
                case 8: resgateInvestimento();
                case 9: AccountRepository.list().forEach(a -> System.out.println());
                case 10: InvestmentRepository.list().forEach(a -> System.out.println());
                case 11: AccountRepository.listWallets().forEach(a -> System.out.println());
                case 12: { InvestmentRepository.updateAmount(); System.out.println("Investimentos atualizados");}
                case 13: CheckHistory();
                case 14: -> System.exit(0);
                default: System.out.println("Opção invlida!");

            }
        }
    }
    private static void createAccount() {
        System.out.println("Digite as chaves PIX (OBS: PRECISA ESTAREM SEPARADAS POR UM (;))");
        var pix = Arrays.stream(input.next().split(";")).toList();
        System.out.println("Informe o valor inicial do depósito");
        var amount = input.nextLong();
        var wallet = AccountRepository.create(pix, amount);
        System.out.println("A conta " + wallet + "Foi criada com êxito");
    }

    private static void createInvestiment() {
        System.out.println("Insira o nome do investimento");
        var tax = input.nextInt();
        System.out.println("Informe o valor inicial do depósito");
        var initialFounds = input.nextLong();
        InvestmentRepository.create(tax, initialFounds);
    }

    private static void depósito() {
        System.out.println("Insira a chave PIX da conta que você deseja fazer o depósito:");
        var pix = scanner.Next();
        System.out.println("Agora insira o valor do depósito");
        var amount = input.NextLong();
        try {
            AccountRepository.depósito(pix, amount);
        } catch(AccountNotFoundException ex) {
            Sytem.out.println(ex.getMessage());
        }
    }
    private static void saque() {
    System.out.println("Insira a chave PIX da conta que você deseja fazer o saque");
    var pix = scanner.Next();
    System.out.println("Agora insira o valor do saque");
    var amount = input.NextLong();
    try {
        AccountRepository.depósito(pix, amount);
    } catch(NotMoneyEnoughException | AccountNotFoundException ex) {
        System.out.println(ex.getMessage);
    }
    }
    private static void transferência() {
        System.out.println("Insira a chave PIX da conta de origem");
        var source = scanner.Next();
        System.out.println("Insira a chave PIX da conta de origem");
        var target = scanner.Next();
        System.out.println("Agora insira o valor do depósito");
        var amount = input.NextLong();
        try {
        AccountRepository.transferência(source, target, amount);
        } catch(AccountNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void CriarCarteiraDeInvestimento() {
        System.out.println("Informe a chave PIX da conta");
        var pix = scanner.Next();
        var account = AccountRepository.FindByPix(pix);
        System.out.println("Digite o ID do investimento");
        var id = input.NextInt();
        var carteiraInvestimento = InvestmentRepository.initInvestment(account, id);
        System.out.println("Conta de investimento criada");
    }

    private static void Investir() {
        System.out.println("Insira a chave PIX da conta que você deseja fazer o investimento:");
        var pix = scanner.Next();
        System.out.println("Agora insira o valor que irá investir");
        var amount = input.NextLong();
        try {
            InvestmentRepository.depósito(pix, amount);
        } catch(AccountNotFoundException ex) {
            Sytem.out.println(ex.getMessage());
        }
    }
    private static void resgateInvestimento() {
    System.out.println("Insira a chave PIX da conta para resgate do investimento:");
    var pix = scanner.Next();
    System.out.println("Agora insira o valor do saque:");
    var amount = input.NextLong();
    try {
        InvestmentRepository.saque(pix, amount);
    } catch(NotMoneyEnoughException | AccountNotFoundException ex) {
        System.out.println(ex.getMessage);
    }
    }

    private static void CheckHistory() {
    System.out.println("Insira a chave PIX da conta para ver o histórico");
    var pix = scanner.Next();
    AccountWallet wallet;
    try {
        var sortedHistory = AccountException.getHistory(pix);
        sortedHistory.forEach((k, v) -> {
            System.out.println(k.format(ISO_DATE_TIME));
            System.out.println(v.getFirst().transactionID());
            System.out.println(v.getFirst().description());
            System.out.println(v.size())
        });
    } catch(AccountNotFoundException ex) {
        Sytem.out.println(ex.getMessage());
    }
    }

}
