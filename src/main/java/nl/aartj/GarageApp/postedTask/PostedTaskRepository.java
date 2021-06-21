package nl.aartj.GarageApp.postedTask;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostedTaskRepository extends JpaRepository<PostedTask, Long> {
}
