define("frontend-js-metal-web@1.0.0/metal-dom/src/DomEventHandle-min", ["exports","metal-events/src/events"], function(e,t){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function r(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var i=function(e){function t(o,i,s,c){n(this,t);var a=r(this,e.call(this,o,i,s));return (a.capture_=c, a)}return (o(t,e), t.prototype.removeListener=function(){this.emitter_.removeEventListener(this.event_,this.listener_,this.capture_)}, t)}(t.EventHandle);i.prototype.registerMetalComponent&&i.prototype.registerMetalComponent(i,"DomEventHandle"),e["default"]=i});