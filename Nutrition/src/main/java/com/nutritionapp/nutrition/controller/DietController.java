package com.nutritionapp.nutrition.controller;

import com.nutritionapp.nutrition.Entity.DietPlan;
import com.nutritionapp.nutrition.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping(path = "/dietPlan")
public class DietController {
    private static final Logger logger = LogManager.getLogger(DietController.class);
    private final DietService dietService;
    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping
    public List<DietPlan> DietPlan(){

        return dietService.listAllPlan();
  }


  @PostMapping
  public void createDietPlan(@RequestBody DietPlan dietPlan){

        dietService.createDietPlan(dietPlan);
  }


  @DeleteMapping(path ="{dietPlanId}")
  public void removeDietPlan(@PathVariable("dietPlanId") int dietPlanId){
        try {
            dietService.removeDietPlan( dietPlanId);
        }catch(Exception e){
            logger.info("Please enter correct dietPlan Id.");
        }

  }
//  @PutMapping(path ="{dietPlanId}")
//    public void changeDietPlan(
//            @PathVariable(" dietPlanId") int  dietPlanId,
//  @RequestParam(required = false) String slots ,
//  @RequestParam(required = false) String foodType  ){
//        dietService.changeDietPlan(dietPlanId, slots, foodType);
//  }
    @PutMapping(path ="{dietPlanId}")
    public void changeDietPlan(@RequestBody DietPlan dietPlan,
            @PathVariable(" dietPlanId") int  dietPlanId
//  @RequestParam(required = false) String slots ,
//  @RequestParam(required = false) String foodType
  )
    {
        try{
        dietService.changeDietPlan(dietPlan, dietPlanId);}
    catch(Exception e)
    {
        logger.info("The plan you are requesting to change doesn't exists");
    }
    }

}
