--Контакты
CREATE TABLE contact (
ID INT NOT NULL AUTO_INCREMENT
, first_name VARCHAR(60) NOT NULL
, last_name VARCHAR(40) NOT NULL
, birth_date DATE
, version INT NOT NULL DEFAULT 0
, UNIQUE UQ_CONTACT_1 (first_name, last_name)
, PRIMARY KEY (id)
);

--Хобби
CREATE TABLE `study`.hobby (
hobby_id VARCHAR(20) NOT NULL
, PRIMARY KEY (hobby_id)
);

--таблица с информацией и деталями телефона
CREATE TABLE `study`.contact_tel_detail(
id INT NOT NULL AUTO_INCREMENT
, contact_id INT NOT NULL
, tel_type VARCHAR(20) NOT NULL
, tel_number VARCHAR(20) NOT NULL
, version INT NOT NULL DEFAULT 0
, UNIQUE uq_contact_tel_detail_1 (contact_id, tel_type)
, PRIMARY KEY (id)
, CONSTRAINT FK_CONTACT_TEL_DETAIL_1 FOREIGN KEY (contact_id)
REFERENCES study.contact (id)
);

--таблица хобби контакта
CREATE TABLE `study`.contact_hobby_detail (
contact_id INT NOT NULL
, hobby_id VARCHAR(20) NOT NULL
, PRIMARY KEY (contact_id, hobby_id)
, CONSTRAINT FK_CONTACT_HOBBY_DETAIL_1 FOREIGN KEY (contact_id)
REFERENCES study.contact (id) ON DELETE CASCADE
, CONSTRAINT FK_CONTACT_HOBBY_DETAIL_2 FOREIGN KEY (hobby_id)
REFERENCES study.hobby (hobby_id)
);







