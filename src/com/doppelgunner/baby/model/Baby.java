package com.doppelgunner.baby.model;

import com.doppelgunner.baby.model.brain.Brain;
import com.doppelgunner.baby.model.brain.Knowledge;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by robertoguazon on 12/08/2016.
 */
public class Baby implements Serializable {
    private static final long serialVersionUID = 809182409122L;

    private Brain brain;

    public Baby() {
        brain = new Brain();
    }

    public void teach(String subject, String content) {
        subject = subject.toLowerCase();
        brain.input(subject, content);
    }

    public String ask(String subject) throws Exception {
        subject = subject.toLowerCase();
        String product = "";
        ArrayList<Knowledge> listOfContents = brain.output(subject);

        for (int i = 0; i < listOfContents.size(); i++) {
            product += "\t" + i + " - " + listOfContents.get(i) + "\n";
        }

        return product;
    }

    public void forget(String subject, int index) throws Exception {
        subject = subject.toLowerCase();
        String product = "";
        ArrayList<Knowledge> listOfContents = brain.output(subject);
        listOfContents.remove(index);
        System.out.println("Pooof..! one less memory, makes the baby happy!");
    }

}
