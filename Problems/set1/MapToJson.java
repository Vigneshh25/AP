//import java.util.Map;
//
//public class MapToJson {
//
//    public static String toJson(Object object, int indentLevel) {
//        StringBuilder json = new StringBuilder();
//        if (object instanceof Map) {
//            json.append(mapToJson((Map<?, ?>) object, indentLevel));
//        } else if (object instanceof List) {
//            json.append(listToJson((List<?>) object, indentLevel));
//        } else if (object instanceof String) {
//            json.append("\"").append(object).append("\"");
//        } else if (object instanceof Number || object instanceof Boolean) {
//            json.append(object.toString());
//        } else {
//            json.append("\"").append(object.toString()).append("\"");
//        }
//        return json.toString();
//    }
//
//    private static String mapToJson(Map<?, ?> map, int indentLevel) {
//        StringBuilder json = new StringBuilder();
//        json.append("{\n");
//        String indent = " ".repeat(indentLevel + 2);
//        String separator = "";
//
//        for (Map.Entry<?, ?> entry : map.entrySet()) {
//            json.append(separator).append(indent);
//            json.append("\"").append(entry.getKey().toString()).append("\": ");
//            json.append(toJson(entry.getValue(), indentLevel + 2));
//            separator = ",\n";
//        }
//
//        json.append("\n").append(" ".repeat(indentLevel)).append("}");
//        return json.toString();
//    }
//
//    private static String listToJson(List<?> list, int indentLevel) {
//        StringBuilder json = new StringBuilder();
//        json.append("[\n");
//        String indent = " ".repeat(indentLevel + 2);
//        String separator = "";
//
//        for (Object element : list) {
//            json.append(separator).append(indent);
//            json.append(toJson(element, indentLevel + 2));
//            separator = ",\n";
//        }
//
//        json.append("\n").append(" ".repeat(indentLevel)).append("]");
//        return json.toString();
//    }
//
//    public static void main(String[] args) {
//        // Create a test map with various data types
//        Map<String, Object> testMap = new HashMap<>();
//        testMap.put("name", "John Doe");
//        testMap.put("age", 30);
//        testMap.put("isVerified", true);
//        testMap.put("address", Map.of("street", "123 Main St", "city", "Anytown"));
//        testMap.put("phoneNumbers", List.of("123-456-7890", "098-765-4321"));
//        testMap.put("attributes", Map.of("height", 180, "weight", 75.5, "isMarried", false));
//
//        // Convert map to JSON string
//        String jsonString = toJson(testMap, 0);
//        System.out.println(jsonString);
//    }
//}
