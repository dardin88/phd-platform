CREATE DATABASE IF NOT EXISTS phd_platform_db;
USE phd_platform_db;

-- creo la tabella news
create table IF not EXIsts news(
idNews             integer PRIMARY kEY check(idnews>=0),
title              varchar(50) not null,      
description        text not null);
-- popolo la tabella news
INSERT INTO news (idNews,title,description)VALUES
(1,'avviso1','Avviso: corsi di is2 interrotti perchè il gatto si è ferito una gamba'),
(2,'new1','News:il direttore procederà a conferire il premio');

-- creo la tabella degli account
create table IF not EXIsts account(
secondaryEmail      varchar(50) primary key,
email 				varchar(50),
surname				varchar(25) not null,
name				varchar(25) not null,
password			varchar(16),
typeAccount			varchar(20),
isAdministrator		boolean default false);

-- popolo la tabella degli account
INSERT INTO account (secondaryEmail,email,surname,name,password,typeAccount,isAdministrator )VALUES
('test@hotmail.it',null, 'Rossi', 'Mario', null,null,false),
('ballo@hotmail.it','ballo@unisa.it', 'Conti','Carlo', 'test1','phdstudent',false),
('wrestler@hotmail.it','wrestler@unisa.it', 'Cena', 'John', 'test3','professor',false),
('dracula@hotmail.it','dracula@unisa.it', 'Conte', 'Dracula', 'test4','professor',true),
('adelucia@hotmail.it','adelucia@unisa.it', 'DeLucia', 'Andrea', 'test5','professor',true),
('dinucci@hotmail.it','dinucci@unisa.it','Dario','Dinucci','test6','phdstudent',false);

-- creo la tabella  dei professori
create table if not exists professor(
fkAccount			varchar(50) primary key,
link 				varchar(150),
department 			varchar(60) not null,
foreign key(fkAccount) references account(secondaryEmail) on delete cascade on update cascade);

-- popolo la tabella  dei professori
INSERT INTO professor (fkAccount,link,department)VALUES
('wrestler@hotmail.it','https://it.wikipedia.org/wiki/John_Cena','Dipartimento di Mazzate'),
('dracula@hotmail.it','https://it.wikipedia.org/wiki/Conte_Dracula','Dipartimento di Giurisprudenza'),
('adelucia@hotmail.it','http://www.unisa.it/docenti/andreadelucia/index','Dipartimento di Informatica');


create table if not exists curriculum(
name 				varchar(100) primary key,
description			text not null);

