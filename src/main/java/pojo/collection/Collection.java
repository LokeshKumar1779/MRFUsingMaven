package pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {

    private Info info;
    private List<ItemMain> itemMainList;
    private String id;

    public Collection() {
    }

    public Collection(Info info, List<ItemMain> itemMains) {
        this.info = info;
        this.itemMainList = itemMains;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<ItemMain> getItem() {
        return itemMainList;
    }

    public void setItem(List<ItemMain> itemMains) {
        this.itemMainList = itemMains;
    }
}
