use dogwebapp;
drop table health_advice; drop table activity_log;  drop table dog; drop table user; drop table dog;

create table activity (
                          activityid integer not null auto_increment,
                          name varchar(255) not null,
                          primary key (activityid)
) engine=InnoDB;

create table activity_log (
                              activity_id integer not null,
                              dog_id integer not null,
                              id integer not null auto_increment,
                              timestamp datetime(6) not null,
                              notes varchar(255),
                              primary key (id)
) engine=InnoDB;

create table dog (
                     age integer not null,
                     dogid integer not null auto_increment,
                     height float(23) not null,
                     userid integer not null,
                     weight integer not null,
                     breed_name varchar(255) not null,
                     imageurl varchar(255),
                     name varchar(255) not null,
                     primary key (dogid)
) engine=InnoDB;

create table health_advice (
                               adviceid integer not null auto_increment,
                               dogid integer not null,
                               advice_text varchar(255),
                               `condition` enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
                               primary key (adviceid)
) engine=InnoDB;

create table user (
                      last_login date,
                      registration_date date not null,
                      userid integer not null auto_increment,
                      email varchar(255) not null,
                      name varchar(255) not null,
                      password varchar(255) not null,
                      primary key (userid)
) engine=InnoDB;

alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

alter table activity_log
    add constraint FKadd6g0gp5xkonquavbrmowk7c
        foreign key (activity_id)
            references activity (activityid);

alter table activity_log
    add constraint FKdfobs7b448pdsy0ky9w8puu1j
        foreign key (dog_id)
            references dog (dogid);

alter table dog
    add constraint FKl7le8hg2smplod9ecy259xha0
        foreign key (userid)
            references user (userid);

alter table health_advice
    add constraint FKnc3isxkjy8hjpsjbi7j3cqua0
        foreign key (dogid)
            references dog (dogid);

