package IO.commands;

import annotations.Alias;
import annotations.Inject;
import contracts.AsynchDownloader;
import exceptions.InvalidInputException;

@Alias("download")
public class DownloadFileCommand extends Command {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadFileCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileUrl = data[1];
        this.downloadManager.download(fileUrl);
    }
}
