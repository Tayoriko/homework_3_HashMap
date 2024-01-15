package practice.validator;

public class ValidStudent {

    public String checkDataOfStudent(String data){
        boolean error = false;
        String errorMessage = "";
        if (data.isEmpty()) {error = true; errorMessage += "Empty input string. ";}
        //sort elements to array
        String[] student = data.split(",");
        //check array length
        if (student.length == 5) {
            //check data
            for (int i = 0; i < student.length; i++) {
                //crop void symbols
                student[i] = student[i].trim();
                //check empty elements
                if (student[i].isEmpty()) {
                    error = true;
                    errorMessage += "No data for one of elemts. ";
                }
                //check name, surname and city
                if (i < 2) {
                    if (!student[i].matches(RegexBase.regexName)) {
                        error = true;
                        if (i == 0) errorMessage += "Incorect name: " + student[i] + ". ";
                        if (i == 1) errorMessage += "Incorrect surname: " + student[i] + ". ";
                    }
                }
            }
            if (!student[2].matches(RegexBase.regexCity)) {
                error = true;
                errorMessage += "Incorrect city: " + student[2] + ". ";
            }
            //check coruse
            if (!student[3].matches(RegexBase.regexCourse)) {
                error = true;
                errorMessage += "Incorrect course: " + student[3] + ". ";
            }
            //check old
            if (!student[4].matches(RegexBase.regexOld)) {
                error = true;
                errorMessage += "Incorrect old: " + student[4] + ". ";
            }
        } else  {error = true; errorMessage = "Incorrect data, avoid information. ";}
        if (!error) {
            return data;
        } else {
            String message = "validator.ValidStudent().checkDataOfStudent() -> ";
            System.out.println(message + errorMessage + "Check input data: " + data);
            return "";
        }
    }
}
