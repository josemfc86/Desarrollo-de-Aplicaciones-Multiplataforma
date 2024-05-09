package com.example.ChicApiREST.dao;

import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.BusinessType;
import com.example.ChicApiREST.entities.Provider;

import java.util.List;

public interface ProviderDAO {
    public List<Provider> findByBusinessTypeEager(BusinessType businessType);
}
