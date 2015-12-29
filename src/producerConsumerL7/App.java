/*
 * Line no 39 and 51 (commented with Check1 and Check2)
 * Why try/catch block is compulsory there?
 * Adding throws InterruptedException in front of main method doesn't work there.
 *
 * Whereas Line no 66 and 67 (commented with Check3 and Check4)
 * Adding throws InterruptedException in front of main method works.
 * try/catch block not compulsory there.
 */
package producerConsumerL7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void producer() throws InterruptedException{
		Random random = new Random();
		while(true){
			queue.put(random.nextInt(100));
		}
	}

	public static void consumer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Thread.sleep(100);
			if(random.nextInt(10) == 0){
				Integer value = queue.take();

				System.out.println("Take value: " + value + " size of queue: " + queue.size());
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					producer(); //Check1
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumer();//Check2
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});


		thread1.start();//Check3
		thread2.start();//Check4

		/*
		thread1.join();
		thread2.join();
		*/
	}

}
