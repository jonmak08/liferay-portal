define("frontend-js-metal-web@1.0.0/metal-list/src/ListItem.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,t,i){"use strict";function s(e){return e&&e.__esModule?e:{"default":e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function n(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var r=s(t),l=i.SoyTemplates.get();"undefined"==typeof l.ListItem&&(l.ListItem={}),l.ListItem.render=function(e,t,i){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<li id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="listitem list-group-item component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+' clearfix" data-index="'+soy.$$escapeHtmlAttribute(e.index)+'">'+l.ListItem.item(e,null,i)+"</li>")},goog.DEBUG&&(l.ListItem.render.soyTemplateName="Templates.ListItem.render"),l.ListItem.item=function(e,t,i){var s=(e.item.avatar?'<span class="list-image pull-left '+soy.$$escapeHtmlAttribute(e.item.avatar["class"])+'">'+soy.$$escapeHtml(e.item.avatar.content)+"</span>":"")+'<div class="list-main-content pull-left"><div class="list-text-primary">'+soy.$$escapeHtml(e.item.textPrimary)+"</div>"+(e.item.textSecondary?'<div class="list-text-secondary">'+soy.$$escapeHtml(e.item.textSecondary)+"</div>":"")+"</div>";if(e.item.icons)for(var a=e.item.icons,n=a.length,o=0;n>o;o++){var r=a[o];s+='<span class="btn-icon '+soy.$$escapeHtmlAttribute(r)+' pull-right"></span>'}if(e.item.iconsHtml){s+='<div class="pull-right">';for(var l=e.item.iconsHtml,m=l.length,c=0;m>c;c++){var p=l[c];s+=soy.$$escapeHtml(p)}s+="</div>"}return (s+=e.item.label?'<span class="label list-label pull-right '+soy.$$escapeHtmlAttribute(e.item.label["class"])+'">'+soy.$$escapeHtml(e.item.label.content)+"</span>":"", soydata.VERY_UNSAFE.ordainSanitizedHtml(s))},goog.DEBUG&&(l.ListItem.item.soyTemplateName="Templates.ListItem.item"),l.ListItem.render.params=["id","index","item"],l.ListItem.item.params=["item"];var m=function(e){function t(){return (a(this,t), n(this,e.apply(this,arguments)))}return (o(t,e), t)}(r["default"]);m.prototype.registerMetalComponent&&m.prototype.registerMetalComponent(m,"ListItem"),m.RENDERER=i.SoyRenderer,i.SoyAop.registerTemplates("ListItem"),e["default"]=m});