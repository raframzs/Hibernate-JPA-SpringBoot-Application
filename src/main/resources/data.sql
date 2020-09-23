INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES 
(10001, 'JPA in 100 Steps', sysdate(), sysdate(), false),
(10002, 'PHP full stack', sysdate(), sysdate(), false),
(10003, 'Angular developer', sysdate(), sysdate(), false),
(10004, 'SpringBoot master', sysdate(), sysdate(), false),
(10005, 'Machine Learning advanced', sysdate(), sysdate(), false),
(10006, 'Neural Networks', sysdate(), sysdate(), false),
(10007, 'Administration of TICs', sysdate(), sysdate(), false),
(10008, 'Laravel Developer', sysdate(), sysdate(), false),
(10009, 'Deep Learning 232 steps', sysdate(), sysdate(), false),
(10010, 'Computational Architecture', sysdate(), sysdate(), false),
(10011, 'How to love in quarentine times', sysdate(), sysdate(), false),
(10012, 'Java OCA', sysdate(), sysdate(), false);

INSERT INTO passport(id, number) VALUES
(214188118,'RAFR<<<<<<<<<<<<<PSS1412800'),
(214188122,'JULJ<<<<<<<<<<<<<PER1418810'),
(214188124,'JONT<<<<<<<<<<<<<PZZ1412820'),
(214188126,'SING<<<<<<<<<<<<<PAL1418830'),
(214188128,'MARG<<<<<<<<<<<<<PSW1418840'),
(214188130,'EDWIC<<<<<<<<<<<<<PXY1412850');

INSERT INTO student(id, name, passport_id) VALUES
(66201082,'RAFAEL RAMIREZ',214188118),
(66201013,'JULIAN JIMENZ',214188122),
(66201014,'JONATHAN TORRES',214188124),
(66201015,'SINDY GARCÍA',214188126),
(66201016,'MARIA GUADALUPE',214188128),
(66201017,'EDWIN CONTRERAS',214188130);

INSERT INTO review(id, rating, description, course_id) VALUES
(20100,'10','Great Course', 10001),
/*(20200,'8','Amazing course', 10002),*/
(20300,'6','Good, could be better', 10003),
(20400,'9','Pertfecly repository', 10004),
(20500,'7','Good Experience', 10005),
(20600,'5','Somethings are sad', 10006);

INSERT INTO student_course(student_id, course_id) VALUES
(66201082,10001),
(66201013,10001),
(66201014,10001),
(66201082,10003),
(66201016,10006),
(66201082 ,10006);

INSERT INTO full_time_employee (id, name, salary) VALUES
(1001, 'Brad Pitt', 5200000),
(1002, 'Robert Downey', 3200100),
(1003, 'Nicolas Cage', 1348000);

INSERT INTO part_time_employee (id, name, hourly_wage) VALUES
(2001, 'Leonardo DiCaprio', 120),
(2002, 'Salma Hayek', 230),
(2003, 'Joaquin Phoenix', 400);

/*SELECT course.ID AS COURSE_ID, course.name, review.description, review.rating
FROM course
INNER JOIN review ON course.ID = review.course_id

SELECT student.id, student.name, course.name
FROM student
LEFT OUTER JOIN student_course
ON student.id = student_course.student_id
LEFT OUTER JOIN course
ON student_course.course_id = course.id*/