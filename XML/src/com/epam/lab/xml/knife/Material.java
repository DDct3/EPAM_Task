package com.epam.lab.xml.knife;

public class Material {
    private String blade;
    private String handles;
    private boolean bloodFlow;

    public Material() {
    }

    public Material(String blade, String handles, boolean bloodFlow) {
        this.blade = blade;
        this.handles = handles;
        this.bloodFlow = bloodFlow;
    }

    public String getBlade() {
        return blade;
    }

    public void setBlade(String blade) {
        this.blade = blade;
    }

    public String getHandles() {
        return handles;
    }

    public void setHandles(String handles) {
        this.handles = handles;
    }

    public boolean isBloodFlow() {
        return bloodFlow;
    }

    public void setBloodFlow(boolean bloodFlow) {
        this.bloodFlow = bloodFlow;
    }

    @Override
    public String toString() {
        return "Material{" +
                "blade='" + blade + '\'' +
                ", handles='" + handles + '\'' +
                ", bloodFlow=" + bloodFlow +
                '}';
    }
}
