INSERT INTO users(username, password, enabled)
    VALUES ('root', 'root', TRUE );
INSERT INTO users(username, password, enabled)
    VALUES ('guest', '12345', TRUE );
INSERT INTO AUTHORITIES(USERNAME, AUTHORITY)
    VALUES ('root', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES(USERNAME, AUTHORITY)
    VALUES ('root', 'ROLE_USER');
INSERT INTO AUTHORITIES(USERNAME, AUTHORITY)
    VALUES ('guest', 'ROLE_USER')
