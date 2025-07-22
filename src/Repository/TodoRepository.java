package Repository;

import java.time.LocalDate;

public class TodoRepository {
   private String nameTask;
   private String descriptionTask;
   private LocalDate localDateTask;
   private EStatus status = EStatus.TODO;


   public TodoRepository(String nameTask, String descriptionTask, LocalDate localDateTask){
      this.nameTask = nameTask;
      this.descriptionTask = descriptionTask;
      this.localDateTask = localDateTask;
   }

   public  String getTodoName(){
      return this.nameTask;
   }
   public  String getTodoDescription(){
      return this.descriptionTask;
   }

   public LocalDate getTodoLocalDate(){
      return this.localDateTask;
   }

   public EStatus getTodoStatus(){
      return this.status;
   }

   public void setTodoName(String val){
       this.nameTask = val;
   }
   public void setTodoDescription(String val){
       this.descriptionTask = val;
   }

   public void setTodoLocalDate(LocalDate val){
       this.localDateTask = val;
   }

   public void setTodoStatus(EStatus val){
       this.status = val;
   }
}


