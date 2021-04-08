-- View: d_ooredoo_mega.mv_get_customer_data

-- DROP MATERIALIZED VIEW d_ooredoo_mega.mv_get_customer_data;

CREATE MATERIALIZED VIEW d_ooredoo_mega.mv_get_customer_data
TABLESPACE pg_default
AS
 SELECT t1.context_id,
    t1.customer_id,
    t1.snapshot_date,
    t5.descr AS portfolio,
    t4.vat_number,
    t4.customer_name,
    t4.affiliate,
    t6.group_name,
    t4.industry_id,
    t4.area_id,
    t4.customer_since,
    t4.optimization_excluded,
    t4.assessment_excluded,
    t7.descr AS customer_status,
    t1.profit_margin,
    t1.exposure,
    t1.aging,
    t1.activity,
    t1.mitigants,
    t1.criteria,
    t1.behavioral_score,
    t1.credit_ark_score,
    COALESCE(t3.pd_life, 0::double precision) AS pd,
    t1.provisions,
    t1.balance,
    t1.past_dues,
    t1.turnover,
    t1.payments,
    12 AS p0_periods,
    12 AS p1_periods,
    t8.snapshot_date AS qualitative_snapshot_date,
    t8.qualitative_score,
    t8.data_trunk,
    t9.assessment_date,
    t9.assessment,
    t9.entity_info,
    max(t1.balance) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_max_balance,
    last_value(t1.snapshot_date) OVER (PARTITION BY t1.customer_id ORDER BY t1.balance, t1.snapshot_date ROWS BETWEEN 11 PRECEDING AND CURRENT ROW) AS p0_max_balance_date,
    min(t1.balance) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_min_balance,
    max(t1.past_dues) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_max_past_due,
    avg(t1.balance) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_avg_balance,
    avg(t1.turnover) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_avg_turnover,
    avg(t1.payments) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_avg_payments,
    max(t2.score::text) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_worst_behavioral_score,
    max(t3.score::text) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN CURRENT ROW AND 11 FOLLOWING) AS p0_worst_credit_ark_score,
    max(t1.balance) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_max_balance,
    min(t1.balance) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_min_balance,
    max(t1.past_dues) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_max_past_due,
    avg(t1.balance) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_avg_balance,
    avg(t1.turnover) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_avg_turnover,
    avg(t1.payments) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_avg_payments,
    max(t2.score::text) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_worst_behavioral_score,
    max(t3.score::text) OVER (PARTITION BY t1.customer_id ORDER BY t1.snapshot_date DESC ROWS BETWEEN 12 FOLLOWING AND 23 FOLLOWING) AS p1_worst_credit_ark_score
   FROM d_ooredoo_mega.v_snapshot t1
     JOIN d_ooredoo_mega.m_score t2 ON t1.context_id = t2.context_id AND t1.behavioral_score::text = t2.score::text
     JOIN d_ooredoo_mega.m_score t3 ON t1.context_id = t3.context_id AND t1.credit_ark_score::text = t3.score::text
     JOIN d_ooredoo_mega.d_customer t4 ON t1.context_id = t4.context_id AND t1.customer_id::text = t4.customer_id::text
     LEFT JOIN d_ooredoo_mega.d_portfolio t5 ON t5.context_id = t4.context_id AND t5.portfolio_id = t4.portfolio_id
     LEFT JOIN d_ooredoo_mega.d_group t6 ON t6.context_id = t4.context_id AND t6.group_id::text = t4.group_id::text
     LEFT JOIN d_ooredoo_mega.m_customer_status t7 ON t1.context_id = t7.context_id AND t1.customer_status_id = t7.customer_status_id
     LEFT JOIN d_ooredoo_mega.d_qualitative_data t8 ON t1.context_id = t8.context_id AND t1.customer_id::text = t8.customer_id::text AND t1.snapshot_date = t8.snapshot_date
     LEFT JOIN d_ooredoo_mega.d_ext_assessment t9 ON t1.context_id = t9.context_id AND t1.customer_id::text = t9.customer_id::text AND t1.snapshot_date = t9.assessment_date
WITH DATA;

ALTER TABLE d_ooredoo_mega.mv_get_customer_data
    OWNER TO postgres;
