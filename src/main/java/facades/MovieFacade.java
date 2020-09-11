package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
  

    public Movie addMovie() {
        Movie movie = new Movie(1997, "Ostekvaseren", "kalle");
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } finally {
            em.close();
        }
    }
    
    

    public long countAllMovies() {
        EntityManager em = getEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return count;
        } finally {
            em.close();
        }

    }

    public Movie getMovieFromYear(int year) {
        EntityManager em = emf.createEntityManager();
        Movie movie = null;
        try {

            Query query = em.createNamedQuery("Movie.getByYear");
            query.setParameter("" + "year", year);
            movie = (Movie) query.getSingleResult();

        } finally {
            em.close();
        }

        return movie;
    }

    public Movie getMovieFromTitle(String title) {
        EntityManager em = emf.createEntityManager();
        Movie movie = null;
        try {
            Query query = em.createNamedQuery("Movie.getByTitle");
            query.setParameter("title", title);
            movie = (Movie) query.getSingleResult();

        } finally {
            em.close();
        }

        return movie;
    }
     
    public Movie getMovieById(long id){
        EntityManager em = emf.createEntityManager();
        try {
              Query query = em.createNamedQuery("Movie.getById");
              query.setParameter("id", id);
              Movie movie = (Movie) query.getSingleResult();
              return movie;
        }         
        finally {
            em.close();
        }
    }
}
