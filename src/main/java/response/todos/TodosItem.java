package response.todos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodosItem{

    @JsonProperty("todo")
    private String todo;

    @JsonProperty("id")
    private int id;

    @JsonProperty("completed")
    private boolean completed;

    @JsonProperty("userId")
    private int userId;

    public void setTodo(String todo){
        this.todo = todo;
    }

    public String getTodo(){
        return todo;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }
}