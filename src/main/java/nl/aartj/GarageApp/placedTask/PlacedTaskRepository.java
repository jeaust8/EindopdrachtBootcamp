package nl.aartj.GarageApp.placedTask;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedTaskRepository extends JpaRepository<PlacedTask, Long> {
}
