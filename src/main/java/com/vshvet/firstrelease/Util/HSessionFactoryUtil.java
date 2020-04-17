package com.vshvet.firstrelease.Util;

import com.vshvet.firstrelease.Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


//session creation utility
public class HSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(AutoModel.class);
                configuration.addAnnotatedClass(AutomobileEngine.class);
                configuration.addAnnotatedClass(MeasurementUnits.class);
                configuration.addAnnotatedClass(Elements.class);
                configuration.addAnnotatedClass(Parameters.class);
                configuration.addAnnotatedClass(SuperchargedType.class);
                configuration.addAnnotatedClass(Cylinders.class);
                configuration.addAnnotatedClass(Parameters.class);
                configuration.addAnnotatedClass(EngineType.class);
                configuration.addAnnotatedClass(EngineManufacturer.class);
                configuration.addAnnotatedClass(Engine.class);
                configuration.addAnnotatedClass(EngineNumber.class);
                configuration.addAnnotatedClass(FuelType.class);
                configuration.addAnnotatedClass(AutoManufacture.class);
                configuration.addAnnotatedClass(ParameterNames.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
                System.out.println("Исключение!Исключение!Исключение!Исключение!Исключение!Исключение!Исключение!" );
            }
        }
        return sessionFactory;
    }
}