'use strict';

let request = require('request');
let witClient = require('./lib/wit.js');
const sessions = {};

function callSendAPI(messageData) {
  return new Promise(function(resolve, reject) {
    request({
      uri: 'https://graph.facebook.com/v2.6/me/messages',
      qs: { access_token: process.env.PAGE_ACCESS_TOKEN },
      method: 'POST',
      json: messageData
  
    }, function (error, response, body) {
      if (!error && response.statusCode == 200) {
        var recipientId = body.recipient_id;
        var messageId = body.message_id;
  
        console.log("Successfully sent generic message with id %s to recipient %s", 
          messageId, recipientId);
        resolve();
      } else {
        console.error("Unable to send message.");
        // console.error(response);
        // console.error(error);
        reject(error);
      }
    });  
  });
}

function sendTextMessage(recipientId, messageText) {
  var messageData = {
    recipient: {
      id: recipientId
    },
    message: {
      text: messageText
    }
  };
  
  return callSendAPI(messageData);
}

function receivedMessage(event) {
  var senderID = event.sender.id;
  var recipientID = event.recipient.id;
  var timeOfMessage = event.timestamp;
  var message = event.message;

  console.log("Received message for user %d and page %d at %d with message:", 
    senderID, recipientID, timeOfMessage);
  console.log(JSON.stringify(message));

  var messageId = message.mid;

  // You may get a text or attachment but not both
  var messageText = message.text;
  var messageAttachments = message.attachments;

  if (messageText) {

    // If we receive a text message, check to see if it matches any special
    // keywords and send back the corresponding example. Otherwise, just echo
    // the text we received.
    switch (messageText) {
      case 'image':
        // sendImageMessage(senderID);
        break;

      case 'button':
        // sendButtonMessage(senderID);
        break;

      case 'generic':
        // sendGenericMessage(senderID);
        break;

      case 'receipt':
        // sendReceiptMessage(senderID);
        break;

      default:
        witClient.converse(senderID, messageText, sessions[senderID] = sessions[senderID] || {})
          .then(function(context){
            sessions[senderID] = context;
            console.log('context', JSON.stringify(context));
            return sendTextMessage(senderID, context.msg);
          });
        
    }
  } else if (messageAttachments) {
    sendTextMessage(senderID, "Este tipo de mensaje no está soportado.");
  }
}

module.exports = {callSendAPI, sendTextMessage, receivedMessage};