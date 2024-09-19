package designpatterns.structural.adapter;

public class XmlToJson {

    /*
     * Adapter design pattern is one of the structural design pattern that make two unrelated
     * interfaces can work together. The object that joins these unrelated interface is called an Adapter.
     * Use adapters to reuse existing classes that don't match the required interface of a client or system.
     * */
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
