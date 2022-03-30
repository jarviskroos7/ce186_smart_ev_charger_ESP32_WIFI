"use strict";function WiFiPortalViewModel(e,n){var i=this;i.baseHost=ko.observable(""!==e?e:"openevse.local"),i.basePort=ko.observable(n),i.baseEndpoint=ko.pureComputed(function(){var e="//"+i.baseHost();return 80!==i.basePort()&&(e+=":"+i.basePort()),e}),i.config=new ConfigViewModel(i.baseEndpoint),i.status=new StatusViewModel(i.baseEndpoint),i.scan=new WiFiScanViewModel(i.baseEndpoint),i.wifi=new WiFiConfigViewModel(i.baseEndpoint,i.config,i.status,i.scan),i.time=new TimeViewModel(i.status),i.initialised=ko.observable(!1),i.updating=ko.observable(!1),i.wifi.selectedNet.subscribe(function(e){!1!==e&&i.config.ssid(e.ssid())}),i.config.ssid.subscribe(function(e){i.wifi.setSsid(e)}),i.wifiPassword=new PasswordViewModel(i.config.pass);var t=null;i.start=function(){i.updating(!0),i.config.update(function(){i.status.update(function(){i.initialised(!0),t=setTimeout(i.update,5e3),i.updating(!1),i.status.connect(),i.config.min_current_hard.subscribe(function(){i.generateCurrentList()}),i.config.max_current_hard.subscribe(function(){i.generateCurrentList()}),i.generateCurrentList()})})},i.update=function(){i.updating()||(i.updating(!0),null!==t&&(clearTimeout(t),t=null),i.status.update(function(){t=setTimeout(i.update,5e3),i.updating(!1)}))},i.openEvseSetting=function(n){var t=i.config[n],e=new ConfigGroupViewModel(i.baseEndpoint,function(){var e={};return e[n]=t(),e});return t.subscribe(function(){i.config.loaded()&&e.save()}),e},i.openEvseService=i.openEvseSetting("service"),i.openEvseMaxCurrentSoft=i.openEvseSetting("max_current_soft"),i.createCurrentArray=function(t,e){return Array(e-t+1).fill().map(function(e,n){return{name:t+n+" A",value:t+n}})},i.generateCurrentList=function(){var e=i.config.min_current_hard(),n=i.config.max_current_hard(),n=i.createCurrentArray(e,n);ko.mapping.fromJS(n,{},i.currentLevels)},i.serviceLevels=[{name:"Auto",value:0},{name:"1",value:1},{name:"2",value:2}],i.currentLevels=ko.observableArray(i.createCurrentArray(6,80))}function scaleString(e,n,t){return(parseInt(e)/n).toFixed(t)}!function(){var n=window.location.hostname,t=window.location.port;$(function(){var e=new WiFiPortalViewModel(n,t);ko.applyBindings(e),e.start()})}();
//# sourceMappingURL=wifi_portal.js.map