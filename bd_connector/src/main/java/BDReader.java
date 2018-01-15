import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BDReader {

    public static <T extends DataInterface> String getXmlDataById(Class className, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        @SuppressWarnings("unchecked")
        CriteriaQuery<T> query = builder.createQuery(className);
        @SuppressWarnings("unchecked")
        Root<T> root = query.from(className);
        query.select(root)
            .where(builder.equal(root.get("id"), id));
        T queryResult = session
            .createQuery(query)
            .getSingleResult();
        tx.commit();
        return queryResult.getData();
    }
}
