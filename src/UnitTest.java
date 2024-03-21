import org.junit.Assert;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTest {
    @org.junit.jupiter.api.Test
    void getName() {
        Account a = new Account("JohnsAccount");
        a.setAccountName("PetersAccount");
        Assert.assertEquals(a.getAccountName(),"PetersAccount");
    }

    @org.junit.jupiter.api.Test
    void getCurrency() {
        Account a = new Account("JohnsAccount");
        a.setCurrencies(CurrencyCodes.USD,100);
        Integer test = a.getCurrencies().get(CurrencyCodes.USD);
        Assert.assertEquals(test, (Integer) 100);
    }

    @org.junit.jupiter.api.Test
    void setEmptyName() {
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                Account a = new Account("");
            }
        });
    }

    @org.junit.jupiter.api.Test
    void setNegativeCurrenctQuantity() {
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                Account a = new Account("Johns");
                a.setCurrencies(CurrencyCodes.USD,-100);
            }
        });
    }

    @org.junit.jupiter.api.Test
    void checkUndo() throws NothingToUndo {

        Account a = new Account("Qwerty");
        String oldAccountName = a.getAccountName();
        a.setAccountName("ASDFG");
        a.undo();
        Assert.assertEquals(oldAccountName, a.getAccountName());
     }
    @org.junit.jupiter.api.Test
    void checkSnapshot() {

        Account a1 = new Account("Qwerty");

        a1.setCurrencies(CurrencyCodes.RUR,10000);
        a1.setCurrencies(CurrencyCodes.USD,100);
        Loadable qs1 = a1.Save();
        a1.setAccountName("PseudoOrk1");
        a1.setCurrencies(CurrencyCodes.RUR,20000);
        a1.setCurrencies(CurrencyCodes.USD,200);
        qs1.load();

        Integer test = a1.getCurrencies().get(CurrencyCodes.USD);
        Assert.assertEquals(test, (Integer) 100);
    }

}
