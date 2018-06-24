SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM EVENT;
DELETE FROM EVENT_STATE;
DELETE FROM MEMBER;
DELETE FROM SUGGESTION;
DELETE FROM TASK_GROUP;
DELETE FROM TASK;
DELETE FROM AUTHORITIES;
DELETE FROM USER_AUTHORITIES;
DELETE FROM USER;
DELETE FROM VOTE;
DELETE FROM VOTE_STATE;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO TASK_GROUP(created_date, name, description,parent_id) VALUES(CURRENT_TIMESTAMP, 'Protocole', '',NULL);
INSERT INTO TASK_GROUP(created_date, name, description,parent_id) VALUES(CURRENT_TIMESTAMP, 'Moyens préliminaires', '',(SELECT id FROM TASK_GROUP WHERE name = 'Protocole'));
INSERT INTO TASK_GROUP(created_date, name, description,parent_id) VALUES(CURRENT_TIMESTAMP, 'Autres procédures', '',(SELECT id FROM TASK_GROUP WHERE name = 'Protocole'));

INSERT INTO TASK(created_date, name, description, task_group_id) VALUES(CURRENT_TIMESTAMP, 'Date de signification', 'Dernière date à laquelle la demande a été signifiée à toutes les parties', (SELECT id FROM TASK_GROUP WHERE name= 'Protocole'));
INSERT INTO TASK(created_date, name, description, task_group_id) VALUES(CURRENT_TIMESTAMP, 'Moyens déclinatoires', '', (SELECT id FROM TASK_GROUP WHERE name= 'Moyens préliminaires'));
INSERT INTO TASK(created_date, name, description, task_group_id) VALUES(CURRENT_TIMESTAMP, 'Moyens D’irrecevabilité', '', (SELECT id FROM TASK_GROUP WHERE name= 'Moyens préliminaires'));
INSERT INTO TASK(created_date, name, description, task_group_id) VALUES(CURRENT_TIMESTAMP, 'Autres Moyens Préliminaires', '', (SELECT id FROM TASK_GROUP WHERE name= 'Moyens préliminaires'));

INSERT INTO MEMBER(created_date, email, first_name, last_name) VALUES(CURRENT_TIMESTAMP, 'walidrift@gmail.com', 'Walid', 'Bezzaoui');
INSERT INTO USER(created_date, password, username, member_id)  VALUES(CURRENT_TIMESTAMP, '$2a$10$qwMriqG.D04PPneIIjC/l.xI/Gh2gPL1/wnJl2ILs/wNtXKNqHh66', 'walidrift@gmail.com', 1);

INSERT INTO EVENT_STATE(created_date, name) VALUES(CURRENT_TIMESTAMP, 'Pending');
INSERT INTO EVENT_STATE(created_date, name) VALUES(CURRENT_TIMESTAMP, 'Accepted');
INSERT INTO EVENT_STATE(created_date, name) VALUES(CURRENT_TIMESTAMP, 'Refused');

