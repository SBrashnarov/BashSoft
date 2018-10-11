package IO.commands;

import contracts.*;
import exceptions.InvalidInputException;

public abstract class Command implements Executable {

    private String input;
    private String[] data;

    protected Command(String input, String[] data) {
        this.setInput(input);
        this.setData(data);
    }

    protected String getInput() {
        return input;
    }

    private void setInput(String input) {
        if (input == null || input.trim().equals("")) {
            throw new InvalidInputException(input);
        }
        this.input = input;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidInputException(input);
        }
        this.data = data;
    }
}
