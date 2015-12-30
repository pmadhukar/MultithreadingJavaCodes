package deadlockL11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private Account acc1 = new Account(10000);
	private Account acc2 = new Account(10000);

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void acquireLock(Lock lock1, Lock lock2) throws InterruptedException{

		while(true){
			//Acquire Locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;

			try {
				gotFirstLock = lock1.tryLock();
				gotSecondLock = lock2.tryLock();
			} finally {
				if(gotFirstLock && gotSecondLock){
					return;
				}
				if(gotFirstLock) {
					lock1.unlock();
				}
				if(gotSecondLock){
					lock2.unlock();
				}
			}

			//Locks not acquired
			Thread.sleep(1);
		}

	}

	public void firstThread() throws InterruptedException{
		Random random = new Random();

		for(int i=0; i<1000; i++){

			//TODO: try acquiring locks before the loop starts
			//following part changed to introduce acquireLock() method
			//lock1.lock();
			//lock2.lock();

			//This method ensures that there will be no deadlock
			//irrespective of the order of locks acquired
			//e.g. secondThread can acquire lock2 then lock1 in that order
			//but acquireLock() method will ensure that there is no deadlock
			acquireLock(lock1, lock2);

			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException{
		Random random = new Random();

		for(int i=0; i<1000; i++){

			//TODO: try acquiring locks before the loop starts
			//If we change the order of locks as below:
			/*
			 * lock2.lock();
			 * lock1.lock();
			 */
			//It will cause deadlock, because firstThread() acquire lock1
			//and secondThread() will acquire lock2

			//Following lines changed to use method acquireLock()
			//lock1.lock();
			//lock2.lock();

			acquireLock(lock2, lock1);

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
