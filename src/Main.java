public class Main {
    public static void main(String[] args) throws NothingToUndo {

        System.out.println("Hello world!");

        Account a1 = new Account("deposit1");
        a1.setCurrencies(CurrencyCodes.RUR, 10000);
        a1.setCurrencies(CurrencyCodes.USD,200);
        a1.printCurrencies();
        a1.undo().undo();
        a1.printCurrencies();
        a1.setCurrencies(CurrencyCodes.USD,200);
        a1.printCurrencies();
        a1.undo();
        a1.printCurrencies();

        System.out.println("\n\n========================!");
        a1.setCurrencies(CurrencyCodes.RUR,10000);
        a1.setCurrencies(CurrencyCodes.USD,100);
        a1.printCurrencies();
        Loadable qs1 = a1.Save();
        a1.setAccountName("PseudoOrk1");
        a1.setCurrencies(CurrencyCodes.RUR,20000);
        a1.setCurrencies(CurrencyCodes.USD,200);
        System.out.println("\n---------------");
        a1.printCurrencies();
        qs1.load(); //восстанавливаемся
        System.out.println("\n---------------");
        a1.printCurrencies();
    }
}