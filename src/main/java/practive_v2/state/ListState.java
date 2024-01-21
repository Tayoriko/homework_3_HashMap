package practive_v2.state;

import practive_v2.GlobalElements;

public enum ListState {

    MAIN(0, "===> Main menu"),
    RECORDS(1, "===> Students menu"),
    STATS(1, "===> Statistics menu");

    private Integer layer;
    private String name;
    ListState(Integer layer, String name){
        this.layer = layer;
        this.name = name;
    }

    protected Integer getLayer() {
        return this.layer;
    }

    protected String getName() {
        return GlobalElements.LN + name;
    }
}
