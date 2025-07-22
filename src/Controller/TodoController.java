package Controller;

import Repository.TodoRepository;
import Service.TodoService;

import java.time.LocalDate;
import java.util.Scanner;


public class TodoController {

    private final TodoService todoService = new TodoService();


    public void chooseAction() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Напишите команду, которую вы хотите выполнить");
        System.out.println("add | list | edit | delete | filter | sort | exit");
        System.out.print("Команда: ");

        String str = scanner.next();

        switch (str) {
            case "list":
                this.showTasks();
                break;
            case "add":
                this.addTask();
                break;
            case "edit":
                this.editTask();
                break;
            case "delete":
                this.deleteTask();
                break;
            case "filter":
                this.filterTask();
                break;
            case "sort":
                this.sortTask();
                break;
            case "exit":
                this.exitTask();
                break;
            default:
                this.chooseAction();
                throw new Exception("Такого действия нет!: " + str);
        }
    }


    final void showTasks() throws Exception {
        System.out.println("Список задач:");
        System.out.println("----------------------");
        todoService.showTasks();
        this.chooseAction();
    }


    final void addTask() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название задачи: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.print("Введите год, месяц (число) и день (число), через пробел: ");

        try {
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();
            LocalDate localDate = LocalDate.of(year, month, day);
            TodoRepository task = new TodoRepository(name, description, localDate);
            todoService.addTask(task);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        } finally {
            this.chooseAction();
        }
    }

    final void editTask() throws Exception {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите номер задачи: ");
            Integer numberTask = scanner.nextInt();
            scanner.nextLine();
            todoService.checkHasTask(numberTask);

            System.out.println();
            System.out.print("Введите название задачи: ");
            String name = scanner.nextLine();
            System.out.println();
            System.out.print("Введите описание задачи: ");
            String description = scanner.nextLine();
            System.out.println();
            System.out.println("Введите один из статусов");
            System.out.println("TODO | IN_PROGRESS | DONE");
            System.out.print("Новый статус: ");
            String status = scanner.nextLine();
            System.out.println();
            System.out.print("Введите год, месяц (число) и день (число), через пробел: ");
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();
            LocalDate localDate = LocalDate.of(year, month, day);
            TodoRepository task = new TodoRepository(name, description, localDate, status);
            todoService.editTask(numberTask, task);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.chooseAction();
        }
    }

    final void deleteTask() throws Exception {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите номер задачи: ");
            Integer numberTask = scanner.nextInt();
            todoService.checkHasTask(numberTask);
            System.out.println();
            todoService.deleteTask(numberTask);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.chooseAction();
        }
    }

    final void filterTask() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите по какому статусы вы хотите фильтровать: ");
        String status = scanner.nextLine();
        System.out.println();

        try {
            todoService.filterTask(status);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.chooseAction();
        }
    }

    final void sortTask() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите поле по которому хотите сортировать статус | дата: ");
        String typeOfSort = scanner.nextLine();
        System.out.println();

        try {
            todoService.sortTask(typeOfSort);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.chooseAction();
        }
    }

    final void exitTask() {
        System.out.println("Завершение работы!");
        todoService.exitTask();
    }
}
