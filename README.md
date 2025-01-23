
Library Management Application (Java Desktop)
Description
This application provides a user-friendly interface to manage a library. It includes features for managing books, users, loan tracking, and returns. The application is delivered as an executable file (bibliotheque.exe) and uses CSV files to store the data.

Prerequisites
Operating System:
Compatible with all operating systems that support the Java Virtual Machine (JVM).

Required CSV Files (automatically generated if missing):

livres.csv
utilisateurs.csv
emprunts.csv
retours.csv
Installation and Execution
Step 1: Download
Download the zip file containing the application.

Step 2: Prepare the Files
Place the following files in the same folder as the executable (bibliotheque.exe):

livres.csv
utilisateurs.csv
emprunts.csv
retours.csv
Note: If any of these files are missing, the application will automatically create them at the first launch with default headers.

Step 3: Run the Application
Double-click on the bibliotheque.exe file to launch the application.

Step 4: Login
Login using the following credentials:

Username: admin
Password: admin
Main Features
Book Management:

Add, modify, and delete books.
Track book availability (available or borrowed).
User Management:

Add, modify, and delete users.
Loan Management:

Check book availability before borrowing.
Add a loan.
Extend or delete loans.
Return Management:

Record book returns and restore their availability.
Calculate penalties for late returns.
Report Generation:

Generate reports based on library data.
User Guide
Adding a Loan
Open the application and go to the Loans tab.
Enter the following information:
User ID: Select a user from the list.
Book ID: Enter the ID of the book to borrow.
Number of Days: Specify the loan duration.
Click Validate.
If the book is available, the loan will be added, and availability will be marked as No.
If the book is already borrowed, an error message will appear.
Returning a Book
Go to the Returns tab.
Select the corresponding loan.
Validate the return to mark the book as available again (Yes).
Searching and Exporting
Use the search bar in the tabs to quickly find a user, book, or loan.
All changes are automatically saved in the CSV files.
CSV File Structure
livres.csv:

Columns: Id;Title;Author;PublicationYear;Genre;Available.
utilisateurs.csv:

Columns: Id;Name;Email.
emprunts.csv:

Columns: Id;UserId;BookId;LoanDate;ExpectedReturnDate.
retours.csv:

Columns: Id;LoanId;ReturnDate;Penalty.
Troubleshooting
Error: "File not found"

Ensure the CSV files are located in the same folder as the executable.
Double-click does not work

Try running the application as an administrator (right-click > "Run as administrator").
Performance Issues

Ensure your system meets the prerequisites. Close any unnecessary applications.
