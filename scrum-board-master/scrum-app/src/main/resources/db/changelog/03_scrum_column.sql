ALTER TABLE task
ADD COLUMN scrum_id INTEGER  REFERENCES scrum(id);
/**
ALTER TABLE  scrum_userid
ADD COLUMN scrum_id INTEGER  REFERENCES scrum(id);**/