# CS-18000--Project-4--README

## Running the Project
To run Project 4, navigate to the 
___
## Submissions
- Brighspace
- Vocareum
___
## User
Provides implementation of a User in the Marketplace, either of a Seller or a Customer.
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
| String email, String password, int userType | Creates a new User object. |
### Methods
| Method | Modifiers and Parameters | Returns | Description |
| -------| ---------- | ------- | ----------- |
| getEmail | public none | String | Returns the email of the current User object. |
| getPassword | public none | String | Returns the password of the current User object. |
| isBuyer | public none | boolean | Returns a boolean whether the user is a buyer or not. |
| isSeller | public none | boolean | Returns a boolean whether the user is a seller or not. |
| setUserType | public int userType | void | Sets the userType of the current User Object. userType of 0 sets the User as a buyer. userType of 1 sets the User as a seller. |
| setEmail | public String email | void | Sets the email of the current User object. If the email is not valid, then the method will throw the InvalidUserInput exception. |
| setPassword | public String password | void | Sets the password of the current User object. If the password is not valid, then the method will throw the InvalidUserInput exception. |
| accountExists | public static String emailEntered | boolean | Checks if a user's information is currently in the userData.txt file. Reads the file, and if any of the lines have an indexOf the emailEntered that is greater than 1 then the method returns true. Otherwise, returns false. In the case of FileNotFoundException and IOException, also returns false and prints the error message. |
| readUserData | public static none | ArrayList<String> | Reads the userData.txt file and returns all of the results as an ArrayList<String>. The entries in the ArrayList are indexed in order that they were read. In the case of FileNotFoundException or IOException, returns null and print the error message.
| saveNewUser | public static String email, String password, String userType | void | Opens a FileWriter in append mode and writes the user's information to the userData.txt file in the following format: Email:%s, Password:%s, UserType: %s. In the case of an IOException, prints the error message and does not save the new user's information. |
| isCorrectLogin | public static String emailEntered, String passwordEntered | int | Verifies whether the user has a correct login by first calling readUserData(). After the data has been read, if the passwordEntered and emailEntered are equal to the email and password of any line, returns the userType of the user. Buyer = 0, Seller = 1. Returns -1 if the login is not correct (no matching password and email have been found in the userData.txt file). |
| isValidPassword | public static String password | boolean | Verifies if the password passed is valid or not. A valid password is not null and has a length > 0. If the password is valid, returns true. Otherwise, returns false. |
| isValidEmail | public static String email | boolean | Verifies if the email passed is valid or not. A valid email has an @ that is not at the first index of the string, a 3 letter dot ending (such as .com, .hub, etc.) and the space between the extension and @ are 3 characters apart (Ex. email@bro.com). If the email is valid, returns true. Otherwise, returns false. |
| editUser| public String newEmail, String newPassword, boolean deleted | void | Allows the user to edit their information. For newEmail and newPassword != null, verifies that email and/or password are valid. Calls readUserData() to receive userData in an ArrayList, and iterates through each of the lines in the userData.txt file. If the email and password of the User object are the same as the email and password on file, then edits the string to contain the new email and/or password. If the deleted boolean is true, then the string is removed entirely. Rewrites all of the new information to the userData.txt file afters with a PrintWriter. |
| getDisplayedItem | public int index | Item | Returns the listing at the index - 1 passed to the method in the sortedListings ArrayList. Throws an IndexOutOfBoundsException if the value at index - 1 in sortedListings is out of bounds.
| loadListings | protected String fileName| void | Calls the readFile() method with the passed fileName. Iterates through the read file and sets the listings to an Item ArrayList.
| printListings | public none | void | Prints the formatted listings in their current sorted or unsorted order.
| writeFile | protected static String filename, String[] lines | void | Writes to the provided file with the provided array of strings.
| readFile | protected static String filename | String[] | Reads from the file and returns an array of file lines.
| unsortListings | public none | void | Sets the sortedListings field to the initial listings field.
### Customer
Provides implementation of a Customer in the Marketplace
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
### Methods
| Method      | Description |
| ----------- | ----------- |
| method1     |             |
| method2     |             |
## Seller
Provides implementation of a Customer in the Marketplace
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
| String email, String password, int userType| Creates a new Seller object which extends User. Seller's stores are also instantiated |
### Methods
| Method | Parameters | Returns | Description |
| -------| ---------- | ------- | ----------- |
| getStores | none | ArrayList \<String\> | |
| saveStores | none | void | |
| displayItems | none | ArrayList \<String\> | reads file with item listings and displays listings that the seller posted |
| addFromCSV | String filename | void | reads file with item listings and adds them to the exisitng item listings |
| addNewItem | Item item | void | allows a seller to add one item to item listings at a time |
| removeItem | Item item | void | allows a seller to remove one item from item listings at a time |
| editItem | Item item, int changeType, String changeValue | void | seller defines the item to change, what value type to change, and what value to change it to and the item value is changed |
| viewAllStats | none | void | allows seller to see all the stats from their store |
| sortStats | int sortType, int sortOrder | void | allows a seller to see stats based  type and order |
## Item
A listing in the Marketplace
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
### Methods
| Method      | Description |
| ----------- | ----------- |
| method1     |             |
| method2     |             |
## Menu
Displays prompts for the user
## IncorrectLoginInformationException
Thrown when login information is incorrect
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
## InvalidLineException
Thrown when line is invalid
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
## InvalidQuantityException
Thrown when quantity is invalid
### Constructor 
| Parameters | Description |
| ---------- | ----------- |
## InvalidUserInputException
Thrown when user input is invalid
### Constructor 
| Parameters | Description |
| ---------- | ----------- |

