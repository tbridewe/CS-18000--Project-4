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

---------------------------------------------------------------------------------------------------------------------------------------------------------Core
• Market
o The marketplace will be a centralized page listing available
products for purchase.
▪ Products will include the following information:
▪ Nameoftheproduct
▪ The store selling the product.
▪ Adescriptionoftheproduct
▪ The quantity available for purchase
▪ Theprice
o The marketplace listing page will show the store, product name, and price of the available goods. Customers can select a specific product to be taken to that product's page, which will include a description and the quantity available.
o When items are purchased, the quantity available for all users decreases by the amount being purchased.
• Sellers
o Sellers can create, edit, or delete products associated with
their stores.
o Sellers can view a list of their sales by store, including
customer information and revenues from the sale. • Customers
o Customers can view the overall marketplace listing products for sale, search for specific products using terms that match
the name, store, or description, and sort the marketplace on
price or quantity available.
o Customers can purchase items from the product page and
review a history of their previously purchased items.
Selections
• Files
o All file imports must occur as a prompt to enter the file path. o Sellers can import or export products for their stores using a
csv file.
▪ All product details should be included, with one row per
product.
o Customers can export a file with their purchase history.
• Statistics
o Sellers can view a dashboard that lists statistics for each of
their stores.
▪ Data will include a list of customers with the number of
items that they have purchased and a list of products
with the number of sales.
▪ Sellerscanchoosetosortthedashboard.
o Customers can view a dashboard with store and seller information.
▪ Data will include a list of stores by number of products sold and a list of stores by the products purchased by that particular customer.
▪ Customerscanchoosetosortthedashboard.
• Shopping cart
o Customers can add products from different stores to a shopping cart to purchase all at once, and can remove any product if they choose to do so. The shopping cart is preserved between sessions, so a customer may choose to sign out and return to make the purchase later.
o Sellers can view the number of products currently in customer shopping carts, along with the store and details associated with the products.

Optional Features:
• Sellers can elect to hold sales that reduce the price of a product until a specified number of units are sold. Customers will be informed of the original and sale price when browsing the marketplace.
• Customers can leave reviews associated with specific products from sellers. Other customers can view the reviews after they post. Sellers may view reviews on their products.
• Sellers may set a per product order quantity limit that prohibits customers from ordering more units than the limit. Customers will not be able to place any additional orders for a product after they reach the limit, unless the seller increases or removes it.
