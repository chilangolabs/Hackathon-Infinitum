'use strict';


let witClient = require('./lib/wit.js');
const sessions = {};
let sendTextMessage = require('./fbSendMessage');

function receivedMessage(event) {
  var senderID = event.sender.id;
  var recipientID = event.recipient.id;
  var timeOfMessage = event.timestamp;
  var message = event.message;

  console.log("Received message for user %d and page %d at %d with message:", 
    senderID, recipientID, timeOfMessage, message.text);
  // console.log(JSON.stringify(message));

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
        console.log('CONTEXT PRE ACTIONS', sessions[senderID]);
        witClient.runActions(senderID, messageText, sessions[senderID] = sessions[senderID] || {})
          .then(function(context){
            sessions[senderID] = context;
            console.log('CONTEXT POST ACTIONS', context);
            if (context.ticket) {
              sessions[senderID] = {};
            }
            // console.log('context ....', context);
            // console.log('context', JSON.stringify(context));
          });
        
    }
  } else if (messageAttachments) {
    sendTextMessage(senderID, "Este tipo de mensaje no está soportado.");
  }
}

module.exports = {sendTextMessage, receivedMessage};