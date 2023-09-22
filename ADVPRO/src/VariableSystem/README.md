# VariableSystem


Design a system with following functionalities,
1.	SET a variable
2.	GET a variable
3.	UNSET a variable
4.	COUNT NUMBERS OF VARIABLE with given value
5.	BEGIN — Begins a new transaction
6.	ROLLBACK — Roll back all the commands in the open transaction
7.	COMMIT — Commit the transaction
EXAMPLE 1:



SET a 20
GET a 20
SET b 30
GET b 30
SET a 10
GET a 10
UPDATE c 40 No variable named “c”
SET c 30
COUNT 30 2
COUNT 40 null
UNSET a
GET a null
EXAMPLE 2:
GET a null
SET a 30
GET a 30
EXAMPLE 3:
SET a 30
BEGIN
GET a 30
SET a 40
GET a 40
SET b 40
GET b 40
ROLLBACK
GET b null
GET a 30
EXAMPLE 4:
BEGIN
SET a 40
SET b 40
SET c 50
COUNT 40 2
BEGIN
COUNT 40 null
COMMIT
COUNT 40 2
BEGIN
SET c 10
GET c 10
ROLLBACK
GET c 50
