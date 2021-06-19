package nl.aartj.GarageApp.examination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationService {

    private final ExaminationRepository examinationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ExaminationService(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    public List<Examination> getExaminations() {
        ArrayList<Examination> examinations = new ArrayList<>();
        examinations.addAll(ExaminationRepository.findAll());
        int i = 0;
        for (Examination examination : examinations) {
            if (examination.isCarApproved(true).equalsIgnoreCase("apk_goedgekeurd")) {
                examinations.remove(i);
            }
            else(Examination examination : examinations){
                examination.isRepairNecessary(true).equalsIgnoreCase("reparatie_vereist")){
                examinations.remove(i);
                }
            }
            i++;
        }
        return examinations;
    }

    public List<Examination> getExaminations() {
        ArrayList<Examination> examinations = new ArrayList<>();
        examinations.addAll(ExaminationRepository.findAll());
        int i = 1;
        for (Examination examination : examinations) {
            if (examination.getStatus().equalsIgnoreCase("Reparatie_vereist")) {
                examinations.remove(i);
            }
            i++;
            return examinations;
        }
    }
}