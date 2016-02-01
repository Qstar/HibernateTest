package test;

import entity.Students;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.util.Date;

public class StudentTest {
    private static SessionFactory ourSessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Session session;
    private static Transaction transaction;


    @Before
    public void init() {
        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = ourSessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void destroy() {
        transaction.commit();
        session.close();
        ourSessionFactory.close();
    }

    @Test
    public void testWriteStudents() throws Exception {
        Students s = new Students(1, "武当山", new Date(), "男", "张三丰");
        File f = new File("/Users/Qstar/Desktop/favicon.jpg");
        InputStream input = new FileInputStream(f);
        Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
        s.setPicture(image);
        session.save(s);
    }

    @Test
    public void testReadBlob() throws Exception {
        Students s = (Students) session.get(Students.class, 1);
        Blob image = s.getPicture();
        InputStream input = image.getBinaryStream();
        File f = new File("/Users/Qstar/Desktop/faviconRead.jpg");
        OutputStream output = new FileOutputStream(f);
        byte[] buff = new byte[input.available()];
        input.read(buff);
        output.write(buff);
        input.close();
        output.close();
    }

}
