package warehause;

import java.util.Map;
import java.util.Set;

record TypeAndPlace (ListWType type, String place){}

public interface Analitics {
    //get vendor list on wh
    Set<ListWVendor> getVendor();
    //get items on wh by place
    Map<TypeAndPlace, Integer> getItemOnPlace(ListWType type, String place);
    //get all item cnt in wh
    Integer getAllCnt();
}
