CREATE DATABASE IF NOT EXISTS Phd_platform_db;
USE Phd_platform_db;
-- creo la tabbela news
create table IF not EXIsts news(
idnews     integer PRIMARY kEY check(idnews>=0),
description       text not null);

INSERT INTO news (idnews,description)VALUES
(1,'Avviso: corsi di is2 interrotti perchè il gatto si è ferito una gamba'),
(2,'News:il direttore procederà a conferire il premio');
-- struttra della tabella acount

create table IF not EXIsts account(
secondaryemail      varchar(50) primary key,
email 				varchar(50),
surname				varchar(25) not null,
name				varchar(25) not null,
password			varchar(16));

-- inseriamo account 

INSERT INTO account (secondaryemail,email,surname,name,password )VALUES
('test1@gmail.com','test@unisa.it', 'Rossi', 'Mario', 'test'),
('ballo1@hotmail.it','ballo@unisa.it', 'Conti','Carlo', 'test1'),
('elyx241@hotmail.it','elyx24@unisa.it', 'Magalli', 'GianCarlo', 'test2'),
('wrestler1@hotmail.it','wrestler@unisa.it', 'Cena', 'John', 'test3'),
('dracula1@hotmail.it','dracula@unisa.it', 'Conte', 'Dracula', 'test4'),
('adelucia1@hotmail.it','adelucia@unisa.it', 'DeLucia', 'Andrea', 'test5'),
('dinucci1@gmail.com','dinucci@unisa.it','Dario','Dinucci','test6');
-- creiamo la tabella professori
create table if not exists professor(
email 				varchar(50) primary key,
secondaryemail		varchar(50) not null unique,
surname				varchar(25) not null,
name				varchar(25) not null,
password			varchar(16) not null,
link 				varchar(150),
department 			varchar(60) not null,
isadministrator		boolean default false);


-- inseriamo i professori 
INSERT INTO professor (email, secondaryemail,surname,name,password,link,department,isadministrator )VALUES
('wrestler@unisa.it','wrestler1@hotmail.it', 'Cena', 'John', 'test3',
'https://it.wikipedia.org/wiki/John_Cena','Dipartimento di Mazzate',false),

('dracula@unisa.it','dracula1@hotmail.it', 'Conte', 'Dracula', 'test4',
'https://it.wikipedia.org/wiki/Conte_Dracula','Dipartimento di Giurisprudenza',false),

('adelucia@unisa.it','adelucia1@hotmail.it', 'DeLucia', 
'Andrea', 'test5',
'http://www.unisa.it/docenti/andreadelucia/index',
'Dipartimento di Informatica',true);

-- creiamo una tabella curriculum
create table if not exists curriculum(
name 				varchar(100) primary key,
description			text not null);

-- inseriamo il nome di un  curriculum per i cicli
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



-- creiamo la tabella cicli
create table if not exists cycle(
number				integer primary key,
description			text not null,
year				varchar(4) not null,
coordinator			varchar(50),
foreign key (coordinator) references professor(email) on delete no action on update cascade);

-- inseriamo i cicli
INSERT INTO cycle (number,description,year,coordinator)VALUES
 (15, 'Il corso di Dottorato di Ricerca in Management 
 & Information Technology ha come obiettivo la formazione
 di specialisti della ricerca in ambito economico-aziendale
 ed informatico. Il corso è strutturato in tre curricula, 
 denominati (i) Economia e Direzione delle Aziende Pubbliche,
 (i) Marketing e Comunicazione e (iii)
 Informatica, Sistemi Informativi e Tecnologie del Software. Attraverso i tre curricula, il corso di Dottorato intende formare figure professionali diverse, ma che riescano ad interagire per la soluzione di problemi complessi in ambito economico-aziendale, grazie all''utilizzo di tecnologie dell''informazione innovative e ad un approccio inter-disciplinare che favorisca la reciproca condivisione di idee e competenze. Il primo curriculum mira a formare specialisti nel settore del management di enti, istituzioni ed aziende afferenti al settore pubblico, con conoscenze relative a principi, teorie e modelli di gestione dei processi di innovazione nella Pubblica Amministrazione. Il secondo curriculum mira a formare specialisti in grado di utilizzare le più avanzate, innovative ed affidabili metodologie di ricerca scientifica in campo economico-sociale, con particolare riferimento al marketing ed alla comunicazione. Infine, il terzo curriculum mira a formare specialisti nel settore dell''Informatica, con conoscenza degli aspetti teorici, metodologici e sperimentali dei sistemi informativi, dell''ingegneria del software, dei dati e della conoscenza, dell''elaborazione di immagini e dell''interazione uomo-macchina, con applicazioni in particolare alla economia e alla gestione aziendale.Il completamento del Corso di Dottorato ed il superamento dell''esame finale consente per tutti e tre i curricula lo svolgimento di attività di ricerca in ambito accademico, nei settori dell''economia aziendale e dell''informatica, costituendo un titolo legalmente riconosciuto nei concorsi universitari, nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l''inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.',
 '2015', 'adelucia@unisa.it'),
 
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
 , '2015', 'dracula@unisa.it');

 
 -- inseiramo i curriculm dei cicli 
