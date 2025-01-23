# Library Management Application (Java Desktop)

## Overview
This application provides a user-friendly interface for managing a library system. It includes functionalities for managing books, users, loans, and returns. The application is delivered as an executable file (`bibliotheque.exe`) and uses CSV files to store the data persistently.

---

## Prerequisites

### Operating System:
The application is compatible with all operating systems that support the Java Virtual Machine (JVM).

### Required CSV Files:
The following CSV files are required for the application to function properly. If any of these files are missing, they will be automatically generated at the first launch with default headers:
- `livres.csv` - Book details
- `utilisateurs.csv` - User information
- `emprunts.csv` - Loan records
- `retours.csv` - Return records

---

## Installation and Execution

### Step 1: Download
Download the zip file containing the application from the provided link.

### Step 2: Prepare the Files
After extracting the zip file, place the following files in the same directory as the executable (`bibliotheque.exe`):
- `livres.csv`
- `utilisateurs.csv`
- `emprunts.csv`
- `retours.csv`

> **Note:** If any of these files are missing, the application will create them automatically at the first launch with default headers.

### Step 3: Run the Application
Double-click on the `bibliotheque.exe` file to launch the application.

### Step 4: Login
Login using the default administrator credentials:
- **Username:** admin
- **Password:** admin

---

## Main Features

### 1. Book Management:
- Add, modify, and delete books in the library.
- Track the availability of books (whether they are available or borrowed).

### 2. User Management:
- Add, modify, and delete users in the system.

### 3. Loan Management:
- Check the availability of books before borrowing them.
- Add a new loan.
- Extend or delete existing loans.

### 4. Return Management:
- Record the return of books and restore their availability.
- Calculate and apply penalties for late returns.

### 5. Report Generation:
- Generate reports based on the library data (such as loan history, overdue books, etc.).

---

## User Guide

### Adding a Loan
1. Open the application and go to the **Loans** tab.
2. Enter the following information:
   - **User ID**: Select a user from the list.
   - **Book ID**: Enter the ID of the book to borrow.
   - **Number of Days**: Specify the loan duration.
3. Click **Validate**.
   - If the book is available, the loan will be added and its availability will be updated to "No".
   - If the book is already borrowed, an error message will be displayed.

### Returning a Book
1. Navigate to the **Returns** tab.
2. Select the corresponding loan record.
3. Validate the return, marking the book as available again.

### Searching and Exporting Data
- Use the search bar in any tab to quickly find a user, book, or loan.
- All modifications to records are automatically saved in the relevant CSV files.

---

## CSV File Structure

1. **livres.csv**:
   - Columns: `Id;Title;Author;PublicationYear;Genre;Available`

2. **utilisateurs.csv**:
   - Columns: `Id;Name;Email`

3. **emprunts.csv**:
   - Columns: `Id;UserId;BookId;LoanDate;ExpectedReturnDate`

4. **retours.csv**:
   - Columns: `Id;LoanId;ReturnDate;Penalty`

---

## Troubleshooting

### Error: "File Not Found"
- Ensure that all required CSV files are located in the same folder as the executable.

### Issue: Double-click does not launch the application
- Try running the application as an administrator (right-click the executable and select "Run as administrator").

### Performance Issues
- Verify that your system meets the necessary prerequisites.
- Close any unnecessary applications to free up system resources.
