* SQL, Modesl and Migrations

* Database Management Systems
	* MySQL
		* CHAR(size)
		* VARCHAR(size)
		* SMALLINT
		* INT
		* BIGINT
		* FLOAT
		* DOUOBLE
	* PostgreSQL
	* SQLite
		* Types
			* TEXT
			* NUMERIC
			* INTEGER
			* REAL
			* BLOB (Binary)


SQLite
# CREATE TABLE
```
CREATE TABLE flights{
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	origin TEXT NOT NULL,
	destination TEXT NOT NULL,
	duration INTEGER NOT NULL
};
```

```id, origin, destination, duration``` are ```name```
```INTEGER, TEXT``` are the ```type```
```NOT NULL, PRIMARY KEY``` are ```constraint```
```AUTOINCREMENT``` is ```automatic updated```

* Constraints
	* CHECK
	* DEFAULT
	* NOT NULL
	* PRIMARY KEY
	* UNIQUE
	
# INSERT INTO
```
INSERT INTO 
	flights(origin, destination, durationo) 
	VALUES("New York", "London", 415)
```

# SELECT
## SELECT ALL
```
SELECT * FROM flights
```
## SELECT ONLY ORIGIN, DESTINATION
```
SELECT origin, destination FROM flights
```

## column data = value
```
SELECT * FROM flights WHERE id = 3;
```

```
SELECT * FROM flights WHERE origin = "New York";
```

## column data <, >
```
SELECT * FROM flights WHERE duration > 500;
```

## AND
```
SELECT * FROM flights WHERE duration > 500 AND destination = "Paris";
```

## OR
```
SELECT * FROM flights WHERE duration > 500 OR destination = "Paris";
```

## column IN
* check sequence of NewYork or Lima
```
SELECT * FROM flights WHERE origin IN("New York", "Lima");
```

## LIKE
* column data has a character "a"
```
SELECT * FROM flights WHERE origin like "%a%";
```
* Functions
	* AVERAGE
	* COUNT
	* MAX
	* MIN
	* SUM

# UPDATE
```
UPDATE flights
	SET duration = 430
	WHERE origin = "New York"
	AND destination = "London";
```

# DELETE
```
DELETE from flights WHERE destination = "Tokyo";
```

* Clauses
	* LIMIT
	* ORDER BY
	* GROUP BY
	* HAVING