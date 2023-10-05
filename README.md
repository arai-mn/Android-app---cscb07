# android-venue-booker
An Android, Java, and Firebase application that allows for the creation and booking of sports events and venues. Various CRUD functions are split among customers and administrators. Data storage and authentication is done through Google Firebase.

# Table of contents
   * [Application Overview](#application-overview)
      * [Customers](#customers)
      * [Administrators](#administrators)
   * [UML Diagrams](#uml-diagrams)
      * [User UML Diagram](#user-uml-diagram)
      * [Customer UML Diagram](#customer-uml-diagram)
      * [Admin UML Diagram](#admin-uml-diagram)
      * [Venue UML Diagram](#venue-uml-diagram)
      * [Event UML Diagram](#event-uml-diagram)

# Application Overview
For testing out the application with some test data, you can log in as p@p.com with password pppppp to see customer screens, or admin@gmail.com with password admin123 to see admin screens.

Upon entering the application, users are greeted with a login screen. The application has two types of users: customers and administrators, both of which use one login screen. Authentication is done through the Firebase Authentication API. Once a user is authenticated, they are compared to entries in a Firebase Realtime DB to determine whether they are an admin or customer, and are authorized to access the rest of the application.

![image](https://user-images.githubusercontent.com/87666671/210279594-57c1f20c-85de-474a-9094-abeeff672bf7.png)

New customers can also sign up here and their data will automatically be created in both the Firebase Authentication API and Firebase Realtime DB.

![image](https://user-images.githubusercontent.com/87666671/210278543-3db25e3c-904e-4334-813d-8d26881b4b6e.png)

## Customers
Once logged in, customers are able to join Events under the Events screen. All events are added to the application by other customers under existing venues. Only upcoming events are displayed.

![image](https://user-images.githubusercontent.com/87666671/210278774-ed40d116-01dd-47cc-ab1a-27917f52bf26.png)

Joined events are then visible in the customer's home screen.

![image](https://user-images.githubusercontent.com/87666671/210278970-abae7195-5002-406c-aebf-95cec921fdc7.png)

The customer is able to see a list of available venues in the Venues menu. At any of the available venues, a customer is able to see a list of all existing events and create upcoming events. Each venue has a limited number of sports/activities that can be chosen for event creation. This list is determined upon venue creation.

![image](https://user-images.githubusercontent.com/87666671/210279528-df7609c8-08fb-4874-8a91-40780c306309.png)
![image](https://user-images.githubusercontent.com/87666671/210279551-6f9a452d-4420-4e9b-93be-e85b705448ff.png)
![image](https://user-images.githubusercontent.com/87666671/210279077-6d81cf81-05eb-4fc9-8000-15ef574b1b23.png)

In the account screen, users are also able to log out, destroying the current user object and hence the session, and also edit their full names.

![image](https://user-images.githubusercontent.com/87666671/210279487-0e43902c-632d-4549-96fd-35033bd894d9.png)

## Administrators
Once logged in, admins are able to view all events scheduled at the venues that they created and manage, and are able to filter this list by venue.

![image](https://user-images.githubusercontent.com/87666671/210279714-cf9cac15-b163-44da-979e-ea76e5edee33.png)

They can then edit the detais of any existing event.

![image](https://user-images.githubusercontent.com/87666671/210279773-27d15ffe-2e07-4d6a-80bf-6ac9135d9f0e.png)

They can also create new venues and provide the event/sport types that are available at this venue
![image](https://user-images.githubusercontent.com/87666671/210279957-deb49033-db33-4ed4-a4b1-1d00b0fc45b4.png)

# UML Diagrams
## User UML Diagram
![image](https://user-images.githubusercontent.com/87666671/210281340-de56ce84-0207-4694-927f-893cbb7eede0.png)

## Customer UML Diagram
![image](https://user-images.githubusercontent.com/87666671/210281419-96f8e7ef-38be-4fb6-81a9-9c18b15e61bf.png)

## Admin UML Diagram
![image](https://user-images.githubusercontent.com/87666671/210281324-d1b8490c-7ef8-4271-b9f1-1133325edc14.png)

## Venue UML Diagram
![image](https://user-images.githubusercontent.com/87666671/210281481-bb6e5838-d55f-4410-96b0-2ba2525467c0.png)

## Event UML Diagram
![image](https://user-images.githubusercontent.com/87666671/210281530-3661ba94-cf90-4ff0-b7f3-d5e8b4e95ba9.png)



