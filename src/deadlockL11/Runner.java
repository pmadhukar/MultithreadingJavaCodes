package deadlockL11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private Account acc1 = new Account(10000);
	private Account acc2 = new Account(10000);

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void firstThread(){
		Random random = new Random();

		for(int i=0; i<1000; i++){

			//TODO: try acquiring locks before the loop starts
			lock1.lock();
			lock2.lock();

			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread(){
		Random random = new Random();

		for(int i=0; i<1000; i++){

			//TODO: try acquiring locks before the loop starts
			lock1.lock();
			lock2.lock();

			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished(){
		System.out.println("Account1 balance: " + acc1.getBalance());
		System.out.println("Account2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
