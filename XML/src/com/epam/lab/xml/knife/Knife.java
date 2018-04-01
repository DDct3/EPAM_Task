package com.epam.lab.xml.knife;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "knife")
public class Knife {

    @XmlElement(name = "type")
    private List<Type> knife = new ArrayList<>();

    public Knife() {
    }

    public Knife(List<Type> knives) {
        this.knife = knives;
    }

    public List<Type> getKnife() {
        return knife;
    }

    public void setKnife(List<Type> knife) {
        this.knife = knife;
    }
}

