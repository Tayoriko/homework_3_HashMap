package practive_v2.input;

import practive_v2.GlobalElements;

public class InputCity {
    public Check readCity(String data) {
        Check<String> check = new Check<>(data.trim());
        //Check empty String
        if (check.getValue().isEmpty()) {
            check.setError();
            check.addMessage("Empty input string.");
        }
        //Check correct regex
        if (!check.getValue().matches(GlobalElements.regexCity)) {
            check.setError();
            check.addMessage("Incorect city: " + data + ".");
        }
        //Error
        if (check.isError()) {
            String message = "input.InputCity().readCity() -> ";
            System.out.println(message + check.getMessage() + "Check input data: " + data);
        }
        //Result
        return check;
    }
}
