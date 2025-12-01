public class Loader implements Runnable {
    private volatile boolean running = true;
    private final String message;

    public Loader(String message) {
        this.message = message;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (running) {
                System.out.print("\r" + message + " " + dots(i));
                System.out.flush();
                Thread.sleep(300);
                i = (i + 1) % 4;
            }
            System.out.print("\r"); // clear line when done
        } catch (InterruptedException ignored) {}
    }

    private String dots(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append('.');
        return sb.toString();
    }
}
