CREATE TABLE charakter_body_part
(id             INTEGER NOT NULL,
 charakter_id   INTEGER NOT NULL,
 body_part_id   INTEGER NOT NULL,
 item_id        INTEGER NOT NULL,
 itemclass      VARCHAR(10),
 PRIMARY KEY(id)
 FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE
 FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE
);

