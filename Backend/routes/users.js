var express = require('express');

const db = require('../config/db');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

var users = express.Router();
const saltRounds = 10;

users.get('/getall', (req , res , next) => {
    let query = "SELECT user_fullname FROM users";
    db.query(query , function (error , results , fields) {
        if (error) throw error ;
        res.json(results);
    });
});


//REGISTER
users.post('/register',(req, res, next) => {
    console.log(req.body);
    const fname = req.body.fullname;
    const email = req.body.email;
    const password = req.body.password;
    const confirmPassword = req.body.confirmpassword;
    const phone = req.body.phone;
    if( confirmPassword == password){
        bcrypt.hash(password, saltRounds, function (err, hash) {
            let query = "INSERT INTO `users` (user_fullname,user_email, user_password , user_phone) VALUES ('" +
              fname +  "','" + email + "','" + hash + "','" + phone + "')";
      
            db.query(query, (err, result) => {
              if (err) {
                console.log(err)
                return res.status(500).send(err);
              }
              db.query("SELECT LAST_INSERT_ID() as user_id", function (error, results, fields) {
        
                if (error) throw error;
        
                const user_id = results[0];
        
                let token = jwt.sign({ email: email, userId: user_id }, 'secret', {
                  expiresIn: '1h'
                })
                res.json({ token: token });
              })
            })
          });
    } else {
        return res.json("Password is not the same !" );
    }

  
  });
  
//Login
  users.post('/login', function (req, res, next) {
    console.log(req.body);
    const email = req.body.email;
    const password = req.body.password;
  
    db.query("SELECT user_id , user_email , user_password FROM users WHERE user_email = ?", [email], function (error, results, fields) {
  
      if (error) throw error;
  
      if (results.length === 0) {
        return res.json({ error: "User doesnot exist!" })
      }
      else {
          console.log(password);
        bcrypt.compare(password, results[0].user_password, (err, respond) => {
  
          if (!respond) {
            return res.json({ error: "Password is incorrect!" });
          }
          if (respond) {
            console.log(results)
            let token = jwt.sign({ email: email, userId: results[0].ID }, 'secret', {
              expiresIn: '1h'
            })
            res.json({
              token: token,
              expireIn: 3600
            });
          }
        })
      }
    })
  });
  


module.exports = users;
