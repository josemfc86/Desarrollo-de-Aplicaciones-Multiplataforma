CREATE TABLE IF NOT EXISTS matriculas
(
    DNI          varchar(9)  NOT NULL,
    NombreModulo varchar(60) NOT NULL,
    Curso        varchar(5)  NOT NULL,
    Nota         double      NOT NULL,
    primary key (DNI),
	constraint FormatoCurso check (Curso rlike '[0-9][0-9]-[0-9][0-9]')
)
    DEFAULT CHARSET = latin1;


insert into matriculas(DNI, NombreModulo, Curso, Nota)
values ('12345678A', 'DAM', '22-23', 5),
       ('87456321B', 'DAM', '22-23', 8),
       ('97864563C', 'DAW', '22-23', 7),
       ('56489731D', 'DAW', '22-23', 10);

