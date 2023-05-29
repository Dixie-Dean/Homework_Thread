import java.util.concurrent.Callable;

public class TextCallable implements Callable<Integer> {
    private final String text;
    private int maxSize;

    public TextCallable(String text) {
        this.text = text;
    }

    @Override
    public Integer call() {
        run();
        return maxSize;
    }

    private void run() {
        maxSize = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (i >= j) {
                    continue;
                }
                boolean bFound = false;
                for (int k = i; k < j; k++) {
                    if (text.charAt(k) == 'b') {
                        bFound = true;
                        break;
                    }
                }
                if (!bFound && maxSize < j - i) {
                    maxSize = j - i;
                }
            }
        }
    }
}