___

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
  - The attributes you collect as part of account creation are up to you. At a minimum, there should be an email and password associated with each
  account. 
  - Users should be required to either create an account or sign in before gaining access to the application. 
  - Whichever identifier you maintain for the user must be unique. 
  - During account creation, a user will specify their role. 
  
Stores
- Each application must support multiple stores.
- Sellers will be able to create as many stores as they like.
- Customers can access any store that has been created. 

Note: The term "user" is used to refer to any user of the application, including both customers and sellers. 
___
Core
- Market
  - The marketplace will be a centralized page listing available
products for purchase.
  - Products will include the following information:
    - Name of the product
    - The store selling the product.
    - A description of the product
    - The quantity available for purchase
    - The price
  - The marketplace listing page will show the store, product name, and price of the available goods. Customers can select a specific product to be taken
  to that product's page, which will include a description and the quantity available.
  - When items are purchased, the quantity available for all users decreases by the amount being purchased.
  
- Sellers
  - Sellers can create, edit, or delete products associated with their stores.
  - Sellers can view a list of their sales by store, including customer information and revenues from the sale.

- Customers
  - Customers can view the overall marketplace listing products for sale, search for specific products using terms that match the name, store, or
  description, and sort the marketplace on price or quantity available.
  - Customers can purchase items from the product page and review a history of their previously purchased items.
___
Selections
  - Files
    - All file imports must occur as a prompt to enter the file path.
    - Sellers can import or export products for their stores using a csv file.
      - All product details should be included, with one row per product.
    - Customers can export a file with their purchase history.
  - Statistics
    - Sellers can view a dashboard that lists statistics for each of their stores.
      - Data will include a list of customers with the number of items that they have purchased and a list of products with the number of sales.
      - Sellers can choose to sort the dashboard.
    - Customers can view a dashboard with store and seller information.
      - Data will include a list of stores by number of products sold and a list of stores by the products purchased by that particular customer.
      - Customers can choose to sort the dashboard.
- Shopping cart
  - Customers can add products from different stores to a shopping cart to purchase all at once, and can remove any product if they choose to do so. The
  shopping cart is preserved between sessions, so a customer may choose to sign out and return to make the purchase later.
  - Sellers can view the number of products currently in customer shopping carts, along with the store and details associated with the products.
___
Optional Features:
- Sellers can elect to hold sales that reduce the price of a product until a specified number of units are sold. Customers will be informed of the
original and sale price when browsing the marketplace.
- Customers can leave reviews associated with specific products from sellers. Other customers can view the reviews after they post. Sellers may view
reviews on their products.
- Sellers may set a per product order quantity limit that prohibits customers from ordering more units than the limit. Customers will not be able to
place any additional orders for a product after they reach the limit, unless the seller increases or removes it.
