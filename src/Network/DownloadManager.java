package Network;

import IO.OutputWriter;
import StaticData.ExceptionMessages;
import StaticData.SessionData;
import contracts.AsynchDownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadManager implements AsynchDownloader {

    public void download(String fileURL) {

        URL url;
        ReadableByteChannel readableByteChannel = null;
        FileOutputStream fileOutputStream = null;

        try {
            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Started downloading..");
            }
            url = new URL(fileURL);
            readableByteChannel = Channels.newChannel(url.openStream());

            String fileName = extractNameOfFile(fileURL);
            File file = new File(SessionData.currentPath + "/resources/" + fileName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Download complete.");
            }
        } catch (MalformedURLException e) {
            OutputWriter.displayException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (readableByteChannel != null) {
                    readableByteChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadOnNewThread(String fileUrl) {
        Thread thread = new Thread(() -> download(fileUrl));
        OutputWriter.writeMessageOnNewLine(
                String.format("Worker thread %d started download..", thread.getId())
        );
        thread.start();
    }

    private String extractNameOfFile(String fileURL) throws MalformedURLException {
        int indexOfLastSlash = fileURL.lastIndexOf('/');
        if (indexOfLastSlash == -1) {
            throw new MalformedURLException(ExceptionMessages.INVALID_PATH);
        }
        return fileURL.substring(indexOfLastSlash + 1);
    }
}
