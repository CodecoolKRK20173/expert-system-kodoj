package parsers;

import containers.Answer;
import containers.Question;
import containers.RuleRepository;
import containers.SingleValue;
import containers.Value;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RuleParser extends XMLParser {

    private RuleRepository ruleRepository;
    private NodeList nodeList;

    public RuleParser(String xmlPath) {
        this.ruleRepository = new RuleRepository();
        loadXmlDocument(xmlPath);
        parseXmlDocument();
    }

    public RuleRepository getRuleRepository() {
        return this.ruleRepository;
    }

    private void parseXmlDocument() {
        this.nodeList = getDocument().getElementsByTagName("Rule");
        addRulesToRepository();
    }

    private void addRulesToRepository() {

        for (int i=0; i<this.nodeList.getLength(); i++) {

            Element ruleNode = (Element) this.nodeList.item(i);
            String id = ruleNode.getAttribute("id");

            Element questionNode = (Element) ruleNode.getElementsByTagName("Question").item(0);
            String questionText = questionNode.getTextContent();
            Answer answer = parseAnswer(ruleNode);
            Question question = new Question(id, questionText, answer);
            this.ruleRepository.addQuestion(question);

        }
    }

    private Answer parseAnswer(Element element) {

        Answer answer = new Answer();
        NodeList selections = element.getElementsByTagName("Selection");

        for (int i=0; i<selections.getLength(); i++) {

            Element selectionNode = (Element) selections.item(i);
            boolean selectionType = Boolean.valueOf(selectionNode.getAttribute("value"));
            Element valueNode = (Element) selectionNode.getChildNodes().item(1);
            Value value;

            if (valueNode.getNodeName().equals("SingleValue")) {

                String param = valueNode.getAttribute("value");
                value = new SingleValue(param, selectionType);

            }
            answer.addValue(value);
        }

        return answer;
    }
}