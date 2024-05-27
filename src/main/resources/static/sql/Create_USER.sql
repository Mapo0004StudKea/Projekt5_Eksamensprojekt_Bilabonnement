CREATE USER bilabonnementuser@localhost IDENTIFIED BY 'Dat23B';

GRANT SELECT, INSERT, DELETE, UPDATE

    ON bilabonnement.*

    TO bilabonnementuser@localhost;

SHOW GRANTS FOR bilabonnementuser@localhost;