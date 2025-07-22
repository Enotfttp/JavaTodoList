package Repository;

import java.time.LocalDate;

public class TodoRepository {
    private String nameTask;
    private String descriptionTask;
    private LocalDate localDateTask;
    private Integer numberTask;
    private EStatus status = EStatus.TODO;


    public TodoRepository(String nameTask, String descriptionTask, LocalDate localDateTask) {
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.localDateTask = localDateTask;
    }

    public TodoRepository(String nameTask, String descriptionTask, LocalDate localDateTask, String status) {
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.localDateTask = localDateTask;
        try {
            this.status = EStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Такого статуса нет: " + status);
        }
    }

    public TodoRepository(String nameTask, String descriptionTask, LocalDate localDateTask, Integer numberTask) {
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.numberTask = numberTask;
        this.localDateTask = localDateTask;
    }

    public String getTodoName() {
        return this.nameTask;
    }

    public String getTodoDescription() {
        return this.descriptionTask;
    }

    public LocalDate getTodoLocalDate() {
        return this.localDateTask;
    }

    public EStatus getTodoStatus() {
        return this.status;
    }

    public Integer getTodoNumberTask() {
        return this.numberTask;
    }

    public void setTodoName(String val) {
        this.nameTask = val;
    }

    public void setTodoDescription(String val) {
        this.descriptionTask = val;
    }

    public void setTodoLocalDate(LocalDate val) {
        this.localDateTask = val;
    }

    public void setTodoStatus(EStatus val) {
        this.status = val;
    }

    public void setTodoNumberTask(Integer val) {
        this.numberTask = val;
    }


    @Override
    public String toString() {
        return ("Задача № " + this.numberTask +
                "\n" +
                "Название задачи: " + this.nameTask +
                "\n" +
                "Описание задачи: " + this.descriptionTask +
                "\n" +
                "Дата создания задачи: " + this.localDateTask +
                "\n" +
                "Статус задачи: " + this.status
        );
    }
}


