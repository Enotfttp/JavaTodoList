package Controller;

import Repository.TodoRepository;
import Service.TodoService;

import java.time.LocalDate;
import java.util.Scanner;


public class TodoController {

    private TodoService todoService;


    public void chooseAction() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберете команду, которую вы хотите выполнить");
        String str = scanner.next();


        switch(str){
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
                 throw new Exception("Такого действия нет!:" + str);
        }
    }


    final void showTasks() {
        System.out.println("Список задач:");
        todoService.showTasks();
    }


    final void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название задачи:");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        System.out.println("Введите год, месяц (число) и день (число), через пробел:");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        LocalDate localDate = LocalDate.of(year, month, day);

        TodoRepository task = new TodoRepository(name, description, localDate);

        todoService.addTask(task);
    }

    final void editTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задачи:");
        Integer numberTask = scanner.nextInt();


        System.out.println("Введите название задачи:");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        System.out.println("Введите год, месяц (число) и день (число), через пробел:");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        LocalDate localDate = LocalDate.of(year, month, day);

        TodoRepository task = new TodoRepository(name, description, localDate);
        try{
            System.out.println(todoService.editTask(numberTask, task));
        }catch(Exception e){
            System.out.println(e);
        }

    }
     final void deleteTask(){
         Scanner scanner = new Scanner(System.in);

         System.out.println("Введите номер задачи:");
         Integer numberTask = scanner.nextInt();
         try{
             System.out.println(todoService.deleteTask(numberTask));
         }catch(Exception e){
             System.out.println(e);
         }
     }

     final void filterTask(){
         Scanner scanner = new Scanner(System.in);

         System.out.println("Введите по какому статусы вы хотите фильтровать");
         String status = scanner.nextLine();

         try{
             todoService.filterTask(status);
         }catch (Exception e){
             System.out.println(e);
         }

     }

     final void sortTask(){
         Scanner scanner = new Scanner(System.in);

         System.out.println("Введите поле по которому хотите сортировать статус | дата");
         String typeOfSort = scanner.nextLine();

         try{
             todoService.sortTask(typeOfSort);
         }catch (Exception e){
             System.out.println(e);
         }
     }

     final void exitTask(){
         System.out.println("Завершение работы");
         todoService.exitTask();
     }


}
