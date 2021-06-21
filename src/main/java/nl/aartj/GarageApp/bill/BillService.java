package nl.aartj.GarageApp.bill;


import nl.aartj.GarageApp.postedTask.PostedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class BillService {

    private final BillRepository billRepository;
    private final PostedTaskRepository postedTaskRepository;
    private EntityManager entityManager;

    @Autowired
    public BillService(BillRepository billRepository, PostedTaskRepository postedTaskRepository){
        this.billRepository = billRepository;
        this.postedTaskRepository = postedTaskRepository;
    }

    public List<Bill> getBills(){
        ArrayList<Bill> bills = new ArrayList<Bill>();
        bills.addAll(billRepository.findAll());
        int i = 0;
        for (Bill bill : bills){
            if(bill.isPaid(true).equalsIgnoreCase("rekening_is_betaald")){
                bills.remove(i);
            }
            i++;
        }
        return bills;
    }

    public List<Bill> getBills(){
        ArrayList<Bill> bills = new ArrayList<Bill>();
        bills.addAll(billRepository.findAll());
        int i = 0;
        for (Bill bill : bills){
            if(bill.isPaid(false).equalsIgnoreCase("rekening_nog_niet_betaald")){
                bills.remove(i);
            }
            i++;
        }
        return bills;
    }
}
