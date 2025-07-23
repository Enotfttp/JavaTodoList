package Service;

import Repository.EStatus;
import Repository.TodoRepository;

import java.util.*;
import java.util.stream.Stream;

public class TodoService {
    private static final HashMap<Integer, TodoRepository> list = new HashMap<Integer, TodoRepository>();

    public void checkHasTask(Integer numberTask) throws Exception {
        boolean hasTask = list.get(numberTask) != null;

        if (!hasTask) {
            throw new Exception("Такого номера не существует: " + numberTask);
        }
    }

    public void showTasks() {
        Optional.ofNullable(list).ifPresent(el -> el
                .forEach((key, value) ->
                        System.out.println(value + "\n" + "----------------------")
                ));
    }

    public void addTask(TodoRepository elem) {
        Integer numberTask = elem.hashCode();

        TodoRepository curTodo = new TodoRepository(elem.getTodoName(), elem.getTodoDescription(), elem.getTodoLocalDate(), numberTask);
        list.put(numberTask, curTodo);
    }

    public void editTask(Integer numberTask, TodoRepository elem) throws Exception {
        Stream<TodoRepository> streamOfList = list.values().stream();

        streamOfList.map(val -> {
            if (!val.getTodoNumberTask().equals(numberTask)) return val;
            if (!val.getTodoDescription().equals(elem.getTodoDescription()) && !elem.getTodoDescription().isEmpty()) {
                val.setTodoDescription(elem.getTodoDescription());
            }
            if (!val.getTodoName().equals(elem.getTodoName()) && !elem.getTodoName().isEmpty()) {
                val.setTodoName(elem.getTodoName());
            }
            if (val.getTodoStatus() != elem.getTodoStatus() && !elem.getTodoStatus().toString().isEmpty()) {
                val.setTodoStatus(elem.getTodoStatus());
            }
            if (!val.getTodoLocalDate().equals(elem.getTodoLocalDate())) {
                val.setTodoLocalDate(elem.getTodoLocalDate());
            }

            return val;
        }).toList();
    }

    public void deleteTask(Integer numberTask) throws Exception {
        list.remove(numberTask);
    }

    public void filterTask(String status) throws IllegalArgumentException {
        Stream<TodoRepository> streamOfList = list.values().stream();

        try {
            streamOfList.filter(val -> val.getTodoStatus() == EStatus.valueOf(status))
                    .forEach(value -> System.out.println(value + "\n" + "----------------------"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Данного статуса нет в списке статусов!");
        }
    }


    public void sortTask(String typeOfSort) throws Exception {
        System.out.println("typeOfSort: " + typeOfSort);

        if (Objects.equals(typeOfSort, "дата")) {
            this.sortTaskLocalDate().stream()
                    .forEach(el -> System.out.println(el + "\n" + "----------------------"));
        } else if (Objects.equals(typeOfSort, "статус")) {
            this.sortTaskStatus().stream()
                    .forEach(el -> System.out.println(el + "\n" + "----------------------"));
        } else {
            throw new Exception("По данному параметру сортировки нет: " + typeOfSort);
        }
    }

    public List<TodoRepository> sortTaskStatus() {
        Stream<TodoRepository> streamOfList = list.values().stream();
        return streamOfList.sorted(Comparator.comparing(TodoRepository::getTodoStatus).reversed()).toList();
    }

    public List<TodoRepository> sortTaskLocalDate() {
        Stream<TodoRepository> streamOfList = list.values().stream();
        return streamOfList.sorted(Comparator.comparing(TodoRepository::getTodoLocalDate).reversed()).toList();
    }

    public void exitTask() {
        System.exit(0);
    }
}
