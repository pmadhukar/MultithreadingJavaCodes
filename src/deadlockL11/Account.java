package deadlockL11;

public class Account {
	private double balance;

	private void withdraw(double amount){
		balance -= amount;
	}

	private void deposit(double amount){
		balance += amount;
	}

	public Account(double balance) {
		this.balance = balance;
	}


	public double getBalance() {
		return balance;
	}

	public void transfer(Account acc1, Account acc2, double amount){
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}
}
