'use strict';

console.log(`Version: ${process.version}`);
var express = require('express');
var app = express();
let bodyParser = require('body-parser');
var fb = require("./messenger.js");
var {callSendAPI, sendTextMessage, receivedMessage} = fb;

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
    console.log(req.method, req.originalUrl, JSON.stringify(data));
    if (data.object === 'page') {
      data.entry.forEach(function(pageEntry) {
        var pageID = pageEntry.id;
        var timeOfEvent = pageEntry.time;
        
        pageEntry.messaging.forEach(function(messagingEvent) {
          if (messagingEvent.optin) {
            // receivedAuthentication(messagingEvent);
          } else if (messagingEvent.message && !messagingEvent.message.is_echo) {
            receivedMessage(messagingEvent);
          } else if (messagingEvent.delivery) {
            // receivedDeliveryConfirmation(messagingEvent);
          } else if (messagingEvent.postback) {
            // receivedPostback(messagingEvent);
          } else {
            console.log("Webhook received unknown messagingEvent: ", messagingEvent);
          }
        });
      });
    }
    
    res.sendStatus(200);
  });

app.use(router);

app.listen(process.env.PORT, process.env.IP);