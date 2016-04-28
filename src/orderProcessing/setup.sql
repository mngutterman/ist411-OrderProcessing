CREATE TABLE inventoryItems(
    itemId            SMALLINT AUTO_INCREMENT,
    itemName          VARCHAR(256),
    itemQuantity      SMALLINT,
    itemCost          FLOAT,

    PRIMARY KEY (itemId)
)

CREATE TABLE transactionLog(
    id           SMALLINT AUTO_INCREMENT,
    log          VARCHAR(1000),

    PRIMARY KEY (id)
)

INSERT INTO inventoryItems (itemName, itemQuantity, itemCost)
VALUES ("pen", 2, 0.50), ("paper", 1, 3.0);