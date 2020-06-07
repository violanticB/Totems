package net.violantic.totem.builder;

import java.util.ArrayList;
import java.util.List;

public class TotemField {
    
    private List<TotemComponent> totemBlocks;

    public TotemField() {
        this.totemBlocks = new ArrayList<>();
    }

    public List<TotemComponent> getComponents() {
        return totemBlocks;
    }
    
}

