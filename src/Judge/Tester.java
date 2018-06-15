package Judge;

import IO.OutputWriter;
import StaticData.ExceptionMessages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tester {

    public void compareContent(String actualOutput, String expectedOutput) throws IOException {
        OutputWriter.writeMessageOnNewLine("Reading files...");

        try {
            String mismatchPath = getMismatchPath(expectedOutput);

            List<String> actualOutputString = readTextFile(actualOutput);
            List<String> expectedOutputString = readTextFile(expectedOutput);

            boolean mismatch = compareStrings(actualOutputString, expectedOutputString, mismatchPath);

            if (mismatch) {
                List<String> mismatchString = readTextFile(mismatchPath);

                for (String difference : mismatchString) {
                    OutputWriter.writeMessageOnNewLine(difference);
                }
            } else {
                OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
            }
        } catch (IOException ioe) {
            throw new IOException(ExceptionMessages.INVALID_PATH);
        }
    }

    private String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String folderPath = expectedOutput.substring(0, index);

        return folderPath + "\\mismatch.txt";
    }

    private List<String> readTextFile(String filePath) throws IOException {
        List<String> text = new ArrayList<>();

        File file = new File(filePath);

        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();

            while (reader.readLine() != null) {
                text.add(line);
            }

        } catch (IOException ioe) {
            throw new IOException(ExceptionMessages.INVALID_PATH);
        }

        return text;
    }



    private boolean compareStrings(
            List<String> actualOutputString,
            List<String> expectedOutputString,
            String mismatchPath) {
        OutputWriter.writeMessageOnNewLine("Comparing files...");
        boolean isMismatch = false;

        try {
            FileWriter fileWriter = new FileWriter(mismatchPath);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());

            String output;
            for (int i = 0; i < maxLength; i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if (!actualLine.equals(expectedLine)) {
                    output = String
                            .format("mismatch -> expected(%s), actual(%s) %n"
                                    , expectedLine
                                    , actualLine);
                    isMismatch = true;
                } else {
                    output = String.format("line match -> %s%n", actualLine);
                }

                writer.write(output);
            }

            writer.close();
        } catch (IOException e) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.CANNOT_ACCESS_FILE);
        } catch (IndexOutOfBoundsException e) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.INVALID_OUTPUT_LENGTH);
        }

        return isMismatch;
    }
}
