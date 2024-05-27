# QueryEngine

 Create an engine that can process the user query. The main focus is not the logic but System Design.
1.	How the created query engine scales out perfectly even upon adding new features later?
2.	How do we create a system that can handle the following inputs and process the input query?
Question:
1.	Given a table containing a set of 10 employees with respective fields:
ID	Name	Age	Designation	Department	Reporting To
2.	Show all employee data
3.	Process the query:
1.	Get input from the user until presses exit.
2.	Get field value to compare, comparison operator as input
1.	If the field value is age (int data type), supported comparators: >, <, !=, ==
2.	If the field value is of string data type, supported comparators: ‘startswith’, ‘contains’, ‘endswith’, ‘notcontains’, ‘equals’ and ‘notequals’.
3.	Use ‘AND’ in default for queries with multiple checks.
4.	Eg: age > 30 and age < 50 and department contains finance and reporting to A
4.	Show the reporting to hierarchy for the given employee name: J -> I -> F -> D -> C -> B-> A
5.	Show the employees reporting to the given manager.
6.	Show summary of Department, Designation, ReportingTo.


DB Query

CREATE TABLE employees (
    -> ID INT PRIMARY KEY,
    -> Name VARCHAR(255) NOT NULL,
    -> Age INT NOT NULL,
    -> Designation VARCHAR(255) NOT NULL,
    -> Department VARCHAR(255) NOT NULL,
    -> ReportingTo INT,
    -> FOREIGN KEY (ReportingTo) REFERENCES employees (ID)
    -> );
    
    INSERT INTO employees (ID, Name, Age, Designation, Department, ReportingTo)
    -> VALUES 
    -> (1, 'John Doe', 35, 'Manager', 'Finance', NULL),
    -> (2, 'Jane Smith', 32, 'Assistant Manager', 'Marketing', 1),
    -> (3, 'Tommy Lee', 28, 'Marketing Executive', 'Marketing', 2),
    -> (4, 'Emily Davis', 26, 'Finance Executive', 'Finance', 1),
    -> (5, 'Michael Brown', 30, 'IT Manager', 'IT', 1);
