package IO;

import java.io.IOException;
import java.util.Scanner;

import StaticData.SessionData;
import contracts.Interpreter;
import contracts.Reader;

public class InputReader implements Reader {

    private static final String END_COMMAND = "quit";

    private Interpreter interpreter;

    public InputReader(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void readCommands() {
        Scanner scanner = new Scanner(System.in);

        OutputWriter.writeMessage(String.format("%s>", SessionData.currentPath));

        String input = scanner.nextLine().trim();

        while (!input.equals(END_COMMAND)) {
            try {
                interpreter.interpretCommand(input);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            OutputWriter.writeMessage(String.format("%s>", SessionData.currentPath));

            input = scanner.nextLine().trim();
        }

        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            if (!thread.getName().equals("main") && !thread.isDaemon()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