create table if not exists curriculumcic(
curriculum 			varchar(100),
cycle				integer,
coordinator			varchar(50),
primary key (curriculum,cycle),
foreign key (curriculum) references curriculum(name) on delete cascade on update cascade,
foreign key (cycle) references cycle(number) on delete cascade on update cascade,
foreign key (coordinator) references professor(email) on delete no action on update cascade);

-- inseriamo  i curriculum dei cicli tenuti da un proff
INSERT INTO curriculumcic (curriculum,cycle,coordinator)VALUES
('Informatica, Sistemi Informativi e Tecnologie del Software',15,'adelucia@unisa.it');

-- creiamo una tabella insegnamento
create table if not exists teach(
curriculum 			varchar(100),
cycle				integer,
professor			varchar(50),
primary key (curriculum,cycle,professor),
foreign key (curriculum) references curriculumcic(curriculum) on delete cascade on update cascade,
foreign key (cycle) references curriculumcic(cycle) on delete cascade on update cascade,
foreign key (professor) references professor(email) on delete no action on update cascade);
-- inseriamo l'insegnamento sul ciclo di un curri
INSERT INTO teach (curriculum,cycle,professor)VALUES
('Informatica, Sistemi Informativi e Tecnologie del Software',15,'adelucia@unisa.it');

-- creiamo la tabella corso

create table if not exists course(
idcourse			integer	primary key,
curriculum 			varchar(100) not null,
cycle				integer not null,
name				varchar(50) not null,
unique (curriculum,cycle,name),
foreign key (curriculum) references curriculumcic(curriculum) on delete cascade on update cascade,
foreign key (cycle) references curriculumcic(cycle) on delete cascade on update cascade);

-- inseriamo i corsi

INSERT INTO course (idcourse,curriculum,cycle,name)VALUES
(1,'Informatica, Sistemi Informativi e Tecnologie del Software',15,'IS2');

-- creiamo la tabella studente
create table IF not EXIsts phdstudent(
email 			   	varchar(50) primary key,
secondaryemail		varchar(50) not null,
surname				varchar(25) not null,
name				varchar(25) not null,
password			varchar(16) not null,
telephone			varchar(15),
isactive			boolean default false,
link 				varchar(150),
deparment			varchar(50) not null,
isadministrator		boolean default false,
researchinterest	text,
cycle 				integer,
curriculum 			varchar(100),
tutor				varchar(50),
foreign key (tutor) references professor(email) on delete no action on update cascade,
foreign key (curriculum) references curriculumcic(curriculum) on delete no action on update cascade,
foreign key (cycle) references curriculumcic(cycle) on delete no action on update cascade);

-- inseriamo il phdstudent
INSERT INTO phdstudent(email, secondaryemail,surname,name,password,telephone,isactive,link,deparment,isadministrator,researchinterest,cycle,curriculum,tutor)VALUES
('ballo@unisa.it','ballo@hotmail.it', 'Conti','Carlo', 'test1','3881144567',true,null,'Dipartimento di Informatica',null,null,null,null,null),

