package practive_v2.input;

import practive_v2.GlobalElements;

public class InputInteger {
    public Check readInteger(String data){
        Check<Integer> check = null;
        try {
            check = new Check<>(Integer.valueOf(data.trim()));
        } catch (Exception e){
            check = new Check<>(-1);
            check.setError();
            String message = "input.InputInteger().readInteger() -> ";
            System.out.println(message + "Invalid input value: " + data + " - exception: " + e.getMessage());
        }
        return check;
    }

    public Check readOld(String data) {
        Check<String> check = new Check<>(data.trim());
        //Check empty String
        if (check.getValue().isEmpty()) {
            check.setError();
            check.addMessage("Empty input string.");
        }
        //Check correct regex
        if (!check.getValue().matches(GlobalElements.regexOld)) {
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
