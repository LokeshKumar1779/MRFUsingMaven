package pojo.collection;

import java.util.List;

public class ItemMain {

    private String name;
    private List<Item> item;

    public ItemMain() {
    }

    public ItemMain(String name, List<Item> item) {
        this.name = name;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
