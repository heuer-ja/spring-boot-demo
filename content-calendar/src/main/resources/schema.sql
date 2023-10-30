CREATE TABLE Content(
    id INTEGER AUTO_INCREMENT,
    title VARCHAR(255),
    desc TEXT,
    status VARCHAR(20),
    content_type VARCHAR(50),
    date_created DATETIME,
    date_updated DATETIME,
    url VARCHAR(255),
    primary key (id)
);

INSERT INTO Content(title, desc, status, content_type, date_created)
VALUES ( 'Automated Title', 'huhu', 'IN_PROGRESS', 'ARTICLE', CURRENT_TIMESTAMP())
