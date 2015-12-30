package producerConsumerL9;

import java.util.LinkedList;

public class Worker {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException{
		int value = 0;
		while(true){
			synchronized(lock){
				while(list.size() == LIMIT){
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}

		}

	}

	public void consume() throws InterruptedException{
		while(true){

			synchronized(lock){
				while(list.size() == 0){
					lock.wait();
				}

				System.out.print("size of list: " + list.size());
				int value = list.removeFirst();
				System.out.println(" ;value removed: " + value);

				lock.notify();
				Thread.sleep(1000);
			}

		}

	}
}
