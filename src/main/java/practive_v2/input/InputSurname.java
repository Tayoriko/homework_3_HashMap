package practive_v2.input;

import practive_v2.GlobalElements;

public class InputSurname {
    public Check readOneSurname(String data) {
        Check<String> check = new Check<>(data.trim());
        //Check empty String
        if (check.getValue().isEmpty()) {
            check.setError();
            check.addMessage("Empty input string.");
        }
        //Check correct regex
        if (!check.getValue().matches(GlobalElements.regexName)) {
            check.setError();
            check.addMessage("Incorect surname: " + data + ".");
        }
        //Error
        if (check.isError()) {
            String message = "input.InputSurname().readOneSurname() -> ";
            System.out.println(message + check.getMessage() + "Check input data: " + data);
        }
        //Result
        return check;
    }
}
