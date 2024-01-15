package practice.validator;

public class ValidSurname {

    public String checkOneSurname(String data) {
        boolean error = false;
        String errorMessage = "";
        if (data.isEmpty()) {
            error = true;
            errorMessage += "Empty input string. ";
        }
        if (!data.matches(RegexBase.regexName)) {
            error = true;
            errorMessage += "Incorect surname: " + data + ". ";
        }
        if (!error) {
            return data;
        } else {
            String message = "validator.ValidSurname().checkOneSurname() -> ";
            System.out.println(message + errorMessage + "Check input data: " + data);
            return "";
        }
    }

    public String[] checkTwoSurname(String data) {
        boolean error = false;
        String errorMessage = "";
        String[] student = data.split(",");
        if (student.length == 2) {
            for (int i = 0; i < student.length; i++) {
                    //crop void symbols
                    student[i] = student[i].trim();
                    //check empty elements
                    if (student[i].isEmpty()) {
                        error = true;
                        errorMessage += "No data for one of elemts. ";
                    }
                    //check name, surname and city
                    if (!student[i].matches(RegexBase.regexName)) {
                        error = true;
                        if (i == 0) errorMessage += "Incorect name: " + student[i] + ". ";
                        if (i == 1) errorMessage += "Incorrect surname: " + student[i] + ". ";
                    }
                }
            }
            if (!error) {
                return student;
            } else {
                String message = "validator.ValidSurname().checkOneSurname() -> ";
                System.out.println(message + errorMessage + "Check input data: " + data);
                return new String[]{"", ""};
            }
        }
    }

