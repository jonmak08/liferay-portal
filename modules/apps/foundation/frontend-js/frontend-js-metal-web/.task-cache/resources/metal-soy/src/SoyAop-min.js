define("frontend-js-metal-web@1.0.0/metal-soy/src/SoyAop-min", ["exports","./SoyTemplates"], function(n,e){"use strict";function t(n){return n&&n.__esModule?n:{"default":n}}Object.defineProperty(n,"__esModule",{value:!0});var l=t(e),i={interceptFn_:null,getOriginalFn:function(n){return n.originalFn?n.originalFn:n},handleTemplateCall_:function(n,e,t,l,r,a){return i.interceptFn_?i.interceptFn_.call(null,n,e,t,l,r,a):t.call(null,l,r,a)},registerTemplates:function(n){var e=l["default"].get(n);Object.keys(e).forEach(function(t){var l=e[t];l.originalFn||(e[t]=i.handleTemplateCall_.bind(null,n,t,l),e[t].originalFn=l)})},startInterception:function(n){i.interceptFn_=n},stopInterception:function(){i.interceptFn_=null}};n["default"]=i});