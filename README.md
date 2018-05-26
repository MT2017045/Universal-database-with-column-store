# Universal-database-with-column-store
Implementation of column store with underlying mysql database with implemented adhoc queries 

This implementation is useful for those databases which have too much columns. ColumnStore store all the columns as seperate tables so that retrieving one column may not unneccesary retrieve all other columns, thus improving the performance time.


to run the project make necessary changes to yourInfo.java which requires the login credential for mysql server.
it also have information about the steps to create the database to make the project work.

the first file to run is : newRunGUI.java 


