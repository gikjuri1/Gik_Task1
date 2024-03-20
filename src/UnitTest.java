import org.junit.Assert;

import java.util.Optional;

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

    /*@org.junit.jupiter.api.Test
    void checkUndo() throws NothingToUndo {

        Unit u = new Unit("Qwerty");
        String oldName = u.getName();
        u.setName("ASDFG");
        u.undo();
        Assert.assertEquals(oldName, u.getName());
     }
     */
}
