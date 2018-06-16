package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public abstract class Command implements Executable {

    private String input;
    private String[] data;

    private Database repository;
    private ContentComparer tester;
    private DirectoryManager ioManager;
    private AsynchDownloader downloadManager;

    protected Command(String input,
                      String[] data,
                      Database repository,
                      ContentComparer tester,
                      DirectoryManager ioManager,
                      AsynchDownloader downloadManager) {
        this.setInput(input);
        this.setData(data);
        this.repository = repository;
        this.tester = tester;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    public String getInput() {
        return input;
    }

    private void setInput(String input) {
        if (input == null || input.trim().equals("")) {
            throw new InvalidInputException(input);
        }
        this.input = input;
    }

    public String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidInputException(input);
        }
        this.data = data;
    }

    protected Database getRepository() {
        return repository;
    }

    protected ContentComparer getTester() {
        return tester;
    }

    protected DirectoryManager getIoManager() {
        return ioManager;
    }

    protected AsynchDownloader getDownloadManager() {
        return downloadManager;
    }
}
