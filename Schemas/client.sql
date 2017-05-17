# Sequence for client id 

CREATE sequence seq_clientId start with 1 increment by 1; 

# Stores clients information 
CREATE TABLE client ( 
id NUMBER(10), 
email VARCHAR(255), 
password VARCHAR(255), 
address VARCHAR(255), 
phone_number VARCHAR(20),
discount NUMBER(10),
PRIMARY KEY(email) 
);
