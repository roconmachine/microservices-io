INSERT INTO public.domains(id, app_name, created_date, last_modified, class_name)
VALUES (100, 'PAI', NOW(), null, 'class.pai.report');


-- actions
INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (101, 'PAI', Now(), null, 'apply on ..', 'COMPLETE REPORT');
INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (102, 'PAI', Now(), null, 'apply on ..', 'CLOSE');

INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (103, 'PAI', Now(), null, 'apply on ..', 'ACCEPT');
INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (104, 'PAI', Now(), null, 'apply on ..', 'PRIMARY INVESTIGATION COMPLETE');

INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (105, 'PAI', Now(), null, 'apply on ..', 'RESET');
INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (106, 'PAI', Now(), null, 'apply on ..', 'RESTORE');

INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (107, 'PAI', Now(), null, 'apply on ..', 'NOTIFY');
INSERT INTO public.actions(
	id, app_name, created_date, last_modified, description, name)
	VALUES (108, 'PAI', Now(), null, 'apply on ..', 'RESOLVE');

--state

INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (201, 'PAI', Now(), null, '?', 'class.pai.report', false, 'INCOMPLETE', 1, 'start');
INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (202, 'PAI', Now(), null, '?', 'class.pai.report', false, 'SUBMITTED', 1, '');
INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (203, 'PAI', Now(), null, '?', 'class.pai.report', false, 'INVESTIGATION COMPLETED', 1, '');
INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (204, 'PAI', Now(), null, '?', 'class.pai.report', false, 'ONBOARD', 1, '');

INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (205, 'PAI', Now(), null, '?', 'class.pai.report', false, 'RESOLVED', 1, '');
INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (206, 'PAI', Now(), null, '?', 'class.pai.report', false, 'CLOSED', 1, '');
INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (207, 'PAI', Now(), null, '?', 'class.pai.report', false, 'INACTIVE', 1, '');
INSERT INTO public.states(
	id, app_name, created_date, last_modified, description, domain, is_leaf, name, rank, reference)
	VALUES (208, 'PAI', Now(), null, '?', 'class.pai.report', false, 'REJECTED', 1, '');


--lifecycle

INSERT INTO public.lifecycles(
	id, app_name, created_date, last_modified, action_id, from_state, to_state)
	VALUES (500, 'PAI', NOW(), null, 101, 201, 202);
INSERT INTO public.lifecycles(
	id, app_name, created_date, last_modified, action_id, from_state, to_state)
	VALUES (501, 'PAI', NOW(), null, 102, 201, 206);


INSERT INTO public.lifecycles(
	id, app_name, created_date, last_modified, action_id, from_state, to_state)
	VALUES (502, 'PAI', NOW(), null, 101, 202, 206);

INSERT INTO public.lifecycles(
	id, app_name, created_date, last_modified, action_id, from_state, to_state)
	VALUES (503, 'PAI', NOW(), null, 103, 202, 204);

INSERT INTO public.lifecycles(
	id, app_name, created_date, last_modified, action_id, from_state, to_state)
	VALUES (504, 'PAI', NOW(), null, 108, 204, 205);