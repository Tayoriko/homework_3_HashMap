package practice.validator;


public class ValidInteger {
    public int readInteger(String code){
        try {
            Integer value = Integer.valueOf(code.trim());
            if (value >= 0) return value;
            else return -1;
        } catch (Exception e){
            System.out.println("Invalid input value: " + code + " - exception: " + e.getMessage());
            return -1;
        }
    }
}
