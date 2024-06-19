SPW4 - Exercise 3
=================

Name: _____________

Effort in hours: __

## 1. Connect Four Web Application and CI/CD Pipeline

### Task 1.a

Tomcat starts successfully and connect four is playable
![img.png](docs/tomcat_run_test_works.png)
Maven Compile: 
![img.png](docs/maven_compile.png)
Maven test:
![img.png](docs/maven_test.png)
Maven package:
![img.png](docs/maven_package.png)

### Task 1.b
The pipeline works as expected, you can see it runs through all cases required
![img.png](docs/gitlab_pipeline_showcase.png)

### Task 1.c
Pretty much the same pipeline, but now runs on github instead of gitlab
differs in analyze step => uses maven analyze
![img.png](docs/Connect4GithubPipeline.png)
