var express = require('express');
const db = require('../config/db');
var dishes = express.Router();

// Return all Dishes
dishes.get('/getall', (req , res , next) => {
    let query = "SELECT dish_title,dish_price,dish_ingredients,dish_image FROM dishes";
    db.query(query , function (error , results , fields) {
        if (error) throw error ;
        res.json({ data: results });
    });
});



module.exports = dishes;
