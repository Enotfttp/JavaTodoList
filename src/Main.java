import Controller.TodoController;

public class Main {
    public static void main(String[] args)  {
        TodoController todoController = new TodoController();
        try{
            todoController.chooseAction();
        }catch (Exception e){
            System.out.println("Ошибка:" + e);
        }
    }
}