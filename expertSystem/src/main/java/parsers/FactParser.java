package parsers;

import containers.FactRepository;
import containers.Fact;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FactParser extends XMLParser {

    private FactRepository factRepository;
    private NodeList nodeList;

    public FactParser(String xmlPath) {
        this.factRepository = new FactRepository();
        loadXmlDocument(xmlPath);
        parseXmlDocument();
    }

    public FactRepository getFactRepository() {
        return this.factRepository;
    }

    private void parseXmlDocument() {
        this.nodeList = getDocument().getElementsByTagName("Fact");
        addFactsToRepository();
    }

    private void addFactsToRepository() {

        for (int i=0; i<this.nodeList.getLength(); i++) {

            Element factNode = (Element) this.nodeList.item(i);
            String id = factNode.getAttribute("id");
            Element descriptionNode = (Element) factNode.getElementsByTagName("Description").item(0);
            String description = descriptionNode.getAttribute("value");

            Fact fact = new Fact(id, description);
            setFactValues(fact, factNode);
            this.factRepository.addFact(fact);

        }
    }

    private void setFactValues(Fact fact, Element element) {

        Element evalsNode = (Element) element.getElementsByTagName("Evals").item(0);
        NodeList evals = evalsNode.getElementsByTagName("Eval");

        for (int i=0; i<evals.getLength(); i++) {

            Element eval = (Element) evals.item(i);
            String id = eval.getAttribute("id");
            String value = eval.getTextContent();
            fact.setFactValueById(id, Boolean.valueOf(value));
        }

    }
}