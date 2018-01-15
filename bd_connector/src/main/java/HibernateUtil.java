import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory(){
        Properties props = new Properties();
        Configuration configuration = new Configuration();

        try{
            InputStream is = HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties");
            props.load(is);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't find 'hibernate.properties' file", e);
        } catch (IOException e) {
            throw new IllegalStateException("Read file 'hibernate.properties' file error", e);
        }

        configuration.setProperties(props)
            .addAnnotatedClass(CredentialRecordEntity.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties())
            .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
}
