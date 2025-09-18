CREATE TABLE users (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username   VARCHAR(80)  NOT NULL UNIQUE,
  password   VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts (
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id   BIGINT NOT NULL,
  code      VARCHAR(20) NOT NULL UNIQUE,
  type      ENUM('COURANT','EPARGNE') NOT NULL,
  solde     DECIMAL(19,2) NOT NULL DEFAULT 0.00,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_accounts_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE current_accounts (
  id        BIGINT PRIMARY KEY,
  decouvert DECIMAL(19,2) NOT NULL,
  CONSTRAINT fk_current_accounts_account
    FOREIGN KEY (id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE savings_accounts (
  id           BIGINT PRIMARY KEY,
  taux_interet DECIMAL(5,4) NOT NULL,
  CONSTRAINT fk_savings_accounts_account
    FOREIGN KEY (id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE operations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  account_id BIGINT NOT NULL,
  kind ENUM('VERSEMENT','RETRAIT') NOT NULL,
  amount DECIMAL(19,2) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_operations_account
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE INDEX idx_accounts_user ON accounts(user_id);
CREATE INDEX idx_operations_acc_created ON operations(account_id, created_at);
