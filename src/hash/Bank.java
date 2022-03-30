package hash;

public class Bank {

    private int n;

    private long[] balance;

    public Bank(long[] balance) {
        n = balance.length;
        this.balance = new long[n];
        for (int i = 0; i < n; i++) {
            this.balance[i] = balance[i];
        }
    }

    public boolean transfer(int account1, int account2, long money) {
        if(account1 <= 0 || account1 > n || account2 <= 0 || account2 > n) {
            return false;
        }
        if(balance[account1 - 1] < money) {
            return false;
        }else {
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }
    }

    public boolean deposit(int account, long money) {
        if(account <= 0 || account > n) {
            return false;
        }

        balance[account - 1] += money;

        return true;
    }

    public boolean withdraw(int account, long money) {
        if(account <= 0 || account > n || balance[account - 1] < money) {
            return false;
        }

        balance[account - 1] -= money;

        return true;
    }
}
