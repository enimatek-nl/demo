# Demo Spring Setup

### What?
This project is a placeholder to see if it's possible to get a clean project with correct dependencies anno 2023.

### Done
- CodeCoverage
- JDK 11+ HttpClient only
- OAuth with Spring Security
- Azure Keyvault Support
- Feign for Consuming API's
- Feign: TracId, OAuth (inc. azure ad/kv)
### Todo
- Complete Telemetry + App Insight support

## Coverage
Use `Coverage Gutters` VSCode extension and run the following:

`./mvnw clean test jacoco:report && mv target/site/jacoco/jacoco.xml cov.xml`