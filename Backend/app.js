// app.js

/**
 * Required External Modules
 */
//  flexible Node.js web application framework that provides a robust set of features to develop web and mobile applications
const express = require("express");
const bodyParser = require('body-parser');
var UserRoutes = require('./routes/users');
var DishRoutes = require('./routes/dishes');

/**
 * App Variables
 */
const app = express();
const port = process.env.PORT || "8000";

/**
 *  App Configuration
 */
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json())

/**
 * Routes Definitions
 */
app.use('/users', UserRoutes);
app.use('/dishes', DishRoutes);

/**
 * Server Activation
 */
app.listen(port, () => {
  console.log(`Listening to requests on http://localhost:${port}`);
});