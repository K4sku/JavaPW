package model;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.List;

public class Calc implements Calculation{
    String result ="";

    final JShell jshell = JShell.create();

    public String Calc(String equation) {
            List<SnippetEvent> events = jshell.eval("(double) "+ equation);
            for (SnippetEvent e : events) {
                if (e.causeSnippet() == null) {
                    switch (e.status()) {
                        case VALID:
                            if (e.value() != null) {

                                result = e.value();
                            }
                            break;
                        default:
                            result = "Error\n";
                            break;
                    }
                }
        }
        return result;
    }
}