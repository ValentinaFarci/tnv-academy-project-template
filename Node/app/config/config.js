import { Sequelize } from "sequelize";

const db = new Sequelize({
  database: "tnv-final-project",
  username: "root",
  host: "localhost",
  port: 3306,
  dialect: "mysql",
});

export default db;
