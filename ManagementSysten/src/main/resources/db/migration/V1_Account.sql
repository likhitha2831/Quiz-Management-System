create table if not exists accounts
(
accountNumber text primary key,
amount bigint,
userId bigint NOT NULL,
branchName text,
CONSTRAINT user_fk FOREIGN KEY (userId) REFERENCES users (id)
)