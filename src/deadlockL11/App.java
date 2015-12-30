package deadlockL11;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Runner runner = new Runner();

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				runner.firstThread();
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				runner.secondThread();
			}
		});

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		runner.finished();
	}

}
