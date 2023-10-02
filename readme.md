# Gwizd - backend
## Project for 2023 Hackyeah.
### Marcin Bator, Wiktor Mazur, Bogdan Bosak
#### Demo version: https://gwizd.online
## Prerequisites
- Java JDK17
- Spring Boot 2.7.15
- Docker
- IntelliJ IDEA
## Installation

Install using docker:

```bash
  docker-compose up -d
```
Make sure you have port 5432 free on your Docker machine. \
Then run application with IntelliJ IDEA with standard configuration.

## Overview
#### The frontend of this app can be found on : https://github.com/Chopy4/gwizd-backend


The application is designed to allow the user to record their observations 
of animals in the city. You can choose one of 3 types of observations: 
ordinary observation, animal threat observation and missing pet observation. 
The location is downloaded from the device. If the threat option is selected,
a notification containing the exact location is sent to the e-mail address 
(currently the authors' private e-mail address) informing about the need 
for intervention by the appropriate services. In other cases, the observation
is saved on the user's account and the owners of the application also have 
access to it in order to keep statistics. The user receives achievements 
for his observations (functionality not available in the demo application, 
but available in the database), which encourages him to document his 
observations, while working to the benefit of the Voivodeship.


Each observation is recorded on the map available in the "Neighborhood" 
tab. Areas marked with circles do not reveal the exact location, allowing 
users to determine its approximate location, and the exact location is 
listed in the database. The tags disappear after an hour, except for those 
that required reporting to the services - they must be removed manually. 
The user can also track their observations in the "Profile" tab (upper 
right corner). In addition, there is a "Statistics" tab, where one 
demonstrative filtering is available to maintain statistics by Voivodeship.


To increase society's sensitivity to the presence of animals in the city, 
an information tab "Animals in the city" was created. Moreover, realizing 
that users will be more likely to use the application on their phone, 
the website is fully responsive and works well on small resolutions.
    