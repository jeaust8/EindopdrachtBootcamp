package nl.aartj.GarageApp.examination;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/GarageApp/api/v1/APK")
public class ExaminationController {
    private final ExaminationService examinationService;

    @Autowired
    public ExaminationController(ExaminationService examinationService){
        this.examinationService = examinationService;
    }

    @GetMapping
    public List<Examination> getExamination(){ return ExaminationService.getExaminations();}

    @PostMapping(path = "/done/{examinationId}")
    public String examinationFinished(@PathVariable("examinationId") Long examinationId) {return examinationService.examinationFinished(examinationId);}
}
