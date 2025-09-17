-- USERS (1 -> N ACCOUNTS)
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(80)  NOT NULL UNIQUE,
  full_name  VARCHAR(120) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- BASE CLASS: accounts (common data only)
CREATE TABLE accounts (
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id   BIGINT NOT NULL,
  code      VARCHAR(10) NOT NULL UNIQUE,           -- CPT-12345 (validate in app)
  type      ENUM('COURANT','EPARGNE') NOT NULL,    -- discriminator
  solde     DECIMAL(19,2) NOT NULL DEFAULT 0.00,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_accounts_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- SUBCLASS: current_accounts (CompteCourant)
-- PK == FK to accounts(id)
CREATE TABLE current_accounts (
  id        BIGINT PRIMARY KEY,
  decouvert DECIMAL(19,2) NOT NULL,                -- allowed negative balance
  CONSTRAINT fk_current_accounts_account
    FOREIGN KEY (id) REFERENCES accounts(id) ON DELETE CASCADE
);

-- SUBCLASS: savings_accounts (CompteEpargne)
CREATE TABLE savings_accounts (
  id           BIGINT PRIMARY KEY,
  taux_interet DECIMAL(5,4) NOT NULL,              -- e.g., 0.0150 = 1.5%
  CONSTRAINT fk_savings_accounts_account
    FOREIGN KEY (id) REFERENCES accounts(id) ON DELETE CASCADE
);

-- OPERATIONS (linked to base account)
CREATE TABLE operations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  account_id BIGINT NOT NULL,
  op_uuid CHAR(36) NOT NULL UNIQUE,
  kind ENUM('VERSEMENT','RETRAIT') NOT NULL,
  amount DECIMAL(19,2) NOT NULL,
  meta   VARCHAR(255) NULL,                         -- source/destination
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_operations_account
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE INDEX idx_accounts_user ON accounts(user_id);
CREATE INDEX idx_operations_acc_created ON operations(account_id, created_at);
