import java.util.HashMap;

enum CurrencyCodes {RUR, USD, EUR}
public class Account {
    private String AccountName;

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        if (accountName == null || accountName.isEmpty()) throw new IllegalArgumentException();
        AccountName = accountName;
    }

    private HashMap<CurrencyCodes, Integer> currencies;

    public HashMap<CurrencyCodes, Integer> getCurrencies() {
        return new HashMap<CurrencyCodes, Integer>(this.currencies);
    }

    public void setCurrencies(CurrencyCodes curcode, Integer quantity) {
        if (quantity<0) throw new IllegalArgumentException();
        /*if (currencies.containsKey(curcode)) //если мы изменили сущ. значение
        {
            this.commands.push(()->{this.currencies.put(curcode, quantity);});
        }
        else //если мы добавили новое значение
        {
            this.commands.push(()->{this.currencies.remove(curcode);});
        }*/
        this.currencies.put(curcode, quantity);
    }

    public Account(String accountName) {
        this.setAccountName(accountName);
        this.currencies = new HashMap<>();
    }

    private Account() {
    }
    public void printCurrencies()
    {
        this.currencies.values().stream().forEach(System.out::println);
    }
}
