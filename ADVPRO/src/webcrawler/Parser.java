package webcrawler;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public Set<String> parseLinks(String content) {
        Set<String> links = new HashSet<>();
        Pattern pattern = Pattern.compile("href=\"(http[^\"]*)\"");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            links.add(matcher.group(1));
        }
        return links;
    }

    public String parseData(String content) {
        // For simplicity, we just return the entire content.
        return content;
    }
}
