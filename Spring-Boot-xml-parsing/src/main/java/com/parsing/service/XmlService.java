package com.parsing.service;

import com.parsing.model.Transactions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

@Service(value = "xmlService")
@Scope(value = "singleton")
public class XmlService {

    public Transactions parseXml(String path) throws FileNotFoundException, XMLStreamException, TransformerException, JAXBException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileReader(path));
        xmlStreamReader.nextTag();

        xmlStreamReader.nextTag();
        xmlStreamReader.nextTag();
        xmlStreamReader.nextTag();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new StAXSource(xmlStreamReader), new StreamResult(stringWriter));
        StringReader stringReader = new StringReader(stringWriter.toString());
        JAXBContext jaxbContext = JAXBContext.newInstance(Transactions.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Transactions transactions = (Transactions) unmarshaller.unmarshal(stringReader);

        return transactions;
    }
}
