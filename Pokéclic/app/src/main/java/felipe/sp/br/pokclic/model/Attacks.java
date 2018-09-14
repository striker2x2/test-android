package felipe.sp.br.pokclic.model;

import java.util.List;

/**
 * Created by Felipe on 14/09/2018.
 */

public class Attacks {
    private List<String> cost;
    private String name;
    private String text;
    private String damage;
    private int convertedEnergyCost;

    public Attacks() {
    }

    public Attacks(List<String> cost, String name, String text, String damage, int convertedEnergyCost) {
        this.cost = cost;
        this.name = name;
        this.text = text;
        this.damage = damage;
        this.convertedEnergyCost = convertedEnergyCost;
    }

    public List<String> getCost() {
        return cost;
    }

    public void setCost(List<String> cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getConvertedEnergyCost() {
        return convertedEnergyCost;
    }

    public void setConvertedEnergyCost(int convertedEnergyCost) {
        this.convertedEnergyCost = convertedEnergyCost;
    }
}
