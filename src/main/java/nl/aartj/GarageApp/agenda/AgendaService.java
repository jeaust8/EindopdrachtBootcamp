package nl.aartj.GarageApp.agenda;

import nl.aartj.GarageApp.car.CarRepository;
import nl.aartj.GarageApp.customer.CustomerAccount;
import nl.aartj.GarageApp.customer.CustomerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final CarRepository carRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository, CustomerAccountRepository customerAccountRepository, CarRepository carRepository){
        this.agendaRepository = agendaRepository;
        this.customerAccountRepository = customerAccountRepository;
        this.carRepository = carRepository;
    }

    public List<Agenda> getAgenda() {

        return agendaRepository.findAll();
    }



    public String addItem(String day, String month, String year, Long carId, String hour, String minute) {
       String date = day + "-" + month + "-" + year;
       String time = hour + ":" + minute;
       Long customerId = carRepository.findById(carId).get().getCustomerId();

       Optional<CustomerAccount> optionalCustomerAccount = customerAccountRepository.findById(customerId);
       CustomerAccount customerAccount = optionalCustomerAccount.get();


       Agenda item = new Agenda();

       item.setCarId(carId);
       item.setCustomerId(customerAccount.getId());
       item.setSurName(customerAccount.getSurName());
       item.setPhoneNumber(customerAccount.getPhoneNumber());
       item.setDate(date);
       item.setTime(time);


       agendaRepository.save(item);

       return "Afspraak is gemaakt";

    }
}
