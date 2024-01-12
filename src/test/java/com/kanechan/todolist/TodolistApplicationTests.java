package com.kanechan.todolist;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kanechan.todolist.controller.TaskController;
import com.kanechan.todolist.model.Task;
import com.kanechan.todolist.repository.TaskRepository;
import com.kanechan.todolist.util.ExceptionToDoListJava;

@Profile("test")
@SpringBootTest(classes = TodolistApplication.class)
class TodolistApplicationTests {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskController taskController;

    @Test
    public void testSalvarTask() throws ExceptionToDoListJava {
        Task task = new Task();
        task.setDescricao("Teste de Tarefa com Test");

        // Chame o método salvarTask no controlador
        ResponseEntity<Task> responseEntity = taskController.salvarTask(task);

        // Verifique se a resposta foi bem-sucedida (código HTTP 200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtenha a tarefa salva do corpo da resposta
        Task savedTask = responseEntity.getBody();

        // Verifique se a tarefa foi salva corretamente no banco de dados
        assertNotNull(savedTask.getId());

        // Use o Awaitility para esperar até que a tarefa seja persistida no banco de dados
        await().atMost(Duration.ofSeconds(5)).until(() -> taskRepository.findById(savedTask.getId()).isPresent());

        // Verifique se a tarefa está no banco de dados
        assertTrue(taskRepository.findById(savedTask.getId()).isPresent());

    }

}
