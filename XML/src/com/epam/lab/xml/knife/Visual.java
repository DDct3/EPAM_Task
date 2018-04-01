package com.epam.lab.xml.knife;

public class Visual {

    private double length;
    private double width;
    private Material material;

    public Visual() {
    }

    public Visual(double length, double width, Material material) {
        this.length = length;
        this.width = width;
        this.material = material;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Visual{" +
                "length=" + length +
                ", width=" + width +
                ", material=" + material +
                '}';
    }
}
