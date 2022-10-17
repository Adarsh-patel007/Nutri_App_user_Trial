package com.nutritionapp.nutrition.service;

import com.nutritionapp.nutrition.Entity.DietPlan;
import com.nutritionapp.nutrition.exception.DietPlanNotFoundException;
import com.nutritionapp.nutrition.repository.DietPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DietService implements DietServices {
    private static final Logger logger = LogManager.getLogger(DietService.class);
    private final DietPlanRepo dietPlanRepo;
    @Autowired
    public DietService(DietPlanRepo dietPlanRepo) {
        this.dietPlanRepo = dietPlanRepo;
    }

    public List<DietPlan>  listAllPlan(){
        logger.info("listAllPlan method initiated.");
        return dietPlanRepo.findAll();
    }

    public DietPlan createDietPlan(DietPlan dietPlan) {
        logger.info("createDietPlan method executed");
//        Optional<DietPlan> dietById= dietPlanRepo.findDietPlanById(dietPlan.getId());
//        if(dietById.isPresent()){
//            throw new IllegalStateException("plan alrady exists");
//        }
        DietPlan diet = dietPlanRepo.save(dietPlan);
       return  diet;

    }

    public void removeDietPlan(int dietPlanId) throws DietPlanNotFoundException {
        logger.info("removeDietPlan method initiated");
boolean exists = dietPlanRepo.existsById(dietPlanId);
if(!exists){
    throw new DietPlanNotFoundException("The id mentioned" + dietPlanId + "doesn't exists");
}dietPlanRepo.deleteById(dietPlanId);
        logger.info("removeDietPlan method executed");
    }
@Transactional
    public DietPlan changeDietPlan(DietPlan dietPlan, int dietPlanId) throws DietPlanNotFoundException
    {logger.info("changeDietPlan method initiated.");

            DietPlan value = dietPlanRepo.findById(dietPlanId).
                    orElseThrow(()->new DietPlanNotFoundException("DietPlan with id "+
                            dietPlanId +" does not exist."));
            value.setCarbsRatio(dietPlan.getCarbsRatio());
            value.setFatRatio(dietPlan.getFatRatio());
            value.setFoodType(dietPlan.getFoodType());
            value.setSlots(dietPlan.getSlots());
            value.setTotal(dietPlan.getTotal());
            value.setProteinRatio(dietPlan.getProteinRatio());
        logger.info("changeDietPlan method executed");
            return dietPlanRepo.save(value);

    }
}
