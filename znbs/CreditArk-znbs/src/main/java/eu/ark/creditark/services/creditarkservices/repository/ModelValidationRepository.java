package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.model.ValidationRule;
import eu.ark.creditark.services.creditarkservices.model.ValidationRules;
import eu.ark.creditark.services.creditarkservices.utils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class ModelValidationRepository {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final JdbcTemplate jdbcTemplate;

    public ModelValidationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ValidationRules getAvailableModels(int contextId, String schema) {
        ValidationRules response = new ValidationRules();

        return response;
    }
}
