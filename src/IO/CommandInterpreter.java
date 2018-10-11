package IO;

import annotations.Alias;
import annotations.Inject;
import contracts.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreter implements Interpreter {

    private static final String COMMANDS_LOCATION = "src/IO/commands/";
    private static final String COMMANDS_PACKAGE = "IO.commands.";

    private ContentComparer tester;
    private Database repository;
    private AsynchDownloader downloadManager;
    private DirectoryManager inputOutputManager;

    public CommandInterpreter(ContentComparer tester,
                              Database repository,
                              AsynchDownloader downloadManager,
                              DirectoryManager inputOutputManager) {
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.inputOutputManager = inputOutputManager;
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();

        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }
    }

    private Executable parseCommand(String input, String[] data, String command) {

        File commandDirectory = new File(COMMANDS_LOCATION);
        Executable executable = null;

        for (File file : commandDirectory.listFiles()) {
            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            try {
                String className = file.getName()
                        .substring(0, file.getName().lastIndexOf("."));

                Class<Executable> exeClass = (Class<Executable>) Class.forName(COMMANDS_PACKAGE + className);

                if (!exeClass.isAnnotationPresent(Alias.class)) {
                    continue;
                }
                Alias alias = exeClass.getAnnotation(Alias.class);
                String value = alias.value();

                if (!value.equalsIgnoreCase(command)) {
                    continue;
                }

                Constructor<Executable> executableConstructor =
                        exeClass.getConstructor(String.class, String[].class);
                executable = executableConstructor.newInstance(input, data);

                this.injectDependencies(executable, exeClass);
            } catch (ReflectiveOperationException rfe) {
                rfe.printStackTrace();
            }
        }
        return executable;
    }

    private void injectDependencies(Executable executable,
                                    Class<Executable> exeClass)
            throws ReflectiveOperationException {

        Field[] fieldsToSet = exeClass.getDeclaredFields();
        for (Field fieldToSet : fieldsToSet) {
            if (!fieldToSet.isAnnotationPresent(Inject.class)) {
                continue;
            }

            fieldToSet.setAccessible(true);

            Field[] interpreterFields = CommandInterpreter.class.getDeclaredFields();
            for (Field interpreterField : interpreterFields) {
                if (!interpreterField.getType().equals(fieldToSet.getType())) {
                    continue;
                }
                interpreterField.setAccessible(true);
                fieldToSet.set(executable, interpreterField.get(this));
            }
        }
    }
}
