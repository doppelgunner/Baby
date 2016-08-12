package com.doppelgunner.baby.model.brain;

import java.io.Serializable;

/**
 * Created by robertoguazon on 12/08/2016.
 */
public class Knowledge implements Serializable {

    private static final long serialVersionUID = 809182409144L;

    private String content;

    public Knowledge(String content)  {
        this.content = content;
    }

    public String toString() {
        return content;
    }

}
