package exercise;

// BEGIN
public class LabelTag implements TagInterface{
    private String label;
    TagInterface tagInterface;

    public LabelTag(String label, TagInterface tagInterface) {
        this.label = label;
        this.tagInterface = tagInterface;
    }

    @Override
    public String render() {
        return "<label>" + label + tagInterface.render() + "</label>";
    }
}
// END
