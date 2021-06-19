package nl.aartj.GarageApp.placedTask;


import nl.aartj.GarageApp.customer.CustomerAccount;
import nl.aartj.GarageApp.customer.CustomerAccountRepository;
import nl.aartj.GarageApp.product.Product;
import nl.aartj.GarageApp.product.ProductRepository;
import nl.aartj.GarageApp.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlacedTaskService {

    @PersistenceContext
    private EntityManager entityManager;
    private final PlacedTaskRepository placedTaskRepository;
    private final ProductRepository productRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomerAccountRepository customerAccountRepository;

    private ArrayList<Product> orderedProducts;

    private static long placedTaskIdCounter = 0;
    private static long placedTaskDetailsIdCounter = 0;
    private static long lastTaskId = 0;

    public static synchronized long createPlacedTaskId(){
        placedTaskIdCounter++;
        return placedTaskIdCounter;
    }
    public static synchronized long createPlacedTaskDetailsId(){
        return placedTaskDetailsIdCounter++;
    }

    @Autowired
    public PlacedTaskService(PlacedTaskRepository placedTaskRepository, ProductRepository productRepository, UserDetailsServiceImpl userDetailsService, CustomerAccountRepository customerAccountRepository, ArrayList<Product> orderedProducts){
        this.placedTaskRepository = placedTaskRepository;
        this.productRepository = productRepository;
        this.userDetailsService = userDetailsService;
        this.customerAccountRepository = customerAccountRepository;
        this.orderedProducts = orderedProducts;
    }

    public List<PlacedTask> getTasks() {
        ArrayList<PlacedTask> placedTasks = new ArrayList<> ();
        placedTasks.addAll(placedTaskRepository.findAll());
        int i = 0;
        for (PlacedTask placedTask : placedTasks) {
            if (placedTask.getStatus().equalsIgnoreCase("task_finished")) {
                placedTasks.remove(i);
            }
            i++;
        }
        return placedTasks;
    }
    public List<Product> getProducts(){
        return orderedProducts;
    }

    public String addNewProduct(Long productId){
        System.out.println(productId);
        Optional<Product> productFind = productRepository.findById(productId);
        Product product = productFind.get();

        orderedProducts.add(product);

        Collections.sort(orderedProducts, new SortProducts());
        Collections.reverse(orderedProducts);

        return product.getTitle() + " is toegevoegd.";
    }

    @Transactional
    public ResponseEntity placeTask(boolean customerConsent, String payment){
        PlacedTask newTask = new PlacedTask();
        PlacedTaskDetails newTaskDetails = new PlacedTaskDetails();

        double price = 0;
        int amount = 0;
        Long customerId;
        Optional<CustomerAccount> customerAccountOptional = customerAccountRepository.findCustomerByEmail(userDetailsService.userEmail);
        if(customerAccountOptional.isPresent()){
            customerId = customerAccountOptional.get().getId();
        }
        else {
            throw new IllegalStateException("KlantId niet gevonden.");
        }
        if (payment.equalsIgnoreCase("card")|| payment.equalsIgnoreCase("cash")){
            newTask.setPayment(payment);
        }
        else{
            throw new PaymentNotKnownException(payment + " is geen geldig betaalmiddel. Kies uit contant of per pin.");
        }

        String dateFormat = "dd.MM.yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        String date = dateTimeFormatter.format(LocalDate.now());

        for(Product product : placedTasks){
            price = price + product.getPrice();
            amount++;
        }

        newTask.setId(createPlacedTaskId());
        System.out.println("TaakId: " + newTask.getId());
        newTask.setOrderDate(date);
        newTask.setCustomerId(customerId);
        newTask.setStatus("Task_Placed");

        System.out.println(newTask.getPayment());

        if (newTask.getAmount() > 0){
            placedTaskRepository.save(newTask);

            long lastId = 0L;
            int lastQuantity;
            for (Product product: orderedProducts){
                if(product.getId() == lastId && newTask.getId() != lastTaskId){
                    int quantity = lastQuantity + 1;
                    entityManager.createNativeQuery("UPDATE placed_task_details SET quantity = ? WHERE placed_task_id = ? AND product_id =?")
                            .setParameter(1, quantity)
                            .setParameter(2, newTask.getId())
                            .setParameter(3, product.getId())
                            .executeUpdate();

                    lastId = newTaskDetails.getProductId();
                    lastQuantity = quantity;
                }
                else{
                    newTaskDetails.setProductId(product.getId());
                    newTaskDetails.setPlacedTaskId(newTask.getId());
                    newTaskDetails.setQuantity(1);

                    enitityManager.createNativeQuery("INSERT INTO placed_task_details (id, product_id, placed_task_id, quantity) VALUES (?,?,?,?)")
                            .setParameter(1, createPlacedTaskDetailsId())
                            .setParameter(2, newTaskDetails.getProductId())
                            .setParameter(3, newTaskDetails.getPlacedTaskId())
                            .setParameter(4, newTaskDetails.getQuantity())
                            .executeUpdate();
                    lastId = newTaskDetails.getProductId();
                    lastQuantity = 1;
                    System.out.println(lastQuantity);
                }
            }

            lastTaskId = newTask.getId();

            newTask.setId(null);
            newTask.setAmount(0.00F);
            newTask.setTaskDate(null);
            newTask.setCustomerId(null);
            newTask.setCustomerConsent(false);
            newTask.setPayment(null);
            orderedProducts.clear();

            return new ResponseEntity<>("Taak succesvol verwerkt!", HttpStatus.OK);
        }
        else{
            placedTaskIdCounter = placedTaskIdCounter - 1;
            return new ResponseEntity<>("Geen producten / diensten gevonden in taak.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Transactional
    public String readyTask(Long taskId){
        Optional<PlacedTask> taskDetails = placedTaskRepository.findById(taskId);

        PlacedTask task = taskDetails.get();

        task.setStatus("klaar_voor_ophalen");

        entityManager.createNativeQuery("UPDATE placed_task SET status = ? WHERE id = ?")
                .setParameter(1, task.getStatus())
                .setParameter(2, task.getId())
                .executeUpdate();

        return "Status is aangepast.";
    }
}
