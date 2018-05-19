/**
 * sql file
 */

--drop database if exists news_feeds;
create database if not exists news_feeds;
use news_feeds;
drop table if exists users;
create table users(
	id bigint(20) auto_increment not null,
	name varchar(20) not null unique,
	password varchar(20) not null,
	gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP comment 'create time',
	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)DEFAULT CHARSET=utf8;

insert into users(name,password,gmt_create) values('test','123456',now());
insert into users(name,password) values('test1','123456');
insert into users(name,password,gmt_create) values('test2','123456',now());
insert into users(name,password) values('test3','123456');
insert into users(name,password) values('test4','123456');
insert into users(name,password) values('test5','123456');
insert into users(name,password) values('test6','123456');
insert into users(name,password) values('test7','123456');
insert into users(name,password) values('test8','123456');
insert into users(name,password) values('test9','123456');
update users set name='user1' where name='test1';

use news_feeds;
drop table if exists news_catalog;
create table news_catalog(
	id bigint(20) auto_increment not null,
	name varchar(100) NOT NULL unique,
	gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)default charset=utf8;

insert into news_catalog(id, name) values(1,'domestic');
insert into news_catalog(id, name) values(2,'world');
insert into news_catalog(id, name) values(3,'society');
insert into news_catalog(id, name) values(4,'war');
insert into news_catalog(id, name) values(5,'air');
insert into news_catalog(id, name) values(6,'UAV');
insert into news_catalog(id, name) values(9,'other');

insert into news_catalog(id, name) values(10,'国内');
insert into news_catalog(id, name) values(11,'国际');
insert into news_catalog(id, name) values(12,'社会');
insert into news_catalog(id, name) values(13,'战争');
insert into news_catalog(id, name) values(14,'航空');
insert into news_catalog(id, name) values(15,'无人机');
insert into news_catalog(id, name) values(16,'其他');


use news_feeds;
drop table if exists news;
create table news(
	id bigint(20) auto_increment not null,
	title varchar(40) not null unique,
	url varchar(255) NOT NULL,
	media varchar(20) default 'self',
	content varchar(100) comment '备注',
	catalog_id bigint(20) NOT NULL,
	imgurl varchar(255),
	gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id),
	CONSTRAINT catalog_news_id FOREIGN KEY (catalog_id) REFERENCES news_catalog (id) 
)default charset=utf8;
insert into news(title, url,catalog_id, imgurl)
values('test1news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test2news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test3news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test4news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test5news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test6news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test7news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test8news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test9news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test10news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test11news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');
insert into news(title, url,catalog_id, imgurl)
values('test12news','http://news.163.com/air/18/0210/09/DA980G0U000181O6.html',9,'http://cms-bucket.nosdn.127.net/e9883160a0a14f0db682905225807d4e20170331120024.jpeg');

/*
drop table if exists article;
create table article(
	id bigint(20) auto_increment not null,
	title varchar(40) not null,
	author varchar(10) not null,
	url varchar(255) not null,
	gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)default charset=utf8;
*/

use news_feeds;
DROP TABLE IF EXISTS comment;
create table comment(
	id bigint(20) auto_increment not null,
	user_id bigint(20) NOT NULL,
    news_id bigint(20) NOT NULL,
    content varchar(1000),
    gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(id),
    CONSTRAINT comment_user_id FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade,
    CONSTRAINT comment_news_id FOREIGN KEY (news_id) REFERENCES news(id) on delete cascade
)default charset=utf8;
insert into comment(user_id, news_id,content) 
values(1,1,'testcomment');
insert into comment(user_id, news_id,content) 
values(1,2,'testcomment');
insert into comment(user_id, news_id,content) 
values(2,3,'testcomment');


/*
 * 保存用户对新闻的评分，即喜好程度
 */
use news_feeds;
DROP TABLE IF EXISTS news_preferences;
CREATE TABLE news_preferences(
	id bigint(20) auto_increment not null,
   	user_id bigint(20) NOT NULL, 
   	news_id bigint(20) NOT NULL, 
   	preference INTEGER NOT NULL DEFAULT 0, 
   	gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   	primary key(id, user_id, news_id),
   	INDEX (user_id),
    INDEX (news_id),
   	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, 
   	FOREIGN KEY (news_id) REFERENCES news(id) ON DELETE CASCADE 
); 
insert into news_preferences(user_id, news_id, preference)
values(1,1,3);
insert into news_preferences(user_id, news_id, preference)
values(1,2,1);
insert into news_preferences(user_id, news_id, preference)
values(1,3,4);
insert into news_preferences(user_id, news_id, preference)
values(1,4,2);
insert into news_preferences(user_id, news_id, preference)
values(1,5,1);
insert into news_preferences(user_id, news_id, preference)
values(1,6,2);
insert into news_preferences(user_id, news_id, preference)
values(1,7,2);
insert into news_preferences(user_id, news_id, preference)
values(1,8,3);
insert into news_preferences(user_id, news_id, preference)
values(1,9,3);
insert into news_preferences(user_id, news_id, preference)
values(1,10,1);
insert into news_preferences(user_id, news_id, preference)
values(2,1,5);
insert into news_preferences(user_id, news_id, preference)
values(2,2,1);
insert into news_preferences(user_id, news_id, preference)
values(2,3,3);
insert into news_preferences(user_id, news_id, preference)
values(2,4,1);
insert into news_preferences(user_id, news_id, preference)
values(2,5,1);
insert into news_preferences(user_id, news_id, preference)
values(2,6,1);
insert into news_preferences(user_id, news_id, preference)
values(2,7,1);
insert into news_preferences(user_id, news_id, preference)
values(2,8,1);
insert into news_preferences(user_id, news_id, preference)
values(2,9,1);

