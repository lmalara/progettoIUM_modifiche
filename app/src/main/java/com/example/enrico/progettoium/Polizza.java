package com.example.enrico.progettoium;

import java.io.Serializable;
import java.util.ArrayList;

public class Polizza implements Serializable{

    private Veicolo veicolo;
    private int prezzo=160;
    private ArrayList<String> accessori = new ArrayList<String>();
    private int annoScadenza;
    private int meseScadenza;
    static private int classeMerito;
    private int numeroPolizza;

    private boolean attiva;
    private boolean inScadenza;

    private boolean assistenza_stradale=false;
    private boolean kasko=false;
    private boolean furto=false;
    private boolean incendio=false;
    private boolean guida_esperta=false;
    private boolean cristalli=false;

    public Polizza(Veicolo veicolo, int numeroPolizza, int classeMerito){
        this.classeMerito=classeMerito;
        this.veicolo=veicolo;
        this.setPrezzo((int) (getPrezzo() * veicolo.getCilindrata()/500 *(classeMerito/10+0.9f)));
        this.setNumeroPolizza(numeroPolizza);
    }

    public Veicolo getVeicolo(){
        return veicolo;
    }

    public void setVeicolo(Veicolo v){
        this.veicolo=v;
    }

    public boolean isAssistenza_stradale() {
        return assistenza_stradale;
    }

    public void setAssistenza_stradale(boolean assistenza_stradale) {
        this.assistenza_stradale = assistenza_stradale;
    }

    public boolean isKasko() {
        return kasko;
    }

    public void setKasko(boolean kasko) {
        this.kasko = kasko;
    }

    public boolean isFurto() {
        return furto;
    }

    public void setFurto(boolean furto) {
        this.furto = furto;
    }

    public boolean isIncendio() {
        return incendio;
    }

    public void setIncendio(boolean incendio) {
        this.incendio = incendio;
    }

    public boolean isGuida_esperta() {
        return guida_esperta;
    }

    public void setGuida_esperta(boolean guida_esperta) {
        this.guida_esperta = guida_esperta;
    }

    public boolean isCristalli() {
        return cristalli;
    }

    public void setCristalli(boolean cristalli) {
        this.cristalli = cristalli;
    }

    public void aggiornaAccessori(){
        if(isAssistenza_stradale())
            getAccessori().add("Assistenza Stradale");
        else
            getAccessori().remove("Assistenza Stradale");

        if(isCristalli())
            getAccessori().add("Cristalli");
        else
            getAccessori().remove("Cristalli");

        if(isFurto())
            getAccessori().add("Furto");
        else
            getAccessori().remove("Furto");

        if(isGuida_esperta())
            getAccessori().add("Guida Esperta");
        else
            getAccessori().remove("Guida Esperta");

        if (isIncendio())
            getAccessori().add("Incendio");
        else
            getAccessori().remove("Incendio");

        if(isKasko())
            getAccessori().add("Kasko");
        else
            getAccessori().remove("Kasko");
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public int getClasseMerito() {
        return classeMerito;
    }

    public void setClasseMerito(int classeMerito) {
        this.classeMerito = classeMerito;
    }

    public int getAnnoScadenza() {
        return annoScadenza;
    }

    public void setAnnoScadenza(int annoScadenza) {
        this.annoScadenza = annoScadenza;
    }

    public int getMeseScadenza() {
        return meseScadenza;
    }

    public void setMeseScadenza(int meseScadenza) {
        this.meseScadenza = meseScadenza;
    }

    public ArrayList<String> getAccessori() {
        return accessori;
    }

    public void setAccessori(ArrayList<String> accessori) {
        this.accessori = accessori;
    }

    public int getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(int numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public boolean isAttiva() {
        return attiva;
    }

    public void setAttiva(boolean attiva) {
        this.attiva = attiva;
    }

    public boolean isInScadenza() {
        return inScadenza;
    }

    public void setInScadenza(boolean inScadenza) {
        this.inScadenza = inScadenza;
    }
}