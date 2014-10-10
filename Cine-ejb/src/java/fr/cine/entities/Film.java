/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cine.entities;

import fr.cine.utils.JsonReader;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

/**
 *
 * @author toure
 */
@Entity
@Table(name = "FILM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f"),
    @NamedQuery(name = "Film.findById", query = "SELECT f FROM Film f WHERE f.id = :id"),
    @NamedQuery(name = "Film.findByAffiche", query = "SELECT f FROM Film f WHERE f.affiche = :affiche"),
    @NamedQuery(name = "Film.findByPrix", query = "SELECT f FROM Film f WHERE f.prix = :prix"),
    @NamedQuery(name = "Film.findByRelease", query = "SELECT f FROM Film f WHERE f.release = :release"),
    @NamedQuery(name = "Film.findByRuntime", query = "SELECT f FROM Film f WHERE f.runtime = :runtime"),
    @NamedQuery(name = "Film.findByTitre", query = "SELECT f FROM Film f WHERE f.titre = :titre")})
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "AFFICHE")
    private String affiche;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRIX")
    private Double prix;
    @Size(max = 255)
    @Column(name = "RELEASE")
    private String release;
    @Column(name = "RUNTIME")
    private Integer runtime;
    @Size(max = 255)
    @Column(name = "TITRE")
    private String titre;

    public Film() {
    }

    public Film(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAffiche(String s) {
        return "http://image.tmdb.org/t/p/" + s + affiche + "?api_key=63d250a5b71c307f7592228c79b729cf";
    }
    
    public String getAffiche() {
        return "http://image.tmdb.org/t/p/w500" + affiche + "?api_key=63d250a5b71c307f7592228c79b729cf";
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean load() {
        String urlString = "https://api.themoviedb.org/3/movie/" + id + "?api_key=63d250a5b71c307f7592228c79b729cf";
        try {
            JSONObject json = JsonReader.readJsonFromUrl(urlString);
            titre = (String) json.get("title");
            affiche = (String) json.get("poster_path");
            runtime = (int) json.get("runtime");
            release = (String) json.get("release_date");
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public String toString() {
        return "fr.cine.entities.Film[ id=" + id + " ]";
    }
    
}
