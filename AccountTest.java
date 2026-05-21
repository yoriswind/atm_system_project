import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        // Fresh account before each test
        account = new Account("ACC-001", 200.00, "Checking");
    }

    // -------------------------------------------------------
    // Test 1: Successful withdrawal
    // Start with $200, withdraw $50, assert balance is $150
    // -------------------------------------------------------
    @Test
    public void testSuccessfulWithdrawal() {
        System.out.println("This is Test 1: Successful withdrawal");

        boolean result = account.withdraw(50.00);

        assertTrue(result, "Withdrawal should return true when funds are sufficient");
        assertEquals(150.00, account.getBalance(), 0.001,
                "Balance should be $150 after withdrawing $50 from $200");
    }

    // -------------------------------------------------------
    // Test 2: Insufficient funds
    // Start with $30 (re-init), try to withdraw $100, assert false
    // -------------------------------------------------------
    @Test
    public void testInsufficientFunds() {
        System.out.println("This is Test 2: Insufficient funds");

        Account lowBalanceAccount = new Account("ACC-002", 30.00, "Checking");

        boolean result = lowBalanceAccount.withdraw(100.00);

        assertFalse(result, "Withdrawal should return false when funds are insufficient");
        assertEquals(30.00, lowBalanceAccount.getBalance(), 0.001,
                "Balance should remain $30 after a failed withdrawal");
    }
}
