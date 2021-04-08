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
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO " + schema + ", public, core, ext").execute();
        } catch (Exception e) {
            logger.error("contextinfospec {}", e);
        }
        response.setRules( jdbcTemplate.query(
                "select port.descr as portfolio, v_rule.value as value, "
                        + "v_label.description  as desc from d_validation_rules as v_rule "
                        + "join d_portfolio as port on v_rule.portfolio_id=port.portfolio_id "
                        + " join d_validation_rules_labels as v_label on v_label.id=v_rule.d_validation_rule_label_id where v_rule.context_id=?",
                new Object[]{contextId},
                (rs, rowNum) ->
                        new ValidationRule(rs.getString("portfolio"),
                                rs.getString("desc"),
                                rs.getString("value")
                        )
        ));
        return response;
    }
}
