package main;

import parsers.FactParser;
import parsers.RuleParser;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        String factsXmlPath = Main.class.getResource("/Facts.xml").getPath();
        String rulesXmlPath = Main.class.getResource("/Rules.xml").getPath();
        ESProvider esProvider = new ESProvider(new FactParser(factsXmlPath), new RuleParser(rulesXmlPath));

        view.print("Hello, I've heard that you are searching for a new MMO game?\n");

        esProvider.collectAnsers();
        String output = esProvider.evaluate();
        if (output.equals("")) {
            output = "Sorry, our base is too small for your excessive needs";
            view.print(output);
        } else {
            view.print("I am pretty sure that you should try: ");
            view.print(output);
        }
    }
}