package com.epam.lab.xml.knife;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "type")
public class Type implements Comparable<Type> {

    private String type;
    private String handy;
    private String origin;
    private Visual visual;
    private boolean value;

    public Type() {
    }

    public Type(String type, String handy, String origin, Visual visual, boolean value) {
        this.type = type;
        this.handy = handy;
        this.origin = origin;
        this.visual = visual;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + type + '\'' +
                ", handy='" + handy + '\'' +
                ", origin='" + origin + '\'' +
                ", visual=" + visual +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Type type) {
        return 0;
    }
}
