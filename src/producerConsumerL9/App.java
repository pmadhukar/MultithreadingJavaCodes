package producerConsumerL9;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Worker worker = new Worker();

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		thread1.start();
		thread2.start();


		thread1.join();
		thread2.join();
	}

}
