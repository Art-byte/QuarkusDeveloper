package com.artbyte.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

@MongoEntity(collection = "company")
public class Company extends ReactivePanacheMongoEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @NotBlank
    public String name;
    @NotBlank
    public String createdByUser;
    public Boolean activated = true;
    public Instant createdDate = Instant.now();
    public String lastModifiedByUser;
    public Instant lastModifiedDate = Instant.now();


    public static Multi<Company> findActivateCompanies(){
        return stream("activated", true);
    }

    public static Multi<Company> findActiveCompaniesByUser(String user){
        return stream("activated = ?1 and createdByUser = ?2", true, user);
    }

}
