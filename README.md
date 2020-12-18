# CSV2SQL
An application to read .csv files, remove any invalid or duplicate entries, and write it to an SQL database.

# Project Overview
CSV2SQL implements several useful features. It reads entries from a .csv file into the program's memory, and can remove invalid or duplicate entries.
After this, it generates SQL statements to push this data to a local SQL database.
Other features of the program include a console UI, where the user can enter the specific .csv file they want to convert.
The program will inform the user at every stage of the application process as to what is happening. This includes useful information such as the location of the file being read, the location where invalid records are written to for checking, and the time taken for all operations in the program.
When writing to the SQL database, the program will check the .csv file against the database's existing entries to ensure that no duplicates will be added.
The application utilises multi-threading when inserting values into the database to considerably increase the speed of the process.

### Application Walkthrough
CSV2SQL includes a console UI. Upon launching the application, the user is shown the available .csv files to read from.
The user can add their own .csv files simply by placing them in the project's resources folder.
After selecting a .csv file, the program will perform the rest of the operations automatically.
First, the .csv file is read in entirety. After this, the .csv file is cleaned and duplicate or invalid records are removed. 
The removed records are stored in the resources file as .csv files for the user to manually check if they desire.
Next, the program checks the cleaned records to add against the records in the database. If any records already exist in the database, they are removed from the files to add.
After this, the records are pushed to the database, and the program ends.

# How to run
1. Open GIT bash and navigate to your chosen file location to store the project files in.
2. Navigate to the main page of the CSV2SQL repository.
3. Click 'Code', then copy the URL that appears.
4. In GIT bash, type `git clone "<URL HERE>"`, pasting the URL you just copied where specified.
5. Open the CSV2SQL project folder with your IDE of choice.
6. In `src/main/resources`, open `login.properties` and enter valid connection details for an SQL database.
7. (Optional) In `src/main/resources`, add any extra .csv files that you wish to use. 
   There are sample .csv files named `EmployeeRecords.csv` and `EmployeeRecordsLarge.csv` for you to use.
8. Compile and run the application:
    * Main application: Compile and run App.main(), which is found at `CSV2SQL/src/main/java/com/sparta/jh/CSV2SQL/App.java`.
