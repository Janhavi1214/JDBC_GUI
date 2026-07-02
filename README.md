# JDBC GUI — Java Desktop App with MySQL Integration

A Java Swing desktop application that demonstrates full CRUD operations on a MySQL database through a graphical user interface, built using raw JDBC without any ORM layer.

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 21 |
| GUI | Java Swing / AWT |
| Database Connectivity | JDBC (Java Database Connectivity) |
| Database | MySQL |
| Driver | MySQL Connector/J 9.7.0 |
| IDE | Eclipse |

---

## Features

- Connect to a MySQL database using JDBC `DriverManager`
- Perform **Insert**, **Read**, **Update**, and **Delete** operations via GUI forms
- Display query results in a visual table (JTable / JScrollPane)
- Input fields and buttons for user-driven database interaction
- Real-time feedback for successful/failed operations

---

## Project Structure

```
JDBC_GUI/
├── src/                  # Java source files
├── bin/                  # Compiled .class files
├── .classpath            # Eclipse classpath (includes MySQL connector JAR)
└── .project              # Eclipse project descriptor
```

---

## Prerequisites

- Java 21 (JDK)
- MySQL Server running locally
- MySQL Connector/J 9.7.0 JAR (`mysql-connector-j-9.7.0.jar`)
- Eclipse IDE (recommended)

---

## Setup & Run

**1. Clone the repository**
```bash
git clone https://github.com/Janhavi1214/JDBC_GUI.git
cd JDBC_GUI
```

**2. Add MySQL Connector JAR**

Download [MySQL Connector/J 9.7.0](https://dev.mysql.com/downloads/connector/j/) and place the JAR in a known path on your machine.

In Eclipse: Right-click project → **Build Path** → **Add External JARs** → select the connector JAR.

**3. Configure the database connection**

In the source file where the connection is established, update these values to match your local MySQL setup:

```java
String url = "jdbc:mysql://localhost:3306/your_database_name";
String user = "your_mysql_username";
String password = "your_mysql_password";
```

**4. Create the required table**

Run the appropriate `CREATE TABLE` SQL in your MySQL client before launching the app.

**5. Run**

Open the project in Eclipse and run the main class. The GUI window will launch.

---

## Concepts Demonstrated

- **JDBC lifecycle**: `Class.forName()` → `DriverManager.getConnection()` → `PreparedStatement` → `ResultSet`
- **SQL injection prevention** using `PreparedStatement` over raw `Statement`
- **Event-driven programming** with Swing `ActionListener`
- **MVC separation** between UI components and database logic
- **Exception handling** for SQL errors and connection failures

---

## Learnings

This project was built to solidify understanding of how Java applications communicate with relational databases at a low level — before moving on to higher-level abstractions like JPA/Hibernate used in Spring Boot applications.

---

## Author

**Janhavi** — B.E. Computer Science Engineering, RCOEM Nagpur  
[GitHub](https://github.com/Janhavi1214)
