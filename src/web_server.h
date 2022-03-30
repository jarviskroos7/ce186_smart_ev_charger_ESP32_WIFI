#ifndef _EMONESP_WEB_SERVER_H
#define _EMONESP_WEB_SERVER_H

#include <ArduinoJson.h>
#include <MongooseHttpServer.h>

// Content Types
extern const char _CONTENT_TYPE_HTML[];
#define CONTENT_TYPE_HTML FPSTR(_CONTENT_TYPE_HTML)

extern const char _CONTENT_TYPE_TEXT[];
#define CONTENT_TYPE_TEXT FPSTR(_CONTENT_TYPE_TEXT)

extern const char _CONTENT_TYPE_CSS[];
#define CONTENT_TYPE_CSS FPSTR(_CONTENT_TYPE_CSS)

extern const char _CONTENT_TYPE_JSON[];
#define CONTENT_TYPE_JSON FPSTR(_CONTENT_TYPE_JSON)

extern const char _CONTENT_TYPE_JS[];
#define CONTENT_TYPE_JS FPSTR(_CONTENT_TYPE_JS)

extern const char _CONTENT_TYPE_JPEG[];
#define CONTENT_TYPE_JPEG FPSTR(_CONTENT_TYPE_JPEG)

extern const char _CONTENT_TYPE_PNG[];
#define CONTENT_TYPE_PNG FPSTR(_CONTENT_TYPE_PNG)

extern const char _CONTENT_TYPE_SVG[];
#define CONTENT_TYPE_SVG FPSTR(_CONTENT_TYPE_SVG)

extern MongooseHttpServer server;

extern void web_server_setup();
extern void web_server_loop();

extern void web_server_event(JsonDocument &event);

typedef const __FlashStringHelper *fstr_t;

bool requestPreProcess(MongooseHttpServerRequest *request, MongooseHttpServerResponseStream *&response, fstr_t contentType = CONTENT_TYPE_JSON);
void dumpRequest(MongooseHttpServerRequest *request);

#endif // _EMONESP_WEB_SERVER_H
