package IO.commands;

import IO.IOManager;
import Judge.Tester;
import Network.DownloadManager;
import Repository.StudentsRepository;
import exceptions.InvalidInputException;

public abstract class Command {

    private String input;
    private String[] data;

    private StudentsRepository repository;
    private Tester tester;
    private IOManager ioManager;
    private DownloadManager downloadManager;

    protected Command(String input,
                   String[] data,
                   StudentsRepository repository,
                   Tester tester,
                   IOManager ioManager,
                   DownloadManager downloadManager) {
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

    protected StudentsRepository getRepository() {
        return repository;
    }

    protected Tester getTester() {
        return tester;
    }

    protected IOManager getIoManager() {
        return ioManager;
    }

    protected DownloadManager getDownloadManager() {
        return downloadManager;
    }

    public abstract void execute() throws Exception;
}
