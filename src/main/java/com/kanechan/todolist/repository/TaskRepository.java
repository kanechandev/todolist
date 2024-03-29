package com.kanechan.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kanechan.todolist.model.Task;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long>{

	@Query("SELECT a FROM Task a WHERE UPPER(TRIM(a.descricao)) LIKE %?1%")
	List<Task> buscarTaskDesc(String desc);

	@Modifying
	@Query("UPDATE Task t SET t.isCompletado = :isCompletado WHERE t.id = :id")
	int atualizarTaskStatus(@Param("id") Long id, @Param("isCompletado") boolean isCompletado);

}
