INSERT INTO gym (gym_name, created_by, created_on)
VALUES
('Power Gym', 0, NOW()),
('Iron Factory', 0, NOW()),
('Ladies Fitness Club', 0, NOW());

INSERT INTO user (username, password, type, gym_id, created_by, created_on)
VALUES
('development', '$2a$10$79KatEYcHJCtQm90XOpwseKHArC21YFO6hpLN01H0ytthZXMYy4Hu', 'ADMIN', 1, 0, NOW()),
('admin_pg', '$2a$10$79KatEYcHJCtQm90XOpwseKHArC21YFO6hpLN01H0ytthZXMYy4Hu', 'ADMIN', 1, 0, NOW()),
('trainer_pg', '$2a$10$79KatEYcHJCtQm90XOpwseKHArC21YFO6hpLN01H0ytthZXMYy4Hu', 'TRAINER', 1, 0, NOW()),
('admin_if', '$2a$10$79KatEYcHJCtQm90XOpwseKHArC21YFO6hpLN01H0ytthZXMYy4Hu', 'ADMIN', 1, 0, NOW()),
('staff_lfc', '$2a$10$79KatEYcHJCtQm90XOpwseKHArC21YFO6hpLN01H0ytthZXMYy4Hu', 'STAFF', 1, 0, NOW());

INSERT INTO member (name, age, gender, phone_number, gym_id, created_by, created_on)
VALUES
('Ahmed Hassan', 24, 'M', '01011111111', 1, 0, NOW()),
('Mohamed Salah', 29, 'M', '01022222222', 1, 0, NOW()),
('Youssef Adel', 34, 'M', '01033333333', 1, 0, NOW()),
('Sara Mohamed', 22, 'F', '01044444444', 1, 0, NOW()),
('Nour Ali', 27, 'F', '01055555555', 1, 0, NOW()),
('Omar Fathy', 31, 'M', '01066666666', 1,0, NOW()),
('Khaled Mostafa', 40, 'M', '01077777777', 1, 0, NOW());

INSERT INTO member (name, age, gender, phone_number, gym_id, created_by, created_on)
VALUES
('Ahmed Hassan', 24, 'M', '01011111111', 1, 1, NOW()),
('Mohamed Salah', 29, 'M', '01022222222', 1, 1, NOW()),
('Youssef Adel', 34, 'M', '01033333333', 1, 1, NOW()),
('Sara Mohamed', 22, 'F', '01044444444', 1, 1, NOW()),
('Nour Ali', 27, 'F', '01055555555', 1, 1, NOW()),
('Omar Fathy', 31, 'M', '01066666666', 1, 1, NOW()),
('Khaled Mostafa', 40, 'M', '01077777777', 1, 1, NOW());


INSERT INTO members_subscription (
  member_id, date_from, date_to, paid_amount,
  receipt_number, num_of_sessions,
  created_by, created_on
) VALUES
(1, DATE_SUB(CURDATE(), INTERVAL 60 DAY), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 600, 'RCPT-2001', 30, 1, NOW()),
(2, DATE_SUB(CURDATE(), INTERVAL 40 DAY), DATE_SUB(CURDATE(), INTERVAL 10 DAY), 500, 'RCPT-2002', 20, 1, NOW()),
(3, DATE_SUB(CURDATE(), INTERVAL 60 DAY), DATE_ADD(CURDATE(), INTERVAL 90 DAY), 1500, 'RCPT-2003', 90, 1, NOW()),
(4, DATE_SUB(CURDATE(), INTERVAL 60 DAY), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 700, 'RCPT-3001', 30, 1, NOW()),
(5, DATE_SUB(CURDATE(), INTERVAL 60 DAY), DATE_ADD(CURDATE(), INTERVAL 15 DAY), 650, 'RCPT-3002', 30, 1, NOW()),
(6, DATE_SUB(CURDATE(), INTERVAL 60 DAY), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 550, 'RCPT-4001', 25, 1, NOW());


INSERT INTO member_attendance (
  member_id,
  gym_id,
  attend_time,
  attend_type,
  created_by,
  created_on
) VALUES
-- Power Gym
(1, 1, NOW(), 'I', 1, NOW()),
(1, 1, DATE_SUB(NOW(), INTERVAL 1 DAY), 'I', 1, NOW()),
(2, 1, DATE_SUB(NOW(), INTERVAL 2 DAY), 'I', 1, NOW()),
(3, 1, DATE_SUB(NOW(), INTERVAL 3 DAY), 'I', 1, NOW()),
(6, 1, NOW(), 'I', 3, NOW()),
(7, 1, DATE_SUB(NOW(), INTERVAL 1 DAY), 'I', 1, NOW()),
(4, 1, NOW(), 'I', 4, NOW()),
(5, 1, DATE_SUB(NOW(), INTERVAL 1 DAY), 'I', 1, NOW());
