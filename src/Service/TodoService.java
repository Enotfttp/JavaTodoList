package Service;

import Repository.EStatus;
import Repository.TodoRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TodoService {
    static HashMap<Integer, TodoRepository> list = new HashMap<Integer, TodoRepository>();

    public void showTasks(){
           list.forEach((key, value) -> System.out.println(key + ": " + value));;
    }

    public TodoRepository addTask(TodoRepository elem){
       return list.put(elem.hashCode(), elem);
    }

    public List<TodoRepository> editTask(Integer numberTask, TodoRepository elem) throws Exception{
        System.out.println("elem: " + elem);
        TodoRepository curTodo = list.get(numberTask);
        if(curTodo != null){
        Stream<TodoRepository> streamOfList = list.values().stream();
       return streamOfList.map(val->{
            if(!val.getTodoDescription().equals(elem.getTodoDescription())){
                val.setTodoDescription(elem.getTodoDescription());
            }
            if(!val.getTodoName().equals(elem.getTodoName())){
                val.setTodoName(elem.getTodoName());
            }
            if(val.getTodoStatus() != elem.getTodoStatus()){
                val.setTodoStatus(elem.getTodoStatus());
            }
            if(!val.getTodoLocalDate().equals(elem.getTodoLocalDate())){
                val.setTodoLocalDate(elem.getTodoLocalDate());
            }
        return val;
        }).collect(Collectors.toList());
        }else{
            throw new Exception("Такого номера нет:" + numberTask);
        }

    }

    public TodoRepository deleteTask(Integer numberTask) throws Exception {
        TodoRepository curTodo = list.get(numberTask);
        if(curTodo != null) {
           return list.remove(numberTask);
        }else{
            throw new Exception("Такого номера нет:" + numberTask);
        }
    }

    public List<TodoRepository> filterTask(String status) throws  IllegalArgumentException{
        Stream<TodoRepository> streamOfList = list.values().stream();
        try{
            return streamOfList.filter(val->val.getTodoStatus() == EStatus.valueOf(status)).collect(Collectors.toList());
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Данного статуса нет в списке статусов");
        }
    }


    public List<TodoRepository> sortTask(String typeOfSort) throws Exception {

        if(typeOfSort == "дата"){
          return  this.sortTaskLocalDate();
        } else if(typeOfSort == "статус"){
          return  this.sortTaskStatus();
        }else{
            throw new Exception("По данному параметру сортировки нет");
        }

    }

    public List<TodoRepository> sortTaskLocalDate(){
        Stream<TodoRepository> streamOfList = list.values().stream();
        return streamOfList.sorted(Comparator.comparing(TodoRepository::getTodoStatus).reversed()).toList();
    }

    public List<TodoRepository> sortTaskStatus(){
        Stream<TodoRepository> streamOfList = list.values().stream();
        return streamOfList.sorted(Comparator.comparing(TodoRepository::getTodoLocalDate).reversed()).toList();
    }

    public void exitTask(){
        System.exit(0);
    }
}
