package practice.validator;

public class RegexBase {
    public static final String regexName = "^[A-Z][a-z]*(-[A-Z][a-z]*)?$";
    public static final String regexCity = "^[A-Za-z]+(?:[ -][A-Za-z]+)*$";
    public static final String regexCourse = "^[a-zA-Z#0-9+-]{1,10}$";
    public static final String regexOld = "^(?:[1-9]|[1-9][0-9])$";
}