insert into news_preferences(user_id, news_id, preference)
values(3,1,2);
insert into news_preferences(user_id, news_id, preference)
values(3,2,5);
insert into news_preferences(user_id, news_id, preference)
values(3,3,1);
insert into news_preferences(user_id, news_id, preference)
values(3,4,3);
insert into news_preferences(user_id, news_id, preference)
values(3,5,1);
insert into news_preferences(user_id, news_id, preference)
values(3,6,2);
insert into news_preferences(user_id, news_id, preference)
values(3,7,5);
insert into news_preferences(user_id, news_id, preference)
values(3,8,1);
insert into news_preferences(user_id, news_id, preference)
values(3,9,1);
insert into news_preferences(user_id, news_id, preference)
values(3,10,1);

insert into news_preferences(user_id, news_id, preference)
values(4,1,3);
insert into news_preferences(user_id, news_id, preference)
values(4,2,5);
insert into news_preferences(user_id, news_id, preference)
values(4,3,4);
insert into news_preferences(user_id, news_id, preference)
values(4,4,1);
insert into news_preferences(user_id, news_id, preference)
values(4,5,2);
insert into news_preferences(user_id, news_id, preference)
values(4,6,3);

insert into news_preferences(user_id, news_id, preference)
values(4,8,3);
insert into news_preferences(user_id, news_id, preference)
values(4,9,5);
insert into news_preferences(user_id, news_id, preference)
values(4,10,3);
insert into news_preferences(user_id, news_id, preference)
values(5,1,3);

insert into news_preferences(user_id, news_id, preference)
values(5,3,4);
insert into news_preferences(user_id, news_id, preference)
values(5,4,3);
insert into news_preferences(user_id, news_id, preference)
values(5,5,2);
insert into news_preferences(user_id, news_id, preference)
values(5,6,5);
insert into news_preferences(user_id, news_id, preference)
values(5,7,2);
insert into news_preferences(user_id, news_id, preference)
values(5,8,1);
insert into news_preferences(user_id, news_id, preference)
values(5,9,3);
insert into news_preferences(user_id, news_id, preference)
values(5,10,5);


/**
 * 保存新闻和新闻的相似程度
 */
use news_feeds;
drop table if exists news_similarity;
CREATE TABLE news_similarity(
	id bigint(20) auto_increment not null,
    news_id1 bigint(20) NOT NULL, 
    news_id2 bigint(20) NOT NULL, 
    similarity DOUBLE NOT NULL DEFAULT 0, 
    gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(id),
    FOREIGN KEY (news_id1) REFERENCES news(id) ON DELETE CASCADE, 
    FOREIGN KEY (news_id2) REFERENCES news(id) ON DELETE CASCADE 
); 
 
CREATE INDEX news_preferences_index1 ON news_preferences ( user_id , news_id ); 
CREATE INDEX news_preferences_index2 ON news_preferences ( user_id ); 
CREATE INDEX news_preferences_index3 ON news_preferences ( news_id );




/**
 * behavior 是用户对该新闻的相关行为
 * 0无行为0000 nothing
 * 1点击0001 click
 * 2阅读0010 read
 * 3评论0011 comment
 * 4收藏0100 collect
 * 5转发0101 forward
 * 其值为以上之和
 */
use news_feeds;
DROP TABLE IF EXISTS behavior_logs;
create table behavior_logs(
	id bigint(20) auto_increment not null,
	user_id bigint(20) NOT NULL,
    news_id bigint(20) NOT NULL,
    click int(8) default 0,
    reading int(8) default 0,
    comment int(8) default 0,
    collect int(8) default 0,
    forward int(8) default 0,
    other int(8) default 0,
	gmt_create  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
	primary key(id, user_id, news_id),
   	INDEX (user_id),
    INDEX (news_id),
    CONSTRAINT newslogs_user_id FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade,
    CONSTRAINT newslogs_news_id FOREIGN KEY (news_id) REFERENCES news (id) on delete cascade
)default charset=utf8;


--use news_feeds;
--drop table if exists news_tags;
--create table news_tags(
--	id bigint(20) auto_increment not null,
--	news_id bigint(20) not null,
--	tags varchar(200) unique,
--	gmt_create  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
--	gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
--	primary key(id),
--	CONSTRAINT tags_news_id FOREIGN KEY (news_id) REFERENCES news (id)
--)default charset=utf8;

show databases;









