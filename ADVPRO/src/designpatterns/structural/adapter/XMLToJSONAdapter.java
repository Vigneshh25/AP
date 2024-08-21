package designpatterns.structural.adapter;

class XMLToJSONAdapter implements AnalyticsTool {
   private JSONAnalyticsTool jsonAnalyticsTool;


   public XMLToJSONAdapter(String xmlData) {
       System.out.println("Converting the XML Data '" + xmlData + "' to JSON Data!");
       String newData = xmlData + " in json";
       jsonAnalyticsTool = new JSONAnalyticsTool();
       jsonAnalyticsTool.setJsonData(newData);
   }


   public void AnalyzeData() {
       jsonAnalyticsTool.AnalyzeData();
   }
}