('dinucci@unisa.it','dinucci@gmail.com','Dario','Dinucci','test6','3881144567',true,'http://www.sesa.unisa.it/people/ddinucci/
','Dipartimento di Informatica',true,'He is
 an open-source enthusiast who likes to work
 on challenging project. In 2014 he completed 
the 10th edition of Google Summer
 of Code working on Gnome Maps and GraphHopper.',15,'Informatica, Sistemi Informativi e Tecnologie del Software','adelucia@unisa.it');

-- creiamo la tabella publication
create table IF not EXIsts publication(
idpublication 		integer	primary key,
title 				varchar(50) not null,
year				varchar(4) not null,
numberpage    		integer not null check(numberpage>0),
link				varchar(150),
type				varchar(20),
otherauthors 		varchar(255),
abstract			text not null,
phdstudent 			varchar(50) not null,
foreign key (phdstudent) references phdstudent(email) on delete cascade on update cascade);
-- inseriamo pubblicaione
INSERT INTO publication(idpublication,title,year,numberpage,link,type,otherauthors,abstract,phdstudent )VALUES
(1,'ICSE 2015 Trip Summary','2015',1,'http://www.sesa.unisa.it/people/ddinucci/pdf/3_trip-report_SEN.pdf',
 'Ricerca',null,
 'Abstract ICSE 2015 has been my first conference and in my opinion it has
been amazing! My agenda in the period 16th
- 22th of May has been
full of very interesting talks and keynotes.
For my PhD studies, I am working on Search Based Software Testing
and Mining Software Repositories. Thus, the formula MSR +
SBST + ICSE was perfect for quickly approaching problems and
meet top researchers in these fields.
In this paper I am going to summarize my experience in brief','dinucci@unisa.it');

-- creiamo la tabbelaa missioni
create table IF not EXIsts mission(
idmission	 		integer	primary key,
description 		text not null,
startdate			date not null,
enddate				date not null,
reference			varchar(255),
place				varchar(70) not null,
phdstudent 			varchar(50) not null,
foreign key (phdstudent) references phdstudent(email) on delete cascade on update cascade);
-- insieriamo le missioni
INSERT INTO mission (idmission,description,startdate,enddate,reference,place,phdstudent)VALUES
(1,'la ricerca dei numeri primi','2016-05-25','2017-06-25',null,'Roma','dinucci@unisa.it');

create table IF not EXIsts collaboration(
idcollaboration	 		integer	primary key,
description 		text not null,
startdate			date not null,
enddate				date not null,
istitution 			varchar(70),
phdstudent 			varchar(50) not null,
foreign key (phdstudent) references phdstudent(email) on delete cascade on update cascade);
-- creiamo la tabella collaboraioni
INSERT INTO collaboration (idcollaboration,description,startdate,enddate,istitution,phdstudent)VALUES
(1,'Studio dei bit al contrario','2016-05-25','2017-06-25','Dipartimento di Informatica','dinucci@unisa.it')       ;


-- creiamo un seminario
create table IF not EXIsts seminar(
idseminar	 		integer	primary key,
date				date not null,
starttime			char(5) not null,
endtime				char(5) not null,
name				varchar(70) not null,
namespeacker 		varchar(50) not null,
desription 			text not null,
place				varchar(50) not null,
course				integer not null,
foreign key (course) references course(idcourse) on delete cascade on update cascade);
-- inseriamo un seminario
INSERT INTO seminar (idseminar,date,starttime,endtime,name,namespeacker,desription,place,course)VALUES
(1,'2016-05-28','10:15','15:10','Come prepararsi ad un colloquio','Gerardo Gallesi','Consigli su 
come prepararsi ad un colloqui nel migliore dei modi',
'Aula P16',1 );
-- creiamo una tabella lezione
create table IF not EXIsts lesson(
idlesson	 		integer	primary key,
date				date not null,
starttime			char(5) not null,
endtime				char(5) not null,
name				varchar(70) not null,
classroom			varchar(30) not null,
desription 			text not null,
cycle				integer not null,
curriculum			varchar(100) not null,
course				integer not null,
foreign key (course) references course(idcourse) on delete cascade on update cascade);
-- inseriamo una lezione
INSERT INTO lesson (idlesson,date,starttime,endtime	,name,classroom,desription,cycle,curriculum,course)VALUES
(1,'2016-05-2','9:10','12:10','ISTS','P12',
'introduzione al corso','15','Informatica, Sistemi Informativi 
e Tecnologie del Software',1);
-- relazione fra proff e lezione
create table IF not EXIsts keep(
professor			varchar(50) not null,
lesson	 			integer	not null,
primary key(professor, lesson),
foreign key (professor) references professor(email) on delete no action on update cascade,
foreign key (lesson) references lesson(idlesson) on delete cascade on update cascade);
-- insieriamo il collegamento
INSERT INTO keep (professor,lesson)VALUES
('adelucia@unisa.it',1) ;

-- creiamo le presenze
create table IF not EXIsts presence(
phdstudent			varchar(50) not null,
lesson	 			integer	not null,
ispresent			boolean default false,
primary key(phdstudent, lesson),
foreign key (phdstudent) references phdstudent(email) on delete no action on update cascade,
foreign key (lesson) references lesson(idlesson) on delete cascade on update cascade);
-- inserimo le presenze
INSERT INTO presence (phdstudent,lesson,ispresent)VALUES
('dinucci@unisa.it',1,false);
