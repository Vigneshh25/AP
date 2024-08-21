package designpatterns.solid.srp.correct;

public class RepositoryView {

     Resume resume;

     public RepositoryView(Resume resume) {

           this.resume = resume;
     }
    
     public void searchResume() {

           //logic to search resume goes here
     }
}