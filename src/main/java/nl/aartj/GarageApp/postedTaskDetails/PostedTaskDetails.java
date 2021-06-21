package nl.aartj.GarageApp.postedTaskDetails;

import javax.persistence.*;

@Entity
@Table
public class PostedTaskDetails {

    @Id
    @SequenceGenerator(name = "placedTaskDetail_sequence", sequenceName = "placedTaskDetail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placedTaskDetail_sequence")
    private Long Id;
    private Long postedTaskId;
    private Long productId;


    public PostedTaskDetails() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long taskId) {
        this.Id = taskId;
    }

    public Long getPostedTaskId() {
        return postedTaskId;
    }

    public void setPostedTaskId(Long postedTaskId) {
        this.postedTaskId = postedTaskId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

   }
