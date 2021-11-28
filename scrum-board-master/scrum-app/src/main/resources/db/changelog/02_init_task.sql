CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    sprint_num INTEGER,
    workload INTEGER,
    progress INTEGER,
    complete_date DATE,
    user_id INTEGER,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    color VARCHAR(50)
);