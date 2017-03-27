# Sequence for client id 

CREATE sequence cart_id start with 1 increment by 1; 

# Stores clients information 
CREATE TABLE client ( 
id NUMBER(10),
userid NUMBER(10),
item VARCHAR(255), 
PRIMARY KEY(id) 
);