/*atat*/
    create table activity (
        activityid integer not null auto_increment,
        name varchar(255) not null,
        primary key (activityid)
    ) engine=InnoDB;

    create table activity_log (
        activity_id integer not null,
        dog_id integer not null,
        id integer not null auto_increment,
        timestamp datetime(6) not null,
        notes varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table dog (
        age integer not null,
        dogid integer not null auto_increment,
        height float(23) not null,
        userid integer not null,
        weight integer not null,
        breed_name varchar(255) not null,
        imageurl varchar(255),
        name varchar(255) not null,
        primary key (dogid)
    ) engine=InnoDB;

    create table health_advice (
        adviceid integer not null auto_increment,
        dogid integer not null,
        advice_text varchar(255),
        condition enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
        primary key (adviceid)
    ) engine=InnoDB;

    create table user (
        last_login date,
        registration_date date not null,
        userid integer not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (userid)
    ) engine=InnoDB;

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table activity_log 
       add constraint FKadd6g0gp5xkonquavbrmowk7c 
       foreign key (activity_id) 
       references activity (activityid);

    alter table activity_log 
       add constraint FKdfobs7b448pdsy0ky9w8puu1j 
       foreign key (dog_id) 
       references dog (dogid);

    alter table dog 
       add constraint FKl7le8hg2smplod9ecy259xha0 
       foreign key (userid) 
       references user (userid);

    alter table health_advice 
       add constraint FKnc3isxkjy8hjpsjbi7j3cqua0 
       foreign key (dogid) 
       references dog (dogid);

    create table activity (
        activityid integer not null auto_increment,
        name varchar(255) not null,
        primary key (activityid)
    ) engine=InnoDB;

    create table activity_log (
        activity_id integer not null,
        dog_id integer not null,
        id integer not null auto_increment,
        timestamp datetime(6) not null,
        notes varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table dog (
        age integer not null,
        dogid integer not null auto_increment,
        height float(23) not null,
        userid integer not null,
        weight integer not null,
        breed_name varchar(255) not null,
        imageurl varchar(255),
        name varchar(255) not null,
        primary key (dogid)
    ) engine=InnoDB;

    create table health_advice (
        adviceid integer not null auto_increment,
        dogid integer not null,
        advice_text varchar(255),
        condition enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
        primary key (adviceid)
    ) engine=InnoDB;

    create table user (
        last_login date,
        registration_date date not null,
        userid integer not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (userid)
    ) engine=InnoDB;

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table activity_log 
       add constraint FKadd6g0gp5xkonquavbrmowk7c 
       foreign key (activity_id) 
       references activity (activityid);

    alter table activity_log 
       add constraint FKdfobs7b448pdsy0ky9w8puu1j 
       foreign key (dog_id) 
       references dog (dogid);

    alter table dog 
       add constraint FKl7le8hg2smplod9ecy259xha0 
       foreign key (userid) 
       references user (userid);

    alter table health_advice 
       add constraint FKnc3isxkjy8hjpsjbi7j3cqua0 
       foreign key (dogid) 
       references dog (dogid);

    create table activity (
        activityid integer not null auto_increment,
        name varchar(255) not null,
        primary key (activityid)
    ) engine=InnoDB;

    create table activity_log (
        activity_id integer not null,
        dog_id integer not null,
        id integer not null auto_increment,
        timestamp datetime(6) not null,
        notes varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table dog (
        age integer not null,
        dogid integer not null auto_increment,
        height float(23) not null,
        userid integer not null,
        weight integer not null,
        breed_name varchar(255) not null,
        imageurl varchar(255),
        name varchar(255) not null,
        primary key (dogid)
    ) engine=InnoDB;

    create table health_advice (
        adviceid integer not null auto_increment,
        dogid integer not null,
        advice_text varchar(255),
        condition enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
        primary key (adviceid)
    ) engine=InnoDB;

    create table user (
        last_login date,
        registration_date date not null,
        userid integer not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (userid)
    ) engine=InnoDB;

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table activity_log 
       add constraint FKadd6g0gp5xkonquavbrmowk7c 
       foreign key (activity_id) 
       references activity (activityid);

    alter table activity_log 
       add constraint FKdfobs7b448pdsy0ky9w8puu1j 
       foreign key (dog_id) 
       references dog (dogid);

    alter table dog 
       add constraint FKl7le8hg2smplod9ecy259xha0 
       foreign key (userid) 
       references user (userid);

    alter table health_advice 
       add constraint FKnc3isxkjy8hjpsjbi7j3cqua0 
       foreign key (dogid) 
       references dog (dogid);

    create table activity (
        activityid integer not null auto_increment,
        name varchar(255) not null,
        primary key (activityid)
    ) engine=InnoDB;

    create table activity_log (
        activity_id integer not null,
        dog_id integer not null,
        id integer not null auto_increment,
        timestamp datetime(6) not null,
        notes varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table dog (
        age integer not null,
        dogid integer not null auto_increment,
        height float(23) not null,
        userid integer not null,
        weight integer not null,
        breed_name varchar(255) not null,
        imageurl varchar(255),
        name varchar(255) not null,
        primary key (dogid)
    ) engine=InnoDB;

    create table health_advice (
        adviceid integer not null auto_increment,
        dogid integer not null,
        advice_text varchar(255),
        condition enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
        primary key (adviceid)
    ) engine=InnoDB;

    create table user (
        last_login date,
        registration_date date not null,
        userid integer not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (userid)
    ) engine=InnoDB;

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table activity_log 
       add constraint FKadd6g0gp5xkonquavbrmowk7c 
       foreign key (activity_id) 
       references activity (activityid);

    alter table activity_log 
       add constraint FKdfobs7b448pdsy0ky9w8puu1j 
       foreign key (dog_id) 
       references dog (dogid);

    alter table dog 
       add constraint FKl7le8hg2smplod9ecy259xha0 
       foreign key (userid) 
       references user (userid);

    alter table health_advice 
       add constraint FKnc3isxkjy8hjpsjbi7j3cqua0 
       foreign key (dogid) 
       references dog (dogid);

    create table activity (
        activityid integer not null auto_increment,
        name varchar(255) not null,
        primary key (activityid)
    ) engine=InnoDB;

    create table activity_log (
        activity_id integer not null,
        dog_id integer not null,
        id integer not null auto_increment,
        timestamp datetime(6) not null,
        notes varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table dog (
        age integer not null,
        dogid integer not null auto_increment,
        height float(23) not null,
        userid integer not null,
        weight integer not null,
        breed_name varchar(255) not null,
        imageurl varchar(255),
        name varchar(255) not null,
        primary key (dogid)
    ) engine=InnoDB;

    create table health_advice (
        adviceid integer not null auto_increment,
        dogid integer not null,
        advice_text varchar(255),
        condition enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
        primary key (adviceid)
    ) engine=InnoDB;

    create table user (
        last_login date,
        registration_date date not null,
        userid integer not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (userid)
    ) engine=InnoDB;

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table activity_log 
       add constraint FKadd6g0gp5xkonquavbrmowk7c 
       foreign key (activity_id) 
       references activity (activityid);

    alter table activity_log 
       add constraint FKdfobs7b448pdsy0ky9w8puu1j 
       foreign key (dog_id) 
       references dog (dogid);

    alter table dog 
       add constraint FKl7le8hg2smplod9ecy259xha0 
       foreign key (userid) 
       references user (userid);

    alter table health_advice 
       add constraint FKnc3isxkjy8hjpsjbi7j3cqua0 
       foreign key (dogid) 
       references dog (dogid);

    create table activity (
        activityid integer not null auto_increment,
        name varchar(255) not null,
        primary key (activityid)
    ) engine=InnoDB;

    create table activity_log (
        activity_id integer not null,
        dog_id integer not null,
        id integer not null auto_increment,
        timestamp datetime(6) not null,
        notes varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table dog (
        age integer not null,
        dogid integer not null auto_increment,
        height float(23) not null,
        userid integer not null,
        weight integer not null,
        breed_name varchar(255) not null,
        imageurl varchar(255),
        name varchar(255) not null,
        primary key (dogid)
    ) engine=InnoDB;

    create table health_advice (
        adviceid integer not null auto_increment,
        dogid integer not null,
        advice_text varchar(255),
        condition enum ('OVERWEIGHT','UNDERWEIGHT','SICK') not null,
        primary key (adviceid)
    ) engine=InnoDB;

    create table user (
        last_login date,
        registration_date date not null,
        userid integer not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (userid)
    ) engine=InnoDB;

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table activity_log 
       add constraint FKadd6g0gp5xkonquavbrmowk7c 
       foreign key (activity_id) 
       references activity (activityid);

    alter table activity_log 
       add constraint FKdfobs7b448pdsy0ky9w8puu1j 
       foreign key (dog_id) 
       references dog (dogid);

    alter table dog 
       add constraint FKl7le8hg2smplod9ecy259xha0 
       foreign key (userid) 
       references user (userid);

    alter table health_advice 
       add constraint FKnc3isxkjy8hjpsjbi7j3cqua0 
       foreign key (dogid) 
       references dog (dogid);
