package designpatterns.structural.adapter;

class JSONAnalyticsTool {
   private String jsonData;


   public void setJsonData(String jsonData) {
       this.jsonData = jsonData;
   }


   public void AnalyzeData() {
       if (jsonData.contains("json")) {
           System.out.println("Analyzing JSON Data - " + jsonData);
       } else {
           System.out.println("Not in the correct format. Can't analyze!");
       }
   }
}
