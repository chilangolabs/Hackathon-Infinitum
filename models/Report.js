'use strict';

var bcrypt = require('bcrypt-nodejs');
var mongoose = require('mongoose');

var ReportModel = function() {
  var reportSchema = mongoose.Schema({
    problem: {
      type: String,
      enum: ['agua', 'alumbrado', 'bache', 'basura']
    },
    coords: [Number],
    score: Number,
    _user: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User'
    },
    history: [
      {
        status: String,
        attendedAt: Date,
        attender: String
      }
    ]
  }, {
    timestamps: true
  });

  var report = mongoose.model('Report', reportSchema);

  return report;
};

module.exports = new ReportModel();
