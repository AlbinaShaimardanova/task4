CREATE TABLE logins (
	id int4 NOT NULL,
	access_date timestamp NULL,
	user_id int4 NULL,
	application text NULL,
	CONSTRAINT "Logins_pkey" PRIMARY KEY (id),
	CONSTRAINT logins_fk FOREIGN KEY (user_id) REFERENCES public.users(id)
);