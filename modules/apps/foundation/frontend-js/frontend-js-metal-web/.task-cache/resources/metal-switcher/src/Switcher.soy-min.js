define("frontend-js-metal-web@1.0.0/metal-switcher/src/Switcher.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,t,r){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function i(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var c=o(t),a=r.SoyTemplates.get();"undefined"==typeof a.Switcher&&(a.Switcher={}),a.Switcher.render=function(e,t,r){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<div id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="switcher component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+soy.$$escapeHtmlAttribute(e.checked?" switcher-on":"")+'"><div class="switcher-control"><div class="switcher-control-icon"></div></div></div>')},goog.DEBUG&&(a.Switcher.render.soyTemplateName="Templates.Switcher.render"),a.Switcher.render.params=["id"];var l=function(e){function t(){return (n(this,t), i(this,e.apply(this,arguments)))}return (s(t,e), t)}(c["default"]);l.prototype.registerMetalComponent&&l.prototype.registerMetalComponent(l,"Switcher"),l.RENDERER=r.SoyRenderer,r.SoyAop.registerTemplates("Switcher"),e["default"]=l});