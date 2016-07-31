'use strict';

let request = require('request');

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
  
        // console.log("Successfully sent generic message with id %s to recipient %s", 
        //   messageId, recipientId);
        console.log('Mensaje Enviado: ', messageData.message.text);
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

module.exports = sendTextMessage;