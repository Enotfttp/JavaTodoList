import Repository.TodoRepository;
import Service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для TodoService")
public class TodoServiceTest {

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoService();
    }

    @Test
    @DisplayName("Тест метода addTask - добавление задачи")
    void testAddTask() {
        TodoRepository newTask = new TodoRepository(
                "Изучить JUnit",
                "Написать тесты для TodoService",
                LocalDate.now()
        );

        // Просто вызываем метод - он не возвращает значение
        assertDoesNotThrow(() -> {
            todoService.addTask(newTask);
        }, "Метод addTask не должен выбрасывать исключения");
    }

    @Test
    @DisplayName("Тест метода checkHasTask - проверка несуществующей задачи")
    void testCheckHasTaskThrowsException() {
        Integer nonExistentTaskId = 999;

        Exception exception = assertThrows(Exception.class, () -> {
            todoService.checkHasTask(nonExistentTaskId);
        });

        assertTrue(exception.getMessage().contains("Такого номера не существует"),
                "Сообщение об ошибке должно содержать правильный текст");
    }

    @Test
    @DisplayName("Тест метода deleteTask - удаление несуществующей задачи")
    void testDeleteTask() {
        TodoRepository newTask = new TodoRepository(
                "Изучить JUnit",
                "Написать тесты для TodoService",
                LocalDate.now()
        );

        todoService.addTask(newTask);

        Integer curNumber = newTask.getTodoNumberTask();

        // Метод deleteTask не проверяет существование задачи перед удалением
        assertDoesNotThrow(() -> {
            todoService.deleteTask(curNumber);
        }, "Метод deleteTask не должен выбрасывать исключения");
    }

    @Test
    @DisplayName("Тест метода sortTaskStatus - получение отсортированного списка по статусу")
    void testSortTaskStatus() {
        List<TodoRepository> sortedTasks = todoService.sortTaskStatus();

        assertNotNull(sortedTasks, "Метод должен возвращать не null");
        assertInstanceOf(List.class, sortedTasks, "Метод должен возвращать List");
    }

    @Test
    @DisplayName("Тест метода sortTaskLocalDate - получение отсортированного списка по дате")
    void testSortTaskLocalDate() {
        List<TodoRepository> sortedTasks = todoService.sortTaskLocalDate();

        assertNotNull(sortedTasks, "Метод должен возвращать не null");
        assertInstanceOf(List.class, sortedTasks, "Метод должен возвращать List");
    }

    @Test
    @DisplayName("Тест метода filterTask - фильтрация с неправильным статусом")
    void testFilterTaskInvalidStatus() {
        String invalidStatus = "INVALID_STATUS";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.filterTask(invalidStatus);
        });

        assertTrue(exception.getMessage().contains("Данного статуса нет в списке статусов"),
                "Сообщение об ошибке должно содержать правильный текст");
    }

    @Test
    @DisplayName("Тест метода filterTask - фильтрация с правильным статусом")
    void testFilterTaskValidStatus() {
        // Given
        String validStatus = "TODO";

        // When & Then
        assertDoesNotThrow(() -> {
            todoService.filterTask(validStatus);
        }, "Метод filterTask не должен выбрасывать исключения для валидного статуса");
    }

    @Test
    @DisplayName("Тест метода sortTask - сортировка с неправильным параметром")
    void testSortTaskInvalidParameter() {
        // Given
        String invalidSort = "неправильный_параметр";

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> {
            todoService.sortTask(invalidSort);
        });

        assertTrue(exception.getMessage().contains("По данному параметру сортировки нет"),
                "Сообщение об ошибке должно содержать правильный текст");
    }

    @Test
    @DisplayName("Тест метода sortTask - сортировка по дате")
    void testSortTaskByDate() {
        // Given
        String sortByDate = "дата";

        // When & Then
        assertDoesNotThrow(() -> {
            todoService.sortTask(sortByDate);
        }, "Метод sortTask не должен выбрасывать исключения для сортировки по дате");
    }

    @Test
    @DisplayName("Тест метода sortTask - сортировка по статусу")
    void testSortTaskByStatus() {
        // Given
        String sortByStatus = "статус";

        // When & Then
        assertDoesNotThrow(() -> {
            todoService.sortTask(sortByStatus);
        }, "Метод sortTask не должен выбрасывать исключения для сортировки по статусу");
    }
}
