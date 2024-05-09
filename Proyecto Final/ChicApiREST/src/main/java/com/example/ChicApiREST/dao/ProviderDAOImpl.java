package com.example.ChicApiREST.dao;

import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.BusinessType;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.repositories.ProviderRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProviderDAOImpl implements ProviderDAO{

    private Session session; // Hibernate

    private ProviderRepository providerRepository;

    public ProviderDAOImpl(Session session, ProviderRepository providerRepository) {
        this.session = session;
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> findByBusinessTypeEager(BusinessType businessType) {

        Query<Provider> query = session.createQuery("SELECT p FROM Provider p JOIN p.businessType bt WHERE bt = :businessType", Provider.class);
        query.setParameter("businessType", businessType);

        List<Provider> providers = query.list();

        return providers;
    }
}
