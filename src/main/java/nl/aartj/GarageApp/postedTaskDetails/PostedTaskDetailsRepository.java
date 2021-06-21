package nl.aartj.GarageApp.postedTaskDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostedTaskDetailsRepository extends JpaRepository<PostedTaskDetails, Long> {
}
