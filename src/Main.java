public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Account a1 = new Account("deposit1");
        a1.setCurrencies(CurrencyCodes.RUR, 10000);
        a1.setCurrencies(CurrencyCodes.USD,200);
        a1.printCurrencies();

    }
}