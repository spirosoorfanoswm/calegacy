set SPRING_JPA_DATABASE=creditark
set SPRING_DATASOURCE_PLATFORM=postgres
set SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/d_depa
set SPRING_DATASOURCE_USERNAME=postgres
set SPRING_DATASOURCE_PASSWORD=root


set SECURITY_ISSUER_URI=http://localhost:9080/auth/realms/dev
set SECURITY_BASIC_ENABLED=true
set ENABLE_SCHEDULING=false
set CA_DEFAULT_USER=system_scenario
set MGMT_SECURITY_ENABLED=false
set CA_SYSTEM_SCENARIO=true
set CA_SYSTEM_SCENARIO_USER=system_scenario
set OAUTH_CLIENT_ID=employee-service
set OAUTH_CLIENT_SECRET=e44c7c46-cfe7-4c9a-871d-ffee8b7f775e
set OAUTH_CLIENT_USER_AUTH_URI=${SPRING_DATASOURCE_URL}/protocol/openid-connect/auth
set OAUTH_CLIENT_ACCESS_TOKEN_URI=${SECURITY_ISSUER_URI}/protocol/openid-connect/token
set OAUTH_CLIENT_SCOPE=openid
set OAUTH_RESOURCE_TOKEN=${SECURITY_ISSUER_URI}/protocol/openid-connect/token/introspect
set RECUR_PHASE=0 18 09 1/1 * ?
set REPORTS_PATH=F:\\reports
set CREATE_SCENARIO_REPORT=true

