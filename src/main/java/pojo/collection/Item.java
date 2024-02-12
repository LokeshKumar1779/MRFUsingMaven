package pojo.collection;

public class Item {

    private String name;
    private Request request;

    public Item() {
    }

    public Item(String name, Request request) {
        this.name = name;
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