INSERT INTO curriculum (name,description)VALUES
('Informatica, Sistemi Informativi e Tecnologie del Software',
 'Il curriculum, Informatica, Sistemi Informativi e Tecnologie del Software, ha l’obiettivo di formare figure professionali dotate di una preparazione scientifica teorica e pratica idonea ad operare con piena professionalità e competenza, sia in ambito accademico che industriale, nelle varie fasi che caratterizzano la ricerca, lo sviluppo, il controllo di qualità e la produzione nel settore dei sistemi informativi e delle tecnologie del software. In particolare, il corso di dottorato di ricerca mira alla formazione di ricercatori con elevata conoscenza degli aspetti teorici, metodologici, sperimentali e applicativi di settore quali quelli dei sistemi informativi e delle basi di dati, dell’ingegneria del software, dell''ingegneria della conoscenza, del web engineering e dell''interazione uomo-macchina, con una elevata capacità di trasferire i risultati della ricerca in ambito industriale e di applicarli
 ai settori dell’economia e del management
 aziendale, del marketing e della comunicazione. 
 Le tematiche scientifiche del curriculum includono:
 Project managementSoftware quality assuranceMetodi per 
 la stima dei costiSistemi a supporto delle decisioni e business intelligenceData warehousingBig dataOpen dataDocument and content managementWorkflow and process managementModellazione e analisi delle prestazioni dei processiBusiness process reengineeringWeb engineeringSistemi cloud-basedIngegneria dei requisiti e progettazione di sistemi softwareManutenzione ed evoluzione di sistemi softwareAnalisi e testing del softwareIngegneria del software empiricaMetodi e strumenti per il lavoro collaborativoLinguaggi visuali e interazione uomo-macchinaComputer graphics e realtà virtualeInterfacce web avanzate, immersive, 3D e apticheDomotica e sistemi di videosorveglianza intelligenti.Interfacce per sistemi domoticiRiconoscimento di immagini e sistemi biometriciComputational intelligenceInformation retrievalTecniche di clustering e data mining, machine learning e classificazioneSistemi informativi geografici e territorialiE-learning e tecnologie per la didattica a distanzaModelli matematici e ottimizzazioneModellazione ed analisi di prestazioni e affidabilità dei sistemiIl Dottore di Ricerca con curriculum in Informatica, Sistemi Informativi e Tecnologie del Software potrà avere diversi sbocchi professionali, che non si fermano a quello di ricercatore accademico o nei centri di ricerca di organizzazioni ed aziende. Infatti, grazie anche alle competenze acquisite con la formazione di tipo manageriale acquisita durante il corso di dottorato, il Dottore di Ricerca potrà ricoprire ruoli di consulente ed esperto di innovazione e trasferimento tecnologico per le aziende del comparto ICT, nonché ruoli di direzione di progetti di ricerca e funzioni direzionali in aziende del comparto ICT.'),
 
 ('Marketing e Comunicazione',
 'Il curriculum “Marketing e Comunicazione” mira 
 a trasferire conoscenze e approcci metodologici 
 relativi a principi, teorie e modelli di ideazione
 e governo delle strategie e delle politiche di marketing 
 e della comunicazione. Il principale obiettivo è formare
 figure professionali dotate di una solida preparazione 
 scientifica, teorica e pratica idonea ad operare nelle università, nei centri di ricerca, negli enti pubblici e nelle imprese private e pubbliche svolgendo attività di ricerca qualificata nelle seguenti aree disciplinari e professionali: marketing strategico e operativo,
 analisi di mercato e del consumo,
 comunicazione istituzionale e d''impresa.
 Il programma formativo si riferisce all’ambito disciplinare
 dell''Economia d''Impresa e prevede che i frequentanti,
 al termine del percorso di apprendimento, acquisiscano un uso 
 agevole delle più avanzate ed affidabili metodologie di ricerca 
 scientifica in campo economico-sociale, oltre ad un’approfondita 
 conoscenza delle teorie e delle più recenti ed innovative impostazioni
 riguardo al marketing ed alla comunicazione.Particolare attenzione di
 studio viene rivolta all’area dei social media e della rete per 
 supportare le scelte strategiche di marketing e di comunicazione
 delle organizzazioni imprenditoriali e delle istituzioni.Le tematiche
 scientifiche del curriculum includono:Marketing Strategy;Competition
 dynamics;Channel management;Salesforce management;Marketing e
 performance aziendali;Behavioral Constructs;Decision making;Consumer
 judgment;Corporate Communication;Corporate identity;Organizational 
 culture;Reputation management;Integrated Marketing Communication
 ;Digital communication;Value creation and innovation;On-line 
 reputation;Web customer experience.In definitiva, il Dottore di
 Ricerca con curriculum in Marketing e Comunicazione si potrà 
 orientare professionalmente verso le seguenti figure: studioso 
 ed esperto di marketing e comunicazione per lo sviluppo della 
 ricerca scientifica, sia nel mondo accademico che più in generale 
 nelle organizzazioni di ricerca; consulente professionale per le 
 imprese pubbliche, private e per le istituzioni nel supportare i 
 processi di marketing e di comunicazione; ricoprire funzioni direzionali all’interno delle organizzazioni imprenditoriali e delle istituzioni nel ruolo di sviluppo delle strategie di marketing e della comunicazione.');


create table if not exists cycle(
number				integer primary key,
description			text not null,
year				varchar(4) not null,
fkProfessor			varchar(50),
foreign key (fkProfessor) references professor(fkAccount) on delete set null on update cascade);

INSERT INTO cycle (number,description,year, fkProfessor)VALUES
(15, 'Il corso di Dottorato di Ricerca in Management 
 & Information Technology ha come obiettivo la formazione
 di specialisti della ricerca in ambito economico-aziendale
 ed informatico. Il corso è strutturato in tre curricula, 
 denominati (i) Economia e Direzione delle Aziende Pubbliche,
 (i) Marketing e Comunicazione e (iii)
 Informatica, Sistemi Informativi e Tecnologie del Software. Attraverso i tre curricula, il corso di Dottorato intende formare figure professionali diverse, ma che riescano ad interagire per la soluzione di problemi complessi in ambito economico-aziendale, grazie all''utilizzo di tecnologie dell''informazione innovative e ad un approccio inter-disciplinare che favorisca la reciproca condivisione di idee e competenze. Il primo curriculum mira a formare specialisti nel settore del management di enti, istituzioni ed aziende afferenti al settore pubblico, con conoscenze relative a principi, teorie e modelli di gestione dei processi di innovazione nella Pubblica Amministrazione. Il secondo curriculum mira a formare specialisti in grado di utilizzare le più avanzate, innovative ed affidabili metodologie di ricerca scientifica in campo economico-sociale, con particolare riferimento al marketing ed alla comunicazione. Infine, il terzo curriculum mira a formare specialisti nel settore dell''Informatica, con conoscenza degli aspetti teorici, metodologici e sperimentali dei sistemi informativi, dell''ingegneria del software, dei dati e della conoscenza, dell''elaborazione di immagini e dell''interazione uomo-macchina, con applicazioni in particolare alla economia e alla gestione aziendale.Il completamento del Corso di Dottorato ed il superamento dell''esame finale consente per tutti e tre i curricula lo svolgimento di attività di ricerca in ambito accademico, nei settori dell''economia aziendale e dell''informatica, costituendo un titolo legalmente riconosciuto nei concorsi universitari, nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l''inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.',
 '2015', 'adelucia@hotmail.it'),
 
(16, 'Il corso di Dottorato di Ricerca in Management &
 Information Technology ha come obiettivo la formazione
 di specialisti della ricerca in ambito economico-aziendale
 ed informatico. Il corso è strutturato in tre curricula,
 denominati (i) Economia e Direzione delle Aziende Pubbliche, 
 (i) Marketing e Comunicazione e (iii) Informatica, Sistemi 
 Informativi e Tecnologie del Software. Attraverso i tre
 curricula, il corso di Dottorato intende formare figure
 professionali diverse, ma che riescano ad interagire per la 
 soluzione di problemi complessi in ambito economico-aziendale,
 grazie all''utilizzo di tecnologie dell''informazione 
 innovative e ad un approccio inter-disciplinare che favorisca 
 la reciproca condivisione di idee e competenze. 
 Il primo curriculum mira a formare specialisti nel settore 
 del management di enti, istituzioni ed aziende afferenti al
 settore pubblico, con conoscenze relative a principi, teorie
 e modelli di gestione dei processi di innovazione nella
 Pubblica Amministrazione. Il secondo curriculum mira a formare
 specialisti in grado di utilizzare le più avanzate, innovative
 ed affidabili metodologie di ricerca scientifica in campo 
 economico-sociale, con particolare riferimento al marketing ed
 alla comunicazione. Infine, il terzo curriculum mira a formare
 specialisti nel settore dell''Informatica, con conoscenza 
 degli aspetti teorici, metodologici e sperimentali dei sistemi 
 informativi, dell''ingegneria del software, dei dati e della 
 conoscenza, dell''elaborazione di immagini e dell''interazione
 uomo-macchina, con applicazioni in particolare alla economia e 
 alla gestione aziendale.\n\nIl completamento del Corso di 
 Dottorato ed il superamento dell''esame finale consente per
 tutti e tre i curricula lo svolgimento di attività di ricerca
 in ambito accademico, nei settori dell''economia aziendale e
 dell''informatica, costituendo un
 titolo legalmente riconosciuto nei concorsi universitari, 
 nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l''inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.'
 , '2015', 'dracula@hotmail.it');

 
create table if not exists curriculumcic(
fkCurriculum 		varchar(100),
fkCycle				integer,
fkProfessor			varchar(50),
primary key (fkCurriculum,fkCycle),
foreign key (fkCurriculum) references curriculum(name) on delete cascade on update cascade,
foreign key (fkCycle) references cycle(number) on delete cascade on update cascade,
foreign key (fkProfessor) references professor(fkAccount) on delete set null on update cascade);


INSERT INTO curriculumcic (fkCurriculum,fkCycle,fkProfessor)VALUES
('Informatica, Sistemi Informativi e Tecnologie del Software',15,'adelucia@hotmail.it');


create table if not exists teach(
fkCurriculum                    varchar(100),
fkCycle				integer,
fkProfessor			varchar(50),
primary key (fkCurriculum,fkCycle,fkProfessor),
foreign key (fkCurriculum) references curriculumcic(fkCurriculum) on delete cascade on update cascade,
foreign key (fkCycle) references curriculumcic(fkCycle) on delete cascade on update cascade,
foreign key (fkProfessor) references professor(fkAccount) on delete no action on update cascade);

INSERT INTO teach (fkCurriculum,fkCycle,fkProfessor	)VALUES
('Informatica, Sistemi Informativi e Tecnologie del Software',15,'adelucia@hotmail.it');


create table if not exists course(
idCourse				integer	primary key,
fkCurriculum 			varchar(100) not null,
fkCycle					integer not null,
name					varchar(50) not null,
description                             varchar(250),
startDate                               date not null,
endDate                                 date not null,
unique (fkCurriculum,fkCycle,name),
foreign key (fkCurriculum) references curriculumcic(fkCurriculum) on delete cascade on update cascade,
foreign key (fkCycle) references curriculumcic(fkCycle) on delete cascade on update cascade);


INSERT INTO course (idCourse,fkCurriculum,fkCycle,name,description,startDate,endDate)VALUES
(1,'Informatica, Sistemi Informativi e Tecnologie del Software',15,'IS2','descrizione del corso','2015-01-01','2015-09-10');

-- creiamo la tabella studente
create table IF not EXIsts phdstudent(
fkAccount 				varchar(50) primary key,
telephone				varchar(15),
link 					varchar(150),
department				varchar(50) not null,
researchInterest		text,
fkCycle 				integer,
fkCurriculum 			varchar(100),
fkProfessor				varchar(50),
foreign key (fkAccount) references account(secondaryEmail) on delete cascade on update cascade,
foreign key (fkProfessor) references professor(fkAccount) on delete set null on update cascade,
foreign key (fkCurriculum) references curriculumcic(fkCurriculum) on delete set null on update cascade,
foreign key (fkCycle) references curriculumcic(fkCycle) on delete set null on update cascade);

-- inseriamo il phdstudent
INSERT INTO phdstudent(fkAccount,telephone,link,department,researchInterest,fkCycle,fkCurriculum,fkProfessor)VALUES
('ballo@hotmail.it','3881144567',null,'Informatica',null,15,'Informatica, Sistemi Informativi e Tecnologie del Software','adelucia@hotmail.it'),

('dinucci@hotmail.it','3881144567','http://www.sesa.unisa.it/people/ddinucci/','Informatica','He is
 an open-source enthusiast who likes to work on challenging project. In 2014 he completed the 10th edition 
 of Google Summer of Code working on Gnome Maps and GraphHopper.',15,'Informatica, Sistemi Informativi e Tecnologie del Software',null);


create table IF not EXIsts publication(
idPublication 		integer	primary key,
title 				varchar(50) not null,
publicationIssue                 varchar(60) not null,
year				varchar(4) not null,
numberPage    		integer not null check(numberpage>0),
link				varchar(150),
type				varchar(20),
otherAuthors 		varchar(255),
abstract			text not null,
fkPhdstudent 		varchar(50) not null,
foreign key (fkPhdstudent) references phdstudent(fkAccount) on delete cascade on update cascade);


INSERT INTO publication(idPublication,title,publicationIssue,year,numberPage,link,type,otherAuthors,abstract,fkPhdstudent )VALUES
(1,'ICSE 2015 Trip Summary','ICSE 2015 SIGSOFT CAPS Report','2015',1,'http://www.sesa.unisa.it/people/ddinucci/pdf/3_trip-report_SEN.pdf',
 'Ricerca',null,
 'Abstract ICSE 2015 has been my first conference and in my opinion it has
been amazing! My agenda in the period 16th
- 22th of May has been
full of very interesting talks and keynotes.
For my PhD studies, I am working on Search Based Software Testing
and Mining Software Repositories. Thus, the formula MSR +
SBST + ICSE was perfect for quickly approaching problems and
meet top researchers in these fields.
In this paper I am going to summarize my experience in brief','dinucci@hotmail.it');


create table IF not EXIsts mission(
idMission	 		integer	primary key,
description 		text not null,
startDate			date not null,
endDate				date not null,
reference			varchar(255),
place				varchar(70) not null,
fkPhdstudent 		varchar(50) not null,
foreign key (fkPhdstudent) references phdstudent(fkAccount) on delete cascade on update cascade);

INSERT INTO mission (idMission,description,startDate,endDate,reference,place,fkPhdstudent)VALUES
(1,'la ricerca dei numeri primi','2016-05-25','2017-06-25',null,'Roma','dinucci@hotmail.it');


create table IF not EXIsts collaboration(
idCollaboration	 	integer	primary key,
description 		text not null,
startDate			date not null,
endDate				date not null,
istitution 			varchar(70),
fkPhdstudent 		varchar(50) not null,
foreign key (fkPhdstudent) references phdstudent(fkAccount) on delete cascade on update cascade);

INSERT INTO collaboration (idCollaboration,description,startDate,endDate,istitution,fkPhdstudent)VALUES
(1,'Studio dei bit al contrario','2016-05-25','2017-06-25','Dipartimento di Informatica','dinucci@hotmail.it')       ;


create table IF not EXIsts seminar(
idSeminar	 		integer	primary key,
date				date not null,
startTime			char(5) not null,
endTime				char(5) not null,
name				varchar(70) not null,
nameSpeacker 		varchar(50) not null,
desription 			text not null,
place				varchar(50) not null,
fkCourse			integer not null,
foreign key (fkCourse) references course(idCourse) on delete cascade on update cascade);

INSERT INTO seminar (idSeminar,date,startTime,endTime,name,nameSpeacker,desription,place,fkCourse)VALUES
(1,'2016-05-28','10:15','15:10','Come prepararsi ad un colloquio','Gerardo Gallesi','Consigli su 
come prepararsi ad un colloqui nel migliore dei modi',
'Aula P16',1 );


create table IF not EXIsts lesson(
idLesson	 		integer	primary key,
date				date not null,
startTime			char(5) not null,
endTime				char(5) not null,
name				varchar(70) not null,
classroom			varchar(30) not null,
desription 			text not null,
fkCourse			integer not null,
foreign key (fkCourse) references course(idCourse) on delete cascade on update cascade);

INSERT INTO lesson (idLesson,date,startTime,endTime	,name,classroom,desription,fkCourse)VALUES
(1,'2016-05-2','9:10','12:10','ISTS','P12','introduzione al corso',1);


create table IF not EXIsts keep(
fkProfessor			varchar(50) not null,
fkLesson	 		integer	not null,
primary key (fkProfessor, fkLesson),
foreign key (fkProfessor) references professor(fkAccount) on delete no action on update cascade,
foreign key (fkLesson) references lesson(idLesson) on delete cascade on update cascade);

INSERT INTO keep (fkProfessor,fkLesson)VALUES
('adelucia@hotmail.it',1) ;


create table IF not EXIsts presence(
fkPhdstudent			varchar(50) not null,
fkLesson	 			integer	not null,
isPresent				boolean default false ,
primary key(fkPhdstudent, fkLesson),
foreign key (fkPhdstudent) references phdstudent(fkAccount) on delete no action on update cascade,
foreign key (fkLesson) references lesson(idLesson) on delete cascade on update cascade);

INSERT INTO presence (fkPhdstudent,fkLesson,isPresent)VALUES
('dinucci@hotmail.it',1,false);