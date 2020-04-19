package search;

public class State {

    private String id;

    public State(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object s) {

        if (!(s instanceof State)){
            return false;
        }

        return ((State) s).getId().equals(id);
    }

    @Override
    public String toString() {
        return "State(" + id + ")";
    }
}
