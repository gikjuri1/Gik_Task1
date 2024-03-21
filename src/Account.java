import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

interface Loadable
{
    void load();
}

class NothingToUndo extends Exception{}
interface Command{
    public void perform();
}
enum CurrencyCodes {RUR, USD, EUR}
public class Account {
    public Loadable Save() {return new Snapshot();}
    private String AccountName;

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        if (accountName == null || accountName.isEmpty()) throw new IllegalArgumentException();
        String oldAccountName= this.getAccountName();
        this.commands.push(()->{this.AccountName=oldAccountName;});
        AccountName = accountName;
    }
    private class Snapshot implements Loadable
    {
        private String AccountName;
        private HashMap<CurrencyCodes, Integer> currencies;

        public Snapshot ()
        {
            this.AccountName = Account.this.AccountName;
            this.currencies = new HashMap<>(Account.this.currencies);
        }
        @Override
        public void load() {
            Account.this.AccountName = this.AccountName;
            Account.this.currencies = new HashMap<>(this.currencies);
        }
    }

    private HashMap<CurrencyCodes, Integer> currencies;

    public HashMap<CurrencyCodes, Integer> getCurrencies() {
        return new HashMap<CurrencyCodes, Integer>(this.currencies);
    }

    public void setCurrencies(CurrencyCodes curcode, Integer quantity) {
        if (quantity<0) throw new IllegalArgumentException();
        if (currencies.containsKey(curcode)) //если мы изменили сущ. значение
        {
            this.commands.push(()->{this.currencies.put(curcode, quantity);});
        }
        else //если мы добавили новое значение
        {
            this.commands.push(()->{this.currencies.remove(curcode);});
        }
        this.currencies.put(curcode, quantity);
    }

    public Account(String accountName) {
        this.setAccountName(accountName);
        this.currencies = new HashMap<>();
    }

    private Account() {
    }
    public  Account undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();
        return this;
    }
    private Deque<Command> commands = new ArrayDeque<>();
    public void printCurrencies()
    {
        this.currencies.values().stream().forEach(System.out::println);
    }
}
