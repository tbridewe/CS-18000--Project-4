# CS-18000---Project-4

The README must include:

1. Instructions on how to compile and run your project. 
2. A list of who submitted which parts of the assignment on Brightspace and Vocareum. 
For example: Student 1 - Submitted Report on Brightspace. Student 2 - Submitted Vocareum workspace.
3. A detailed description of each class. This should include the functionality included in the class, the testing done to verify it works properly, and its relationship to other classes in the project. 

_________________________________________________________________________________________________________________________________________________________

Common Features
While the specifics of each implementation will be related to the option you select, there are several common features for every project. 

User Interactions
- All Project 4 input and output should be handled via the keyboard (System.in) and screen (System.out), just as with all of your previous projects. There is no GUI for this project. 

Data 
- Data must persist regardless of whether or not a user is connected. If a user disconnects and reconnects, their data should still be present. 
  - If a user creates an account and then closes the application (it is no longer running), they should still be able to log in when running it again.
- Descriptive errors should appear as appropriate. For example, if someone tries to log in with an invalid account. The application should not crash under any circumstances. 

Roles
- There will be two defined roles in the application: Sellers and Customers.
  - Sellers will be able to create stores to sell their products and maintain relationships with customers. 
  - Customers will be able to purchase products and contact sellers. 
- Users will select their role  while creating an account, with permissions associated with their actions accordingly. 
- Permissions details will be noted in the option requirements. 

Accounts
- Users can create, edit, and delete accounts for themselves.
  - The attributes you collect as part of account creation are up to you. At a minimum, there should be an email and password associated with each account. 
  - Users should be required to either create an account or sign in before gaining access to the application. 
  - Whichever identifier you maintain for the user must be unique. 
  - During account creation, a user will specify their role. 
  
Stores
- Each application must support multiple stores.
- Sellers will be able to create as many stores as they like.
- Customers can access any store that has been created. 

Note: The term "user" is used to refer to any user of the application, including both customers and sellers. 
