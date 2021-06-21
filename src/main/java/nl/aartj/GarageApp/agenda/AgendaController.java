package nl.aartj.GarageApp.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/GarageApp/api/v1/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaController(AgendaService agendaService, AgendaRepository agendaRepository){
        this.agendaService = agendaService;
        this.agendaRepository = agendaRepository;

    }

    @GetMapping
    public List<Agenda> getAgenda(){
        return agendaService.getAgenda();
    }

    @PostMapping(path = "/add/{day}/{month}/{year}/{carId}/{hour}/{minute}")
    public String addItem(
            @PathVariable ("day") String day,
            @PathVariable ("month") String month,
            @PathVariable ("year") String year,
            @PathVariable ("carId") Long carId,
            @PathVariable ("hour") String hour,
            @PathVariable ("minute") String minute
    ){
        return agendaService.addItem(day,month,year,carId,hour,minute);
    }


}
