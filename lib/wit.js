'use strict';

let Wit = require('node-wit').Wit;

const accessToken = process.env.WIT;

// Quickstart example
// See https://wit.ai/ar7hur/quickstart

const firstEntityValue = (entities, entity) => {
  const val = entities && entities[entity] &&
    Array.isArray(entities[entity]) &&
    entities[entity].length > 0 &&
    entities[entity][0].value
  ;
  if (!val) {
    return null;
  }
  return typeof val === 'object' ? val.value : val;
};

const actions = {
  send(request, response) {
    const {sessionId, context, entities} = request;
    const {text, quickreplies} = response;
    return new Promise(function(resolve, reject) {
      console.log('sending...', JSON.stringify(response));
      return resolve();
    });
  },
  reportar({context, entities}) {
    return new Promise(function(resolve, reject) {
      var location = firstEntityValue(entities, 'location')
      var problema = firstEntityValue(entities, 'problema')
      if (location) {
        context.ticket = Date.now().toString(16); // we should call a weather API here
        delete context.missingLocation;
      } else {
        context.missingLocation = true;
        delete context.ticket;
      }
      return resolve(context);
    });
  },
};

const client = new Wit({accessToken, actions});

module.exports = client;