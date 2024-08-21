package designpatterns.structural.adapter;

public class XmlToJson {
   public static void main(String[] args) {
       String xmlData = "Sample Data";
       JSONAnalyticsTool tool1 = new JSONAnalyticsTool();
       tool1.setJsonData(xmlData);
       tool1.AnalyzeData();


       System.out.println("----------------------------------------------");


       AnalyticsTool tool2 = new XMLToJSONAdapter(xmlData);
       tool2.AnalyzeData();
   }
}
