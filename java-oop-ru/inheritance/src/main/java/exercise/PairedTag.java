package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private String bodyTag;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String bodyTag,
                     List<Tag> children) {
        super(tagName, attributes);
        this.bodyTag = bodyTag;
        this.children = new ArrayList<>(children);
    }

    public String toString() {
        String attributes = attributesToString();

        StringBuilder sb2 = new StringBuilder();
        for (Tag tag : children) {
            sb2.append(tag.toString());
        }
        String children = sb2.toString();

        return "<" + super.getTagName()
                + attributes + ">"
                + bodyTag
                + children
                + "</" + super.getTagName() + ">";
    }
}
// END
