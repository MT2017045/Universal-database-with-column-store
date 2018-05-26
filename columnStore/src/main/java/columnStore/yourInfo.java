package columnStore;

public class yourInfo {

	public static String username="root";
	public static String pass="@grawal1";
}

/*
NEW DATABASE:

create database DBname;
use DBname;
create table namelist( dbID int not null auto_increment primary key, dbname varchar(255) );
create table query( queryID int not null auto_increment primary key, queryname varchar(255), query varchar(255), dbname varchar(255));
*/