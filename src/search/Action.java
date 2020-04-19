package search;

public class Action {

    String id;

    public Action(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Action(" + id + ")";
    }
}
