#Stock Handling Managment REST API 

This project aims to identify some of latest technologies and best practices applied in JAVA , SPRING BOOT.

While Business is intended to be simple as a result of focusing on used technologies but it also cover some of edge cases and race conditions that might be take place
in typical work environments.

Business briefly describes how to manage a stock of products by 
 
 ** Product possible Operations
 -- Add  Product
 -- Update Existing Product
 -- Get List of Products / Get Detialed Product
 -- Delete Product
 
 **  Stock Possible Operations
 -- Add/Update Stock for Existing Product
 -- Get List of Existing Stock 
 -- Get Detialed Stock by Product ID
 -- Get Statistics about Top Available and Top Sold Products
 -- Delete Stock for a certain product
 
 ** Assumptions And Constraints
 
 -- Product Must be added firstly before Add/Update Stock othre wise get Error Message
 -- Mapping relation between Product and Stock is Unidirectional One to One for sake of performance. 
 -- So based on above Stock table must have only one unique product , Stock can not have multiple products.
 -- Get Stock Statistics by Date Range since Last Month till Today or just Today
 -- Stock can be updated with product quantity and sold quantity if exists
 -- concurrent Stock updating not allowed and will return error message , scenario can be simulated on Jmeter (https://jmeter.apache.org/)

---

## Project Architecture

Project is build with  robust and extensible architecture which allow for future updates , considering using Generics , Java 8 Streams
Also project is runnig by default on H2 Database and also Mysql script exists for sake of multi DB providers

1. Entity package which include DB
2. Click the README.md link from the list of files.
3. Click the **Edit** button.
4. Delete the following text: *Delete this line to make a change to the README from Bitbucket.*
5. After making your change, click **Commit** and then **Commit** again in the dialog. The commit page will open and you’ll see the change you just made.
6. Go back to the **Source** page.

---

## Create a file

Next, you’ll add a new file to this repository.

1. Click the **New file** button at the top of the **Source** page.
2. Give the file a filename of **contributors.txt**.
3. Enter your name in the empty file space.
4. Click **Commit** and then **Commit** again in the dialog.
5. Go back to the **Source** page.

Before you move on, go ahead and explore the repository. You've already seen the **Source** page, but check out the **Commits**, **Branches**, and **Settings** pages.

---

## Clone a repository

Use these steps to clone from SourceTree, our client for using the repository command-line free. Cloning allows you to work on your files locally. If you don't yet have SourceTree, [download and install first](https://www.sourcetreeapp.com/). If you prefer to clone from the command line, see [Clone a repository](https://confluence.atlassian.com/x/4whODQ).

1. You’ll see the clone button under the **Source** heading. Click that button.
2. Now click **Check out in SourceTree**. You may need to create a SourceTree account or log in.
3. When you see the **Clone New** dialog in SourceTree, update the destination path and name if you’d like to and then click **Clone**.
4. Open the directory you just created to see your repository’s files.

Now that you're more familiar with your Bitbucket repository, go ahead and add a new file locally. You can [push your change back to Bitbucket with SourceTree](https://confluence.atlassian.com/x/iqyBMg), or you can [add, commit,](https://confluence.atlassian.com/x/8QhODQ) and [push from the command line](https://confluence.atlassian.com/x/NQ0zDQ).