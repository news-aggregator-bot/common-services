create table source (id bigint not null auto_increment primary key, name varchar(255) not null, creation_date datetime(6), update_date datetime(6));
create table source_page (id bigint not null auto_increment primary key, name varchar(255) not null, url varchar(255) not null, creation_date datetime(6), update_date datetime(6), id_source bigint not null, language varchar(50) not null);
create table source_page_content_tag (id bigint not null auto_increment primary key, main varchar(100) not null, title varchar(100) not null, description varchar(100) null, creation_date datetime(6), update_date datetime(6), id_source bigint not null);
create table category (id bigint not null auto_increment primary key, name varchar(255) not null unique, id_parent bigint null);
create table category_localisation (id bigint not null auto_increment primary key, id_category bigint not null, language varchar(50) not null, value varchar(255) not null);
create table tag (id bigint not null auto_increment primary key, name varchar(255) not null unique);
create table `language` (lang varchar(50) not null primary key, name varchar(255) not null unique, localized varchar(255) not null);
create table news_note (id bigint not null auto_increment primary key, title varchar(255) not null, url varchar(255) not null unique, description text null, creation_date datetime(6), update_date datetime(6), id_source_page bigint not null);
create table reader (id bigint not null auto_increment primary key, nick varchar(255) not null unique, name varchar(255) not null, creation_date datetime(6), update_date datetime(6), platform varchar(50) not null);
create table platform (name varchar(50) not null primary key);


create table source_page_tag (id_source_page bigint not null, id_content_tag bigint not null);
create table source_page_category (id_source_page bigint not null, id_category bigint not null);
create table category_tag (id_category bigint not null, id_tag bigint not null);
create table reader_lang (id_reader bigint not null, language varchar(50) not null);
create table reader_source_page (id_reader bigint not null, id_source_page bigint not null);


alter table source_page add foreign key (id_source) references source(id);
alter table source_page add foreign key (language) references language(lang);
alter table source_page_tag add foreign key (id_source_page) references source_page(id);
alter table news_note add foreign key (id_source_page) references source_page(id);
alter table source_page_tag add foreign key (id_content_tag) references source_page_content_tag(id);
alter table source_page_category add foreign key (id_source_page) references source_page(id);
alter table source_page_category add foreign key (id_category) references category(id);
alter table category_tag add foreign key (id_category) references category(id);
alter table category_tag add foreign key (id_tag) references tag(id);
alter table reader add foreign key (platform) references platform(name);
alter table reader_lang add foreign key (id_reader) references reader(id);
alter table reader_lang add foreign key (language) references language(lang);
alter table reader_source_page add foreign key (id_reader) references reader(id);
alter table reader_source_page add foreign key (id_source_page) references source_page(id);
alter table category_localisation add foreign key (id_category) references category(id);
alter table category_localisation add foreign key (language) references language(lang);
alter table category add foreign key (id_parent) references category(id);

create index NEWS_NOTE_URL on news_note (url);

