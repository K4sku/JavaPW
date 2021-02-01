package model;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.List;

public class Calc implements Calculation{
    String[] input;
    String result ="";


    public String Calc(String[] args) {
        input = args;
        if (args.length != 1) {
            result = "Incorrect input";
        }
        JShell jshell = JShell.create();
        try (jshell) { // automatyczne zwalnianie zasobów we frazie try-catch, od JDK9
            List<SnippetEvent> events = jshell.eval(args[0]);
            for (SnippetEvent e : events) {
                if (e.causeSnippet() == null) {
                    switch (e.status()) {
                        case VALID:
                            if (e.value() != null) {
                                result = args[0];
                            }
                            break;
                        default:
                            result = "Error\n";
                            break;
                    }
                }
            }
        }
        return result;
    }
}
