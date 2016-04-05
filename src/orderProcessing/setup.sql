CREATE TABLE inventoryItems(
    itemId            SMALLINT AUTO_INCREMENT,
    itemName          VARCHAR(256),
    itemQuantity      SMALLINT,
    itemCost          FLOAT,

    PRIMARY KEY (itemId)
)

INSERT INTO inventoryItems (itemName, itemQuantity, itemCost)
VALUES ("pen", 2, 0.50), ("paper", 1, 3.0);