package deadlockL11;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Runner runner = new Runner();

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		runner.finished();
	}

}
