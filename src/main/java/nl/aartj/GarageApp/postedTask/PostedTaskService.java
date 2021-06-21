package nl.aartj.GarageApp.postedTask;


import nl.aartj.GarageApp.customer.CustomerAccount;
import nl.aartj.GarageApp.customer.CustomerAccountRepository;
import nl.aartj.GarageApp.postedTaskDetails.PostedTaskDetails;
import nl.aartj.GarageApp.postedTaskDetails.PostedTaskDetailsRepository;
import nl.aartj.GarageApp.product.Product;
import nl.aartj.GarageApp.product.ProductRepository;
import nl.aartj.GarageApp.product.SortProducts;
import nl.aartj.GarageApp.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostedTaskService {

    @PersistenceContext
    private EntityManager entityManager;
    private final PostedTaskRepository postedTaskRepository;
    private final PostedTaskDetailsRepository postedTaskDetailsRepository;
    private final ProductRepository productRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomerAccountRepository customerAccountRepository;

    private ArrayList<Product> addedProductsToTask;
    private Long lastId = 0L;
    private Long detailsId = 1L;





    @Autowired
    public PostedTaskService(PostedTaskRepository postedTaskRepository, ProductRepository productRepository, UserDetailsServiceImpl userDetailsService, CustomerAccountRepository customerAccountRepository, ArrayList<Product> addedProductsToTask, CarRepository carRepository, PostedTaskDetailsRepository postedTaskDetailsRepository, BillRepository billRepository){
        this.postedTaskRepository = postedTaskRepository;
        this.productRepository = productRepository;
        this.userDetailsService = userDetailsService;
        this.customerAccountRepository = customerAccountRepository;
        this.addedProductsToTask = addedProductsToTask;
        this.carRepository = carRepository;
        this.postedTaskDetailsRepository = postedTaskDetailsRepository;
        this.billRepository = billRepository;
    }

    public List<PostedTask> getTasks() {
        ArrayList<PostedTask> postedTasks = new ArrayList<> ();
        postedTasks.addAll(postedTaskRepository.findAll());
        int i = 0;
        for (PostedTask postedTask : postedTasks) {
            if (postedTask.getStatus().equalsIgnoreCase("task_finished")) {
                postedTasks.remove(i);
            }
            i++;
        }
        return postedTasks;
    }
    public List<Product> getTaskProducts(){
        return addedProductsToTask;
    }

    public String addNewProduct(Long productId){
        System.out.println(productId);
        Optional<Product> productFind = productRepository.findById(productId);
        Product product = productFind.get();
        addedProductsToTask.add(product);


        return product.getTitle() + " is toegevoegd.";
    }

    @Transactional
    public String placeTask(boolean customerConsent, String payment, Long carId){
        lastId = lastId + 1;

        PostedTask newTask = new PostedTask();
        PostedTaskDetails newTaskDetails = new PostedTaskDetails();
        Bill bill = new Bill();
        String allProducts = "";



        Optional<Car> carOptional = carRepository.findById(carId);
        Car car = carOptional.get();

        Optional<CustomerAccount>  customerAccountOptional = customerAccountRepository.findById(car.getCustomerId());
        CustomerAccount customerAccount = customerAccountOptional.get();


        String dateFormat = "dd.MM.yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        String date = dateTimeFormatter.format(LocalDate.now());

        double price = 0;

        for (Product product:addedProductsToTask) {
            price = price + product.getPrice();
            newTaskDetails.setId(detailsId);
            newTaskDetails.setProductId(product.getProductId());
            newTaskDetails.setPostedTaskId(lastId);
            allProducts = allProducts + product.getTitle() + ", ";

            postedTaskDetailsRepository.save(newTaskDetails);
            detailsId++;

        }

        newTask.setPayment(payment);
        newTask.setCustomerConsent(customerConsent);
        newTask.setCustomerId(customerAccountRepository.findById(car.getCustomerId()).get().getId());
        newTask.setPriceTotal((float) price);
        newTask.setStatus("wordt_gerepareerd");
        newTask.setTaskDate(date);
        postedTaskRepository.save(newTask);

        bill.setCustomerId(customerAccount.getId());
        bill.setName(customerAccount.getName());
        bill.setPaid(false);
        bill.setPriceTotal(price - (price * 0.21));
        bill.setSurName(customerAccount.getSurName());
        bill.setTotalPlusTaxes(price);
        bill.setProducts(allProducts);
        
        billRepository.save(bill);

        addedProductsToTask.clear();

        return "Bestelling is toegevoegd";



    }

    @Transactional
    public String readyTask(Long taskId){
        Optional<PostedTask> taskDetails = postedTaskRepository.findById(taskId);

        PostedTask task = taskDetails.get();

        task.setStatus("klaar_voor_ophalen");

        entityManager.createNativeQuery("UPDATE placed_task SET status = ? WHERE id = ?")
                .setParameter(1, task.getStatus())
                .setParameter(2, task.getId())
                .executeUpdate();

        return "Status is aangepast.";
    }
    public String removeProduct(Long productId){
        Optional<Product> productFind = productRepository.findProductByProductId(productId);
        Product product = productFind.get();

        addedProductsToTask.remove(product);

        Collections.sort(addedProductsToTask, new SortProducts());
        Collections.reverse(addedProductsToTask);

        return product.getTitle() + " is uit de takenlijst verwijderd.";


    }
}
