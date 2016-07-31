'use strict';

var bcrypt = require('bcrypt-nodejs');
var mongoose = require('mongoose');

/* CONSTANTS */
var SALT_FACTOR = 10;

var UserModel = function() {
  var userSchema = mongoose.Schema({
    email: {
      type: String,
      lowercase: true,
      trim: true,
      unique: true
    },
    password: String,
    facebook: {
      accessToken: String,
      expires: String,
      renewToken: String
    }
  }, {
    timestamps: true
  });

  userSchema.pre('save', function(next) {
    var user = this;

    if (!user.isModified('password')) {
      return next();
    }

    bcrypt.genSalt(SALT_FACTOR, function(err, salt) {
      if (err) {
        return next(err);
      }

      bcrypt.hash(user.password, salt, null, function(err, hash) {
        if (err) {
          return next(err);
        }

        user.password = hash;
        next();
      });
    });
  });

  judgeSchema.methods.passwordMatches = function(candidatePassword) {
    return bcrypt.compareSync(candidatePassword, this.password);
  };

  var user = mongoose.model('User', userSchema);

  return user;
};

module.exports = new UserModel();
