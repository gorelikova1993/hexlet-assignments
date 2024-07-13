package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String tagName;
    private Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = new LinkedHashMap<>(attributes);
    }

    public String getTagName() {
        return tagName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String attributesToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : getAttributes().entrySet()) {
            sb.append(" ");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append("\"");
            sb.append(entry.getValue());
            sb.append("\"");
        }
        String attributes = sb.toString();
        return attributes;
    }
}
// END
