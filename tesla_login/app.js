/* jslint node: true, esversion: 6 */
"use strict";


const express = require("express");
const minimist = require("minimist");
const debug = require("debug")("tesla:app");
const teslajs = require('teslajs');
const cors = require('cors')


let args = minimist(process.argv.slice(2), {
  alias: {
    h: "help",
    v: "version"
  },
  default: {
    help: false,
    version: false,
    port: 3000,
    log: 0
  },
});

if(args.help) {
  console.log("Tesla JS login");
  return 0;
}

if(args.version) {
  console.log("0.0.1");
  return 0;
}

const port = args.port;
const log = args.log;

const app = express();

//
// Create HTTP server by ourselves.
//

// Setup the API endpoints
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors());

teslajs.setLogLevel(log);

app.post("/login", function (req, res) {
  teslajs.login({
    username: req.body.username,
    password: req.body.password,
    mfaPassCode: req.body.mfaPassCode || false,
    mfaDeviceName: req.body.mfaDeviceName || false
  }, (err, result) => {
    if(err) {
      res.status(403).send({
        error: err.message
      });
      return;
    }

    res.status(200).send(result.body);
  });
});

app.listen(port, () => console.log("Tesla Login listening on port " + port + "!"));
