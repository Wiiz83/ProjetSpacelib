/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author uzanl
 */
@Stateless
public class VoyageFacade extends AbstractFacade<Voyage> implements VoyageFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoyageFacade() {
        super(Voyage.class);
    }

    @Override
    public Voyage creerVoyage(Navette navette, Usager usager, Quai quaiDepart, Quai quaiArrive, int NbPassagers, Calendar dateDepart, Calendar dateArrivee) {
        Voyage nouveauVoyage = new Voyage(NbPassagers, navette, Voyage.statutDebutVoyage, usager, dateDepart, dateArrivee, quaiDepart, quaiArrive);
        this.create(nouveauVoyage);
        return nouveauVoyage;
    }

    @Override
    public Voyage findPlusProcheVoyageArriveADateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date utilDate = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiArrivee"), q);
            final Predicate dateArrivePredicate = cb.lessThanOrEqualTo(root.<Date>get("dateArrivee"), utilDate);

            cq.where(cb.and(quaiPredicate, dateArrivePredicate));
            cq.orderBy(cb.asc(root.get("dateArrivee")));

            return getEntityManager().createQuery(cq)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Voyage findPlusProcheVoyageDepartDeNavetteADateEtQuai(Calendar dateDepart, Quai q, Navette n) {
        try {
            java.util.Date ddateDepart = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final ParameterExpression<Quai> quaiParameter = cb.parameter(Quai.class);
            final ParameterExpression<Date> dateArriveParameter = cb.parameter(Date.class);
            final ParameterExpression<Navette> navetteParameter = cb.parameter(Navette.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiDepart"), quaiParameter);
            final Predicate navettePredicate = cb.equal(root.get("navette"), navetteParameter);
            final Predicate dateArrivePredicate = cb.greaterThanOrEqualTo(root.<Date>get("dateDepart"), dateArriveParameter);

            cq.where(cb.and(quaiPredicate, dateArrivePredicate, navettePredicate));
            cq.orderBy(cb.desc(root.get("dateDepart")));

            return getEntityManager().createQuery(cq)
                    .setParameter(quaiParameter, q)
                    .setParameter(navetteParameter, n)
                    .setParameter(dateArriveParameter, ddateDepart, TemporalType.DATE)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Voyage> findAllVoyagesPrevusByUsager(Usager usager) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
        Root<Voyage> root = cq.from(Voyage.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("usager"), usager),
                        cb.equal(root.get("statut"), Voyage.statutDebutVoyage)
                )
        );

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public boolean verifierSiAutresVoyagesPrevusSurNavette(Calendar Cdate, Navette n) {
        try {
            boolean autresVoyages = false;
            java.util.Date date = Cdate.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final Predicate navettePredicate = cb.equal(root.get("navette"), n);
            final Predicate dateDebutPredicate = cb.greaterThanOrEqualTo(root.<Date>get("dateDepart"), date); // premier plus grand ou égal au second

            cq.where(cb.and(navettePredicate, dateDebutPredicate));

            if (getEntityManager().createQuery(cq).getResultList().size() > 0) {
                autresVoyages = true;
            }

            return autresVoyages;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Voyage findVoyageDepartJourDateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date date = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final Predicate quaiPredicate = cb.equal(root.get("quai"), q);
            final Predicate dateDebutPredicate = cb.equal(root.<Date>get("dateDepart"), date);

            cq.where(cb.and(quaiPredicate, dateDebutPredicate));

            return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Voyage findVoyageArriveeJourDateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date date = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final Predicate quaiPredicate = cb.equal(root.get("quai"), q);
            final Predicate dateDebutPredicate = cb.equal(root.<Date>get("dateArrivee"), date);

            cq.where(cb.and(quaiPredicate, dateDebutPredicate));

            return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean verifierSiNavettePossedeDepartVoyageAvantDate(Calendar Cdate, Navette n) {
        boolean autresVoyages = false;
        java.util.Date date = Cdate.getTime();

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
        Root<Voyage> root = cq.from(Voyage.class);

        final Predicate navettePredicate = cb.equal(root.get("navette"), n);
        final Predicate dateDebutPredicate = cb.lessThan(root.<Date>get("dateDepart"), date);

        cq.where(cb.and(navettePredicate, dateDebutPredicate));

        if (getEntityManager().createQuery(cq).getResultList().size() > 0) {
            autresVoyages = true;
        }

        return autresVoyages;
    }

    @Override
    public Voyage findVoyageEnCoursUsager(Usager usager) {
        Date currDate = new Date();
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
        Root<Voyage> root = cq.from(Voyage.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("usager"), usager),
                        cb.equal(root.get("statut"), Voyage.statutDebutVoyage),
                        cb.equal(root.<Date>get("dateArrivee"), currDate)
                )
        );
        cq.orderBy(cb.asc(root.get("dateDepart")));
        Voyage res;
        try {
            res = getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            res = null;
        }
        return res;
    }
    
    
    @Override
    public Voyage findSiVoyagePlanifie(Usager usager, int NbPassagers, Calendar depart, Calendar arrivee) {
        try {
            java.util.Date dateDepart = depart.getTime();
            java.util.Date dateArrive = arrivee.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);
            cq.where(
                    cb.and(
                            cb.equal(root.get("usager"), usager),
                            cb.equal(root.get("statut"), Voyage.statutDebutVoyage),
                            cb.equal(root.<Date>get("dateDepart"), dateDepart)
                    )
            );
            return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
            
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public boolean verifierSiVoyagePasse(Long IdVoyage) {
        boolean voyagePasse = false;
        java.util.Date todayDate = new Date();

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
        Root<Voyage> root = cq.from(Voyage.class);

        cq.where(
                cb.and(
                        cb.equal(root.get("id"), IdVoyage),
                        cb.lessThan(root.<Date>get("dateDepart"), todayDate)
                )
        );

        if (getEntityManager().createQuery(cq).getResultList().size() > 0) {
            voyagePasse = true;
        }

        return voyagePasse;
    }
    
    
    
    @Override
    public boolean findVoyagesUsagerPeriode(Usager usager, Calendar depart, Calendar arrivee) {
        boolean autresVoyagesPrevus = false;
        java.util.Date dateDepart = depart.getTime();
        java.util.Date dateArrive = arrivee.getTime();
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
        Root<Voyage> root = cq.from(Voyage.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("usager"), usager),
                        cb.equal(root.get("statut"), Voyage.statutDebutVoyage),
                        cb.greaterThanOrEqualTo(root.<Date>get("dateArrivee"), dateDepart)
                )
        );

        
        if (getEntityManager().createQuery(cq).getResultList().size() > 0) {
            autresVoyagesPrevus = true;
        }

        return autresVoyagesPrevus;
    }
  

}
