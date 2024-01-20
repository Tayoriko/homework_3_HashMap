package practive_v2.input;

import practive_v2.RecordsInBase;

public class InputRecord {

    public Check checkRecordInput(String data){
        Check<String> checkData = new Check<>(data.trim());
        String[] array = data.split(",");
        Check<String>[] resultOfCheck = new Check[5];
        //Check void String
        if (data.isEmpty()) {
            checkData.setError();
            checkData.addMessage("Empty input string.");
        }
        //Check symbols in String
        else {
            //Check array length
            if (array.length == 5) {
                resultOfCheck[0] = new InputSurname().readOneSurname(array[0]);
                resultOfCheck[1] = new InputSurname().readOneSurname(array[1]);
                resultOfCheck[2] = new InputCity().readCity(array[2]);
                resultOfCheck[3] = new InputCourse().readCourse(array[3]);
                resultOfCheck[4] = new InputInteger().readOld(array[4]);
                for (int i = 0; i < array.length; i++) {
                    if (resultOfCheck[i].isError()) checkData.setError();
                }
            } else {
                checkData.setError();
                checkData.addMessage("Incorrect string length.");
            }
        }
        //Success
        if (!checkData.isError()){
            return new Check<>(new RecordsInBase(resultOfCheck));
        }
        return checkData;
    }

    public Check checkRecordFile(String data){
        Check<RecordsInBase> check = null;
        Check<Boolean> checkData = new Check<>(false);
        String[] array = data.split(",");
        Check<String>[] resultOfCheck = new Check[5];
        Check<Integer> resultOfCheckID = null;
        //Check void String
        if (data.isEmpty()) {
            checkData.setError();
            checkData.addMessage("Empty input string.");
        }
        //Check symbols in String
        else {
            //Check array length
            if (array.length == 6) {
                resultOfCheckID = new InputInteger().readInteger(array[0]);
                resultOfCheck[0] = new InputSurname().readOneSurname(array[1]);
                resultOfCheck[1] = new InputSurname().readOneSurname(array[2]);
                resultOfCheck[2] = new InputCity().readCity(array[3]);
                resultOfCheck[3] = new InputCourse().readCourse(array[4]);
                resultOfCheck[4] = new InputInteger().readOld(array[5]);
                for (int i = 1; i < array.length; i++) {
                    if (resultOfCheck[i-1].isError()) check.setError();
                }
            } else {
                checkData.setError();
                checkData.addMessage("Incorrect string length.");
            }
        }
        //Success
        if (!checkData.isError()){
            check = new Check<>(new RecordsInBase(resultOfCheck));
            check.getValue().setId(resultOfCheckID.getValue());
            return check;
        }
        return checkData;
    }
}
