package com.doppelgunner.baby.model.brain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by robertoguazon on 12/08/2016.
 */
public class Brain implements Serializable {

    private static final long serialVersionUID = 809182409133L;

    private HashMap<String,ArrayList<Knowledge>> memory;

    public Brain() {
        memory = new HashMap<>();
    }

    public void input(String subject, String content) {
        if (memory.get(subject) == null) {
            memory.put(subject, new ArrayList<Knowledge>());
            memory.get(subject).add(new Knowledge(content));
        } else {
            memory.get(subject).add(new Knowledge(content));
        }
    }

    public ArrayList<Knowledge> output(String subject) {
        return memory.get(subject);
    }

}
