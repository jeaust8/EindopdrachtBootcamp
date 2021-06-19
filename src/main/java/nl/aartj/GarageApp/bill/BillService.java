package nl.aartj.GarageApp.bill;


import nl.aartj.GarageApp.placedTask.PlacedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class BillService {

    private final BillRepository billRepository;
    private final PlacedTaskRepository placedTaskRepository;

    @Autowired
    public BillService(BillRepository billRepository, PlacedTaskRepository placedTaskRepository){
        this.billRepository = billRepository;
        this.placedTaskRepository = placedTaskRepository;
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
