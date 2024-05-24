package Problems.set34;

public class RemoveChar {
    public static void main(String[] args) {
        String s1 = "Experinence";
        String s2 = "En";

        StringBuilder sb = new StringBuilder();
        for (char c : s1.toCharArray()) {
            if (!s2.contains(String.valueOf(c))) {
                sb.append(c);
            }
        }

        String result = sb.toString();
        System.out.println(result);

    }
}
//    String s1 = "expErIence";
//    String s2 = "En";

//    Map<Character, Boolean> map = new HashMap<>();
//for (char c : s2.toCharArray()) {
//        map.put(c, true);
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (char c : s1.toCharArray()) {
//        if (!map.containsKey(c)) {
//        sb.append(c);
//        }
//        }
//
//        String result = sb.toString();
//        System.out.println(result);
