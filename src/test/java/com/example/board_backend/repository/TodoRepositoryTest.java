package com.example.board_backend.repository;

import com.example.board_backend.domain.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoRepositoryTest {

    TodoRepository sut;

    @Autowired
    public TodoRepositoryTest(TodoRepository todoRepository) {
        this.sut = todoRepository;
    }

    @Test
    void givenTodo_whenSavingTodo_thenSavesTodo() {
        // Given
        Todo todo = createTodo();

        // When
        sut.save(todo);

        // Then
        long count = sut.count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    void givenPageable_whenSearchingTodos_thenReturnsTodosAsPage() {
        // Given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        // When
        Page<Todo> todos = sut.findAll(pageable);

        // Then
        assertThat(todos).isNotNull();
    }

    private Todo createTodo() {
        return createTodo(1L);
    }

    private Todo createTodo(Long id) {
        Todo todo = Todo.builder()
                .content("Test content")
                .isDone(true)
                .dueDate(LocalDate.now().plusDays(7))
                .build();

        ReflectionTestUtils.setField(todo, "id", id);

        return todo;
    }

}