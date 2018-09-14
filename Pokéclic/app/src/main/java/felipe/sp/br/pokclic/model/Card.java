package felipe.sp.br.pokclic.model;

import java.util.List;

/**
 * Created by Felipe on 14/09/2018.
 */

public class Card {
    private String id;
    private String name;
    private int nationalPokedexNumber;
    private String imageUrl;
    private String imageUrlHiRes;
    private List<String> types;
    private String supertype;
    private String subtype;
    private Ability ability;
    private String hp;
    private String retreatCost;
    private int convertedRetreatCost;
    private int number;
    private String artist;
    private String rarity;
    private String series;
    private String set;
    private String setCod;
    private List<String> text;
    private List<Attacks> attacks;
    private List<Weaknesses> weaknesses;

}
