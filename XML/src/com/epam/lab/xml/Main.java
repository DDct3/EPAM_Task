package com.epam.lab.xml;

import com.epam.lab.xml.knife.Knife;
import com.epam.lab.xml.knife.Type;
import com.epam.lab.xml.knife.Material;
import com.epam.lab.xml.knife.Visual;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException, SAXException, ParserConfigurationException {

        stax();
        sax();
        dom();
        checkingXSD();
    }

    //Task 6 - 7
    private static void sortKnife(List<Type> list){
        list.sort(new KnifeComparator());
        for (Type type : list) {
            System.out.println(type);
        }
    }

    //Task 5
    private static void checkingXSD(){
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new StreamSource(new File("/home/future/EPAM/XML/knife.xsd")))
                    .newValidator()
                    .validate(new StreamSource(new File("/home/future/EPAM/XML/knife.xml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something is wrong");
            System.exit(0);
        }
        System.out.println("Everything is right");
    }

    //Task 4
    private static void stax() throws XMLStreamException, FileNotFoundException {
        Set<Type> set = new HashSet<>();
        Type currType = null;
        Visual visual = null;
        Material material = null;
        String qName = "";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(new FileReader("/home/future/EPAM/XML/knife.xml"));
        Iterator<Attribute> attributes;

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    qName = startElement.getName().getLocalPart();
                    if (qName.equalsIgnoreCase("type")) {
                        currType = new Type();
                        visual = new Visual();
                        material = new Material();
                        attributes = startElement.getAttributes();
                        currType.setType(attributes.next().getValue());
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters character = event.asCharacters();
                    if (qName.equalsIgnoreCase("origin") && !character.isWhiteSpace()) {
                        currType.setOrigin(character.getData());
                    }
                    if (qName.equalsIgnoreCase("handy") && !character.isWhiteSpace()) {
                        currType.setHandy(character.getData());
                    }
                    if (qName.equalsIgnoreCase("length") && !character.isWhiteSpace()) {
                        visual.setLength(Double.parseDouble(character.getData()));
                        visual.getLength();
                    }
                    if (qName.equalsIgnoreCase("width") && !character.isWhiteSpace()) {
                        visual.setWidth(Double.parseDouble(character.getData()));
                    }
                    if (qName.equalsIgnoreCase("blade") && !character.isWhiteSpace()) {
                        material.setBlade(character.getData());
                    }
                    if (qName.equalsIgnoreCase("handles") && !character.isWhiteSpace()) {
                        material.setHandles(character.getData());
                    }
                    if (qName.equalsIgnoreCase("blood-flow") && !character.isWhiteSpace()) {
                        material.setBloodFlow(Boolean.parseBoolean(character.getData()));
                        visual.setMaterial(material);
                    }
                    if (qName.equalsIgnoreCase("value") && !character.isWhiteSpace()) {
                        currType.setVisual(visual);
                        currType.setValue(Boolean.parseBoolean(character.getData()));
                    }
                    break;
            }
            if (currType != null)
                set.add(currType);
        }

        for (Type type : set) {
            System.out.println(type);
        }
    }

    //Task 4
    private static void sax() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(new File("/home/future/EPAM/XML/knife.xml"), handler);

        for (Type type : handler.set) {
            System.out.println(type);
        }
    }

    //Task 4
    private static void dom() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/home/future/EPAM/XML/knife.xml"));
        Set<Type> set = new HashSet<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Type type = new Type();
                type.setType(node.getAttributes().
                        getNamedItem("name").getNodeValue());

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    if (cNode.hasChildNodes()) {
                        cNode = node.getChildNodes().item(j);
                        if (cNode instanceof Element) {
                            String content = cNode.getLastChild().
                                    getTextContent().trim();
                            switch (cNode.getNodeName()) {
                                case "handy":
                                    type.setHandy(content);
                                    break;
                                case "origin":
                                    type.setOrigin(content);
                                    break;
                                case "visual":
                                    Visual visual = new Visual();
                                    for (int k = 0; k < childNodes.getLength(); k++) {
                                        Node secondNode = cNode.getChildNodes().item(k);
                                        if (secondNode instanceof Element) {
                                            String secondContent = secondNode.getLastChild().
                                                    getTextContent().trim();
                                            switch (secondNode.getNodeName()) {
                                                case "length":
                                                    visual.setLength(Double.parseDouble(secondContent));
                                                    break;
                                                case "width":
                                                    visual.setWidth(Double.parseDouble(secondContent));
                                                    break;
                                                case "material":
                                                    Material material = new Material();
                                                    for (int l = 0; l < childNodes.getLength(); l++) {
                                                        Node thirdNode = secondNode.getChildNodes().item(l);
                                                        if (thirdNode instanceof Element) {
                                                            String tgirdContent = thirdNode.getLastChild().
                                                                    getTextContent().trim();
                                                            switch (thirdNode.getNodeName()) {
                                                                case "blade":
                                                                    material.setBlade(tgirdContent);
                                                                    break;
                                                                case "handles":
                                                                    material.setHandles(tgirdContent);
                                                                case "blood-flow":
                                                                    material.setBloodFlow(Boolean.parseBoolean(tgirdContent));
                                                            }
                                                        }
                                                    }
                                                    visual.setMaterial(material);
                                            }
                                        }
                                    }
                                    type.setVisual(visual);
                                    break;
                                case "value":
                                    type.setValue(Boolean.parseBoolean(content));
                            }
                        }
                    }
                    set.add(type);
                }
            }
        }
        for (Type type : set) {
            System.out.println(type);
        }
    }

    //Task 9
    public static void marshall(List<Type> subscribers, Class objectClass) throws JAXBException{

        JAXBContext jaxbCtxt = JAXBContext.newInstance(objectClass);
        Marshaller jaxbMarshaller = jaxbCtxt.createMarshaller();
        File file = new File("/home/future/EPAM/XML/test.xml");

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        jaxbMarshaller.marshal(new Knife(subscribers), file);

    }

    //Task 9
    public static List<Type> unmarshall(Class objectClass) throws JAXBException{

        Knife knife = new Knife();
        JAXBContext jaxbCtxt = JAXBContext.newInstance(objectClass);
        Unmarshaller jaxbUnmarshaller = jaxbCtxt.createUnmarshaller();

        knife = (Knife) jaxbUnmarshaller.unmarshal(new File("/home/future/EPAM/XML/knife.xml"));
        return knife.getKnife();

    }
}
