import IO.CommandInterpreter;
import IO.IOManager;
import IO.InputReader;
import IO.OutputWriter;
import Judge.Tester;
import Network.DownloadManager;
import Repository.RepositoryFilter;
import Repository.RepositorySorter;
import Repository.StudentsRepository;
import contracts.*;

public class Program {
    public static void main(String[] args) {

        ContentComparer tester = new Tester();
        AsynchDownloader downloadManager = new DownloadManager();
        DirectoryManager ioManager = new IOManager();
        DataSorter sorter = new RepositorySorter();
        DataFilter filter = new RepositoryFilter();
        Database repository = new StudentsRepository(filter, sorter);
        Interpreter currentInterpreter =
                new CommandInterpreter(
                        tester,
                        repository,
                        downloadManager,
                        ioManager
                );
        Reader reader = new InputReader(currentInterpreter);

        try {
            reader.readCommands();
        }catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
