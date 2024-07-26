/* Clear WALLETS */
DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

/* Insert wallets */
INSERT INTO
    WALLETS (
    ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE, "VERSION"
)
VALUES (
           1, 'Gabriel - User', 12345678900, 'gabriel@test.com', '123456', 'COMUM', 1000.00, 1
       );

INSERT INTO
    WALLETS (
    ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE, "VERSION"
)
VALUES (
           2, 'Hellen - Lojista', 12345678901, 'hellen@test.com', '123456', 'LOJISTA', 1000.00, 1
       );