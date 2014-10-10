/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cine.beans;

import fr.cine.entities.Film;
import fr.cine.ejb.FilmFacadeLocal;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class FilmMB {

    @EJB
    private FilmFacadeLocal filmInt;
    private Film film = null;
    private Long id;
    private Double prix;

    /**
     * Creates a new instance of FilmEntity
     */
    public FilmMB() {
    }

    public String addFilm() {
        Film f = new Film();
        f.setId(id);
        f.setPrix(prix);
        f.load();
        if (f.getTitre() != null) {
            filmInt.create(f);
        }
        return f.getTitre();
    }

    public List<Film> getAllFilms() throws IOException {
        return filmInt.findAll();
    }
    
    public String getFilm(Long id) {
        film = filmInt.find(id);
        return "affiche.xhtml";
    }
    
    public Film getFilm() {
        return film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
    
}
