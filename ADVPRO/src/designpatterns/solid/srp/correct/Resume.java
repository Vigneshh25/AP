package designpatterns.solid.srp.correct;

public class Resume {

     String technology;
     Integer yearsOfExperience;   

     public String getTechnology() {

           return technology;
     }

     public void setTechnology(String technology) {

           this.technology = technology;
     }

     public Integer getYearsOfExperience() {

           return yearsOfExperience;
     }

     public void setYearsOfExperience(Integer yearsOfExperience) {

           this.yearsOfExperience = yearsOfExperience;
     }
}