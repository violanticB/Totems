package net.violantic.totem.builder;

import org.bukkit.Material;
import org.bukkit.util.Vector;

public class TotemComponent {

    private Vector vector;
    private Material material;

    public TotemComponent(Vector vector, Material material) {
        this.vector = vector;
        this.material = material;
    }

    public Vector getVector() {
        return vector;
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
