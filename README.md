# Bookeroo E-commerce Web Application

# RMIT SEPT 2021 Major Project

## Members
* Adhiraj Jain (s3821245)
* Devin Nimesh Amalean (s3821117)
* Jainam Harshal Doshi (s3825891) 
* Sabarish Venkatesan (s3696187)
* You chan Lee (s3850825)

## Records

* Application Link: https://master.dekig6gu29i4c.amplifyapp.com/

* Github repository: https://github.com/Adhiraj-Jain/Bookeroo-Ecommerce-Web-App
* jira Board: https://team-1626776183602.atlassian.net/secure/RapidBoard.jspa?projectKey=SGP&rapidView=1&view=planning.nodetail&atlOrigin=eyJpIjoiZmY1MjY3M2JlZTVhNDVhZTg3MDlkODM0YTg2ZDhjOWEiLCJwIjoiaiJ9
* CircleCI Link: https://app.circleci.com/pipelines/github/jam-dos/SEPT-Tue-1830-Group2

	
## Code documentation - Release 4.0.0 - 23/10/2021

* Registration of public and publisher accounts
* Login for public, publisher and admin accounts
* Admin features like adding a user
* Admin features like approving a user
* Admin features like blocking a user
* Admin can add Books
* Admin can edit Books
* Book HomePage
* Book Details page
* Users can buy books
* Users can sell books
* Users can share books
* Admins can view all the transaction history
* Transactions are done through PayPal API
* Users can cancel order within 2 days
* Admin can approve cancelation orders
* Admin can reject cancelation orders
* All user can search book through title, authors and categrories 
* Admin can edit users
* Admin can sort transactions based on oldest and newest first
  

To run the application locally : 
1) cd into each and every microservice (ms_booking, ms_availability, ms_profiles, ms_service) and run :
2) ./mvnw package && java -jar target/ms_[microservice]-0.0.1-SNAPSHOT.jar
3) cd into FrontEnd/myfirstapp
4) run "npm install"
5) run "npm start"



