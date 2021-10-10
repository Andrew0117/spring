package com.parsing;

import com.parsing.model.Transactions;
import com.parsing.service.TransactionService;
import com.parsing.service.XmlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws XMLStreamException, IOException, TransformerException, JAXBException {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        XmlService xmlService = context.getBean(XmlService.class);
        TransactionService transactionService = context.getBean(TransactionService.class);

        String currentDirectory = new File("").getAbsolutePath();
        Transactions transactions = xmlService.parseXml(currentDirectory + "/xml/Xml.xml");

        transactionService.saveTransactions(transactions);

        //context.close();
    }

}
