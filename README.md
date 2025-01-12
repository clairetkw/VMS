# VMS (Volunteer Management System)
## Description
### App Summary
1. A full-stack digital platform for new volunteer signups along with a CRM dashboard to enable staff to administer new programs and enrol new volunteers.
2. This is a pro-bono project done to help a local charity organization to manage volunteer signups, as well as manage its programs more efficiently. A simple process of streamlining was performed to migrate some of the manual tasks commonly performed by staff to a digital platform.
3. Tech stacks - ReactJS with TypeScript, React Query, Tailwind CSS, Spring-Boot and Spring Security + Firebase Auth.
4. Deployment pipeline - GitHub, AWS CodePipeline and AWS MySql RDS.
5. App in test environment (on port 8080) is using H2 in-memory database. 


### Relational Diagram
![My Image](RelationalDiagrams.png)

### Authentication & Authorization Flow between frontend and backend
1. Firebase Auth for authentication and authorization.
2. Setup Security Filter Chain and Firebase filter (in backend) to validate/verify all API requests, which require a JWT token.


![My Image](Auth_process.png)

### CICD Pipeline implemented
1. Github (Code Repository)
2. AWS Elastic Registry Service (Image Repository with vulnerability scan)
3. AWS Elastic Container Service - Fargate (with Load balancer and auto-scale)
4. AWS Parameter Store (Credential Management)
5. AWS CodePipeline (Github > CodeBuild > AWS Image Repo - ERS > Deploy)
6. AWS RDS MySQL (Database)

## Team Members

- [Jonathan](https://github.com/goodwill80 "jonathan's github")
- [Ace](https://github.com/acetay "ace's github")
- [Yingwang](https://github.com/shiywsg "yingwang's github")
- [Claire](https://github.com/clairetkw "claire's github")
