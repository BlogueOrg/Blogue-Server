# Blogue-Server
### Environment Configuration
- **local**
  - Host: localhost
  - Database: PostgreSQL (running locally)
- **test**
  - Host: Ubuntu (GitHub Actions)
  - Database: PostgreSQL (Docker container)
- **dev**
  - Host: Ubuntu (AWS EC2)
  - Database: PostgreSQL (AWS RDS)
- **prod**
  - Host: -
  - Database: -
 
### Git flows
- **feature branches**
  - each feature is developed in its own branch
  - contain the features planned for next releases
- **develop branch**
  - completed features for development are integrated in this branch
  - after merging into the develop branch, ci/cd for develop server is triggered
- **main branch**
  - contains stable code intended for production deployment.
  - (after merging into the main branch, ci/cd for production server is triggered) 
