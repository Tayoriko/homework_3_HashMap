package practice.state;

import java.util.Objects;
import java.util.stream.Stream;

public enum ActionType {

    VOID(-9),
    ERROR(-1),
    EXIT(0),
    NAV(1),
    READ(2),
    WRITE(3),
    SEARCH(4),
    STAT(5),
    LOAD(7),
    SAVE(8),
    BACK(9),
    READ_ALL(10);




    private Integer type;
    ActionType(Integer type){
        this.type = type;
    }


    public Integer getType() {
        return type;
    }


    public static ActionType getActionType(Integer action){
        return Stream.of(ActionType.values()).filter(ActionType -> Objects.equals(ActionType.getType(), action))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Incorrect action: " + action);
                    return ActionType.ERROR;
                });
    }
}
