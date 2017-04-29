# Sequence for client id 

CREATE sequence cart_id start with 1 increment by 1; 

# Stores clients information 
CREATE TABLE cart (
userid NUMBER(10),
itemid NUMBER(10),
quantity NUMBER(10), 
PRIMARY KEY(userid, itemid)
);
