package response.todos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TodoResponse{

    @JsonProperty("total")
    private int total;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("skip")
    private int skip;

    @JsonProperty("todos")
    private List<TodosItem> todos;

    public void setTotal(int total){
        this.total = total;
    }

    public int getTotal(){
        return total;
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public int getLimit(){
        return limit;
    }

    public void setSkip(int skip){
        this.skip = skip;
    }

    public int getSkip(){
        return skip;
    }

    public void setTodos(List<TodosItem> todos){
        this.todos = todos;
    }

    public List<TodosItem> getTodos(){
        return todos;
    }
}