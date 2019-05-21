package main.classes;

import java.util.LinkedList;
import java.util.List;

public class ElementTextClass {
    private String name;
    private String text;
    //private main.classes.ElementTextClass parent;
    private List<ElementTextClass> children;

    public ElementTextClass(String name, String text) {
        this.name = name;
        this.text = text;
        this.children = new LinkedList(); ;
    }

    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }

    public  List<ElementTextClass> getChildren() {
        return children;
    }

    public ElementTextClass addChild(String name, String text) {
        ElementTextClass childNode = new ElementTextClass(name, text);
        this.children.add(childNode);
        return childNode;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString(){
        return name+" "+text+" "+children;
    }
}
