CREATE TABLE Expenses(
                         id INTEGER PRIMARY KEY,
                         expense_value DOUBLE,
                         expense_date DATE,
                         expense_category VARCHAR (30)
);
CREATE TABLE Categories_of_expense(
                         category_name VARCHAR(30) PRIMARY KEY
);
ALTER TABLE Expenses ADD FOREIGN KEY (expense_category) REFERENCES  Categories_of_expense(category_name);
