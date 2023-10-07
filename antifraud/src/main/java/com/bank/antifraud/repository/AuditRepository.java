package com.bank.antifraud.repository;

import com.bank.antifraud.entity.Audit;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
    @Timed
    @Query(value = "select id, entity_type, operation_type, " +
            "created_by, modified_by, created_at, modified_at," +
            "new_entity_json, entity_json " +
            "from anti_fraud.audit " +
            "where " +
            "entity_type= :entityType and " +
            "cast(json_extract_path_text(cast(entity_json as json), 'id') as integer) = :id " +
            "LIMIT 1", nativeQuery = true)
    Optional<Audit> findById(@Param("entityType") String entityType,
                             @Param("id") Long id);

}
