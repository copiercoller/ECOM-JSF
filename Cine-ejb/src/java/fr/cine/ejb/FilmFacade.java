/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cine.ejb;

import fr.cine.entities.Film;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toure
 */
@Stateless
public class FilmFacade extends AbstractFacade<Film> implements FilmFacadeLocal {
    @PersistenceContext(unitName = "Cine-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmFacade() {
        super(Film.class);
    }
    
}
