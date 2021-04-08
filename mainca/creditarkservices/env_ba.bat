set SPRING_DATASOURCE_PLATFORM=postgres
set SPRING_DATASOURCE_URL=jdbc:postgresql://192.168.31.111:5432/postgres
set SPRING_DATASOURCE_USERNAME=postgres
set SPRING_DATASOURCE_PASSWORD=postgres
set SECURITY_ISSUER_URI=http://localhost:9080/auth/realms/dev
set SECURITY_BASIC_ENABLED=true
set MGMT_SECURITY_ENABLED=false
set OAUTH_CLIENT_ID=employee-service
set OAUTH_CLIENT_SECRET=1c40815b-ea99-44b2-97b8-dcf772fad1e8
set OAUTH_CLIENT_USER_AUTH_URI=${SPRING_DATASOURCE_URL}/protocol/openid-connect/auth
set OAUTH_CLIENT_ACCESS_TOKEN_URI=${SECURITY_ISSUER_URI}/protocol/openid-connect/token
set OAUTH_CLIENT_SCOPE=openid
set OAUTH_RESOURCE_TOKEN=${SECURITY_ISSUER_URI}/protocol/openid-connect/token/introspect
set recur=0 40 21 1/1 * ?

