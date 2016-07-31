'use strict';

console.log(`Version: ${process.version}`);
var express = require('express');
var app = express();
let bodyParser = require('body-parser');
var jwt = require('jsonwebtoken');
var fb = require("./messenger.js");
var {sendTextMessage, receivedMessage} = fb;
var User = require('./models/User');

app.use(bodyParser.json());

let router = express.Router();

router.route('/').get(function(req, res, next){
  res.end('OK');
});

router.route('/bot/webhook')
  .get(function(req, res, next) {
    if (req.query['hub.mode'] === 'subscribe'
      && req.query['hub.verify_token'] === process.env.VALIDATION_TOKEN) {
      console.log("Validating webhook");
      res.status(200).send(req.query['hub.challenge']);
    } else {
      console.error("Failed validation. Make sure the validation tokens match.");
      res.sendStatus(403); 
    }
  })
  .post(function(req, res, next) {
    var data = req.body;
    if (data.object === 'page') {
      data.entry.forEach(function(pageEntry) {
        var pageID = pageEntry.id;
        var timeOfEvent = pageEntry.time;
        
        pageEntry.messaging.forEach(function(messagingEvent) {
          if (messagingEvent.message && !messagingEvent.message.is_echo) {
            console.log(req.method, req.originalUrl, messagingEvent.message.text);
            receivedMessage(messagingEvent);
          }
        });
      });
    }
    
    res.sendStatus(200);
  });

router.post('/auth/signup', function(req, res, next) {
  var email = req.body.email;
  var password = req.body.passwod;

  if (!email || !password) {
    // TODO
    return res.status(400).json({
      success: false,
      message: 'Falta algún paramétro'
    });
  }
  
  var candidateUser = new User({
    email: email,
    passwod: password
  });
  
  candidateUser.save(function(err) {
    if (err) {
      // TODO
      return res.status(500).json({
        success: false,
        message: 'Ocurruó un error'
      });
    }
    
    res.status(201).json({
      success: true,
      message: 'Usuario creado correctamente'
    });
  });
});

app.use(router);

app.listen(process.env.PORT, process.env.IP);