package com.epam.lab.xml;

import com.epam.lab.xml.knife.Type;
import com.epam.lab.xml.knife.Material;
import com.epam.lab.xml.knife.Visual;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class SAXHandler extends DefaultHandler {

    Set<Type> set = new HashSet<>();
    Type type = null;
    Visual visual = null;
    Material material = null;
    String string = null;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (qName) {
            case "type":
                type = new Type();
                visual = new Visual();
                material = new Material();
                for (int i = 0; i < 10; i++) {
                    type.setType(attributes.getValue("name"));
                }
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "type":
                set.add(type);
                break;
            case "handy":
                type.setHandy(string);
                break;
            case "origin":
                type.setOrigin(string);
                break;
            case "length":
                visual.setLength(Double.parseDouble(string));
                break;
            case "width":
                visual.setWidth(Double.parseDouble(string));
                break;
            case "blade":
                material.setBlade(string);
                break;
            case "handles":
                material.setHandles(string);
                break;
            case "blood-flow":
                material.setBloodFlow(Boolean.parseBoolean(string));
                visual.setMaterial(material);
                type.setVisual(visual);
                break;
            case "value":
                type.setValue(Boolean.parseBoolean(string));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (!String.copyValueOf(ch, start, length).trim().isEmpty()) {
            string = String.copyValueOf(ch, start, length).trim();
        }

    }

}
