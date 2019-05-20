
import java.util.LinkedList;
import java.util.List;

public class ElementClass {
    private String name;
    private List<ElementClass> children;

    public ElementClass(String name) {
        this.name = name;
        this.children = new LinkedList();
    }

    public String getName() {
        return name;
    }

    public  List<ElementClass> getChildren() {
        return children;
    }


    public ElementClass addChild(String name) {
        ElementClass childNode = new ElementClass(name);
        this.children.add(childNode);
        return childNode;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name+" "+children;
    }
}

